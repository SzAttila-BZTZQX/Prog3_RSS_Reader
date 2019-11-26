package sample;

import db.FeedCategory_DAO;
import db.Feed_DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import rss.Feed;
import rss.FeedCategory;
import rss.RSSReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow_Controller implements Initializable {

    @FXML
    public VBox feedItemContainer;

    List<Feed> feeds = new ArrayList<>();
    List<FeedCategory> categories = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateGUI();
    }

    private void updateGUI(){
        //feeds = Feed_DAO.getAllFeed();
        //categories = FeedCategory_DAO.getAllCategory();

        RSSReader reader = new RSSReader("https://www.psu.com/feed/");

        Feed feed = Feed_DAO.getFeed(6);
        FeedCategory category = FeedCategory_DAO.getCategory(1);

        Feed_DAO.addToCategory(feed, category);
    }
}
