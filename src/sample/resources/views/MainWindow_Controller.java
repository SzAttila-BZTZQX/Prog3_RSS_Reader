package sample.resources.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.java.db.Feed_DAO;
import sample.java.rss.Feed;
import sample.java.rss.FeedItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow_Controller {

    @FXML
    public TreeView treeView;
    public VBox feedItemContainer;

    @FXML
    private void initialize(){
        updateGUI();
    }

    private void updateGUI() {
        List<Feed> feeds = Feed_DAO.getAllFeed();
        System.out.println("sas");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FeedItemControl_Controller"));
        try {
            HBox item = loader.load();
            //feedItemContainer.getChildren().add(item);
//            FeedItemControl_Controller controller = loader.getController();
//            controller.init(feeds.get(0).getTitle(), feeds.get(0).getFeedItems().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
