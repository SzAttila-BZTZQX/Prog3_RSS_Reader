package sample;

import db.DBConnector;
import db.Feed_DAO;
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
//        DateFormat df = new SimpleDateFormat("EEE, d MMM yyy HH:mm:ss Z", Locale.US);
//        String sdate = "Sun, 17 Nov 2019 18:15:42 +0000";
//        Date date = feed.getPubDate();
//        System.out.println(df.format(date));


    }

    public void getFeedData(MouseEvent mouseEvent) {
        RSSReader reader = new RSSReader(feedUrl.getText());
        Feed feed = reader.readFeed();

        System.out.println(feed.getTitle());
        //Feed_DAO feed_dao = new Feed_DAO();
        //feed_dao.insertFeed(feed);
    }
}
