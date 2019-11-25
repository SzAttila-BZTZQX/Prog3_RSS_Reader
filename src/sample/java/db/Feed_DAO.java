package sample.java.db;

import sample.java.rss.Feed;
import sample.java.rss.FeedCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Feed Data Acces Object */

public class Feed_DAO {
    public static Feed getFeed(int id){
        try (Connection connection = DBConnector.getConnection()){
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Feed WHERE FeedID=" + id);

                if(resultSet.next()){
                    Feed feed = getFeedFromResultSet(resultSet);
                    return feed;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Feed> getAllFeed(){
        try (Connection connection = DBConnector.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Feed");

                List<Feed> feeds = new ArrayList<>();
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

    public static List<Feed> getAllFeedByCategory(FeedCategory category){
        try(Connection connection = DBConnector.getConnection()){
            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Feed WHERE CategoryID=" + category.getId());

                List<Feed> feeds = new ArrayList<>();
                while(resultSet.next()){
                    Feed feed = getFeedFromResultSet(resultSet);
                    feeds.add(feed);
                }
                return feeds;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insertFeed(Feed feed){
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

    public static boolean updateFeed(Feed feed) {
        try (Connection connection = DBConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Feed SET Title=?, Link=?, Description=?, PubDate=?, FavIconUrl=? WHERE FeedID=" + feed.getId());

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

    public static boolean deleteFeed(Feed feed) {
        try (Connection connection = DBConnector.getConnection()){
            try (Statement statement = connection.createStatement()){
                int i = statement.executeUpdate("DELETE FROM Feed WHERE FeedID=" + feed.getId());

                if(feed.getId() == 1){
                    return true;
                }
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean addToCategory(Feed feed, FeedCategory category){
        try(Connection connection = DBConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Feed SET CategoryID=? WHERE FeedID=" + feed.getId());
            preparedStatement.setInt( 1, category.getId());

            int i = preparedStatement.executeUpdate();

            if(i == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static Feed getFeedFromResultSet(ResultSet resultSet) throws SQLException {
        Feed feed = new Feed();

        feed.setId( resultSet.getInt("FeedID") );
        feed.setTitle( resultSet.getString("Title") );
        feed.setLink( resultSet.getString("Link") );
        feed.setDescription( resultSet.getString("Description") );
        feed.setPubDate( resultSet.getString("PubDate") );
        feed.setFavIconUrl( resultSet.getString("FavIconUrl"));

        return feed;
    }
}
