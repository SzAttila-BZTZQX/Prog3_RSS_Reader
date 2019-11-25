package sample.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.java.rss.FeedItem;

import java.net.URL;
import java.util.ResourceBundle;

public class FeedItemControl_Controller implements Initializable {

    @FXML
    public Label lbl_website;
    public Label lbl_date;
    public Label lbl_title;
    public Label lbl_desc;

    FeedItem feedItem;
    String websiteName;

    public void init(String website, FeedItem feedItem){
        this.feedItem = feedItem;
        this.websiteName = website;
        updateGUI();
    }

    private void updateGUI(){
        lbl_website.setText(websiteName);
        lbl_date.setText(feedItem.getPubDate().toString());
        lbl_title.setText(feedItem.getTitle());
        lbl_desc.setText(feedItem.getDescription());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
