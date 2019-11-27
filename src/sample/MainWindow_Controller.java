package sample;

import db.FeedCategory_DAO;
import db.Feed_DAO;
import javafx.beans.binding.ListBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import rss.Feed;
import rss.FeedCategory;
import rss.FeedItem;
import rss.RSSReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow_Controller implements Initializable {

    @FXML
    public VBox feedItemContainer;
    public TreeView<Object> treeView;
    public WebView webView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //RSSReader reader = new RSSReader("https://www.playstationlifestyle.net/feed/");
        //Feed_DAO.insertFeed(reader.readFeed());

        initGUI();
    }

    private void initGUI(){
        List<FeedCategory> categories = FeedCategory_DAO.getAllCategory();
        initCategoriesControl(categories); // Kategóriák betöltése a Feed-ekkel TreeView-ba
    }

    private void initCategoriesControl(List<FeedCategory> categories){
        TreeItem<Object> rootNode = new TreeItem<>("Kategóriák");

        // Feed-ek kategóriával
        for (FeedCategory category : categories){
            TreeItem<Object> ctgLeaf = new TreeItem<>(category);
            if (category.getFeeds().isEmpty() == false){
                for(Feed feed : category.getFeeds()){
                    loadFeedItems(feed);
                    TreeItem<Object> feedLeaf = new TreeItem<>(feed);
                    ctgLeaf.getChildren().add(feedLeaf);
                }
            }
            rootNode.getChildren().add(ctgLeaf);
        }

        // Feed-ek kategória nélkül
        List<Feed> feeds = Feed_DAO.getAllFeedWithoutCategory();
        for(Feed feed : feeds){
            loadFeedItems(feed);
            TreeItem<Object> item = new TreeItem<>(feed);
            rootNode.getChildren().add(item);
        }

        treeView.setRoot(rootNode);
        treeView.setShowRoot(false);
        //treeView.setEditable(true);
    }

    public void treeView_Mouse_Clicked(MouseEvent mouseEvent) {
        Object selectedItem = null;

        if(treeView.getRoot().getChildren().isEmpty() == false && treeView.getSelectionModel().isEmpty() == false) {
            feedItemContainer.getChildren().clear();
            TreeItem<Object> item = treeView.getSelectionModel().getSelectedItem();
            selectedItem = item.getValue();

            if(selectedItem instanceof Feed){
                loadSelectedFeed((Feed) selectedItem);
            }

            else if(selectedItem instanceof FeedCategory){
                loadSelectedCategory((FeedCategory) selectedItem);
            }
        }
    }

    private void loadFeedItems(Feed feed){
        RSSReader reader = new RSSReader(feed.getRssUrl());
        feed.addFeedItems(reader.readFeedItems());
    }

    private void loadSelectedFeed(Feed feed){

        FXMLLoader loader;
        HBox hbox;
        try {
            for(FeedItem feedItem : feed.getFeedItems()){
                loader = new FXMLLoader(getClass().getResource("FeedItemControl.fxml"));
                hbox = loader.load();
                hbox.setOnMouseClicked(event -> loadSelectedFeedItem(feedItem));
                feedItemContainer.getChildren().add(hbox);
                FeedItemControl_Controller controller = loader.getController();
                controller.initialize(feed.getTitle(), feedItem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSelectedCategory(FeedCategory category){
        for (Feed feed : category.getFeeds()){
            loadSelectedFeed(feed);
        }
    }

    private void loadSelectedFeedItem(FeedItem feedItem){
        loadWebView(feedItem.getDescription());
    }

    private void loadWebView(String content){
        final WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(content);
    }

    private void updateFeedItemControl(){
        System.out.println("Update");

    }
}
