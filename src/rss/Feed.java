package rss;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Az RSS folyamhoz tartozó kötelező, ill. opcionális propertyk */

public class Feed {
    private ArrayList<FeedItem> feedItems = new ArrayList<>();

    // Kötelező
    private String title;
    private String link;
    private String description;
    private String feedCategory;

    // Opcionális
    private Date pubDate;
    private String imageUrl;  // A feedhez csatolt kép URL címe
    //String copyright;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getFeedCategory() {return feedCategory;}

    public void setFeedCategory(String feedCategory) { this.feedCategory = feedCategory;};

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public ArrayList<FeedItem> getFeedItems(){
        return feedItems;
    }

    public void addFeedItem(FeedItem feedItem) { this.feedItems.add(feedItem); }

    public Feed(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;

        //Date date = feedItems.stream().max((p1, p2) -> (p1.getPubDate() - p2.getPubDate())).get();
    }
}
