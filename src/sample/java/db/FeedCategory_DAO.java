package sample.java.db;

import rss.FeedCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedCategory_DAO {
    public FeedCategory getCategory(int id) {
        try(Connection connection = DBConnector.getConnection()){
            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Category WHERE ID=" + id);
                if(resultSet.next()){
                    FeedCategory category = new FeedCategory();
                    category.setId( resultSet.getInt("ID") );
                    category.setName( resultSet.getString("Name") );
                    return category;
                }
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<FeedCategory> getAllCategory() {
        try(Connection connection = DBConnector.getConnection()){
            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Category");
                List<FeedCategory> categories = new ArrayList<>();
                while(resultSet.next()){
                    FeedCategory category = new FeedCategory();
                    category.setId( resultSet.getInt("ID") );
                    category.setName( resultSet.getString("Name"));

                    Feed_DAO feed_dao = new Feed_DAO();
                    category.addFeeds(feed_dao.getAllFeedByCategory(category));

                    categories.add(category);
                }
                return categories;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertCategory(FeedCategory category) {
        try(Connection connection = DBConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Category VALUES (NULL, ?)");
            preparedStatement.setString(1, category.getName());
            int i = preparedStatement.executeUpdate();
            if (i == 1){ return true; }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCategory(FeedCategory category){
        try(Connection connection = DBConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Category SET Name=? WHERE ID=" + category.getId());
            preparedStatement.setString( 1, category.getName() );
            int i = preparedStatement.executeUpdate();
            if(i == 1) { return true; }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCategory(FeedCategory category) {
        try(Connection connection = DBConnector.getConnection()){
            try(Statement statement = connection.createStatement()){
                int i = statement.executeUpdate("DELETE FROM Category WHERE ID=" + category.getId());
                if(i == 1){ return true; }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
