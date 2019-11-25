package sample;

import sample.java.db.FeedCategory_DAO;
import sample.java.db.Feed_DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.java.rss.Feed;
import sample.java.rss.FeedCategory;
import sample.java.rss.RSSReader;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    Feed feed;

    @FXML
    public TextField feedUrl;
    public TextField categoryName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getFeedData(MouseEvent mouseEvent) {
        RSSReader reader = new RSSReader(feedUrl.getText());
        feed = reader.readFeed();

        System.out.println(feed.getFeedItems().get(0).getDescription());
        //Feed_DAO feed_dao = new Feed_DAO();
        //feed_dao.insertFeed(feed);

    }

    public void setCategory(MouseEvent mouseEvent) {
        FeedCategory_DAO feedCategory_dao = new FeedCategory_DAO();
        FeedCategory category = feedCategory_dao.getCategory(2);

        Feed_DAO feed_dao = new Feed_DAO();
        Feed myFeed = feed_dao.getFeed(1);

        feedCategory_dao.deleteCategory(category);
    }
}
