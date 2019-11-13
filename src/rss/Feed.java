package rss;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/** Az RSS folyamhoz tartozó kötelező, ill. opcionális propertyk */

public class Feed {
    ArrayList<FeedItem> feedItems = new ArrayList<>();

    // Kötelező
    String title;
    String link;
    String description;
    String feedCategory;

    // Opcionális
    Date pubDate;
    //FeedImage image;
    //String language;
    //String copyright;
    //String category;
    //Integer ttl;

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


    public ArrayList<FeedItem> getFeedItems(){
        return feedItems;
    }

    public Feed(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;

        //Date date = feedItems.stream().max((p1, p2) -> (p1.getPubDate() - p2.getPubDate())).get();
    }
}
