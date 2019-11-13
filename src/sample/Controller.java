package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import rss.Category;
import rss.Feed;
import rss.FeedItem;
import rss.RSSReader;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField feedUrl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getFeedData(MouseEvent mouseEvent) {
        RSSReader reader = new RSSReader(feedUrl.getText());
        Feed feed = reader.readFeed();
        feed.setFeedCategory("PlayStation");

        FeedItem feedItem = feed.getFeedItems().get(1);
        System.out.println(feedItem.getPubDate());
    }
}
