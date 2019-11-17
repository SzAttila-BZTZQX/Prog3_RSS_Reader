package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import rss.Feed;
import rss.FeedItem;
import rss.RSSReader;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    public TextField feedUrl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RSSReader reader = new RSSReader("https://news.google.com/rss?x=1571747254.2933&hl=en-US&gl=US&ceid=US:en");
        Feed feed = reader.readFeed();

        System.out.println(feed.getPubDate());
    }

    public void getFeedData(MouseEvent mouseEvent) {

    }
}
