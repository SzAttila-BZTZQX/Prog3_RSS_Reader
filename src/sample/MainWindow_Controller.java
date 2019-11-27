package sample;

import db.FeedCategory_DAO;
import db.Feed_DAO;
import javafx.beans.binding.ListBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import java.util.*;

public class MainWindow_Controller implements Initializable {

    @FXML
    public VBox feedItemContainer;
    public TreeView<Object> treeView;
    public WebView webView;
    public Button btnAddFeed;
    public TextField txtFieldAddFeed;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //RSSReader reader = new RSSReader("https://www.playstationlifestyle.net/feed/");
        //Feed_DAO.insertFeed(reader.readFeed());
        initEvents();
        initGUI();
    }

    private void initEvents(){
        txtFieldAddFeed.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            focusState(newValue);
        });
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
        loadWebView(feedItem);
    }

    private void loadWebView(FeedItem feedItem){
        final WebEngine webEngine = webView.getEngine();
        String head = "<style>html{ margin: 30px 30px 30px 30px;} body{font-size: 20px;} a{color: black;} h1{text-align:center;}</style>";
        String h1 = "<h1><a href=" + feedItem.getLink() + ">" + feedItem.getTitle() +"</a></h1>";
        webEngine.loadContent(head + h1 + feedItem.getContent());
    }




    public void btnAddFeed_Clicked(ActionEvent actionEvent) {
        btnAddFeed.setPrefHeight(0);
        btnAddFeed.setDisable(true);
        txtFieldAddFeed.setDisable(false);
        txtFieldAddFeed.setPrefHeight(50);
        txtFieldAddFeed.requestFocus();

    }

    public void txtFieldAddFeed_OnAction(ActionEvent actionEvent) {
        RSSReader reader = new RSSReader(txtFieldAddFeed.getText());
        Feed feed = reader.readFeed();
        Feed feed1 = reader.readFeed();

        System.out.println(feed.equals(feed1));

        HashSet<Feed> feeds = Feed_DAO.getAllFeed();

        if(feeds.add(feed) == false){

        }

        txtFieldAddFeed_FocusLost();
    }

    private void focusState(boolean isFocused){
        if(isFocused == false){
            txtFieldAddFeed_FocusLost();
        }
    }

    private void txtFieldAddFeed_FocusLost(){
        txtFieldAddFeed.setDisable(true);
        txtFieldAddFeed.setPrefHeight(0);
        btnAddFeed.setDisable(false);
        btnAddFeed.setPrefHeight(50);
    }
}
