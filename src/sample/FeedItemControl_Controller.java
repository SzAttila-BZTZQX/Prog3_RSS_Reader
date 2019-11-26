package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import rss.FeedItem;
import org.jsoup.Jsoup;

public class FeedItemControl_Controller {

    @FXML
    public Label lbl_website;
    public Label lbl_date;
    public Label lbl_title;
    public Label lbl_desc;

    String website = "";
    FeedItem feedItem = null;

    public void initialize(String website, FeedItem feedItem){
        this.website = website;
        this.feedItem = feedItem;

        updateGUI();
    }

    private void updateGUI(){
        lbl_website.setText(website);
        lbl_date.setText(feedItem.getPubDate().toString());
        lbl_title.setText(feedItem.getTitle());

        String description = Jsoup.parse(feedItem.getDescription()).text(); // Nyers html kódből a szükséges szöveg
        lbl_desc.setText(description);
    }
}
