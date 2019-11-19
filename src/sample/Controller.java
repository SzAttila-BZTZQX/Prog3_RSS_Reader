package sample;

import db.DBConnector;
import db.FeedCategory_DAO;
import db.Feed_DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import rss.Feed;
import rss.FeedCategory;
import rss.FeedItem;
import rss.RSSReader;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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

        System.out.println(feed.getTitle());
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
