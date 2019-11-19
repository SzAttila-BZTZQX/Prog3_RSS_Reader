package db;

import rss.Feed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Feed Data Acces Object */

public class Feed_DAO {
    public Feed getFeed(int id){
        try (Connection connection = DBConnector.getConnection()){
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Feed WHERE ID=" + id);

                if(resultSet.next()){
                    Feed feed = getFeedFromResultSet(resultSet);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Feed> getAllFeed(){
        try (Connection connection = DBConnector.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Feed");

                List<Feed> feeds = new ArrayList<Feed>();
                while(resultSet.next()){
                    Feed feed = getFeedFromResultSet(resultSet);
                    feeds.add(feed);
                }
                return feeds;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public boolean insertFeed(Feed feed){
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Feed VALUES (NULL, NULL, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, feed.getTitle());
            preparedStatement.setString(2, feed.getLink());
            preparedStatement.setString(3, feed.getDescription());
            preparedStatement.setString(4, feed.getPubDate().toString());
            preparedStatement.setString(5, feed.getFavIconUrl());
            int i = preparedStatement.executeUpdate();

            if(i == 1){
                return true;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateFeed(Feed feed) {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Feed SET Title=?, Link=?, Description=?, PubDate=?, FavIconUrl=? WHERE ID=" + feed.getId());

            preparedStatement.setString(1, feed.getTitle());
            preparedStatement.setString(2, feed.getLink());
            preparedStatement.setString(3, feed.getDescription());
            preparedStatement.setString(4, feed.getPubDate().toString());
            preparedStatement.setString(5, feed.getFavIconUrl());
            int i = preparedStatement.executeUpdate();

            if(i == 1){
                return true;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteFeed(int id) {
        try (Connection connection = DBConnector.getConnection()){
            try (Statement statement = connection.createStatement()){
                int i = statement.executeUpdate("DELETE FROM Feed WHERE ID=" + id);

                if(id == 1){
                    return true;
                }
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }


    private Feed getFeedFromResultSet(ResultSet resultSet) throws SQLException {
        Feed feed = new Feed();

        feed.setTitle( resultSet.getString("Title") );
        feed.setLink( resultSet.getString("Link") );
        feed.setDescription( resultSet.getString("Description") );
        feed.setPubDate( resultSet.getString("PubDate") );
        feed.setFavIconUrl( resultSet.getString("FavIconUrl"));

        return feed;
    }
}
