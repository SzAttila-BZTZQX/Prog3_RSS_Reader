package rss;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

/** RSS folyamokhoz tartozó hírek */

public class FeedItem {
    private Integer id;
    private Integer feedId;

    //Kötelező
    private String title;

    //Opcionális
    private String link;
    private String description;
    private Date pubDate;
    private String content;
    //FeedItemEnclosure enclosure;    //A híhez csatolt média objektum
    //String guid;                  //Egy String ami egyedileg azonosítja a hírt


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() { return link; }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedItem(){

    }

    public FeedItem(String title){
        this.title = title;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if (!(obj instanceof FeedItem)){
            return false;
        }

        if(((FeedItem) obj).title == title && ((FeedItem) obj).pubDate.compareTo(pubDate) == 0 ){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return title + ", " + link + ", " + description + ", " + pubDate;
    }
}
