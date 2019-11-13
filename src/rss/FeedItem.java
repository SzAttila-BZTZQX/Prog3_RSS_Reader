package rss;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

/** RSS folyamokhoz tartozó hírek */

public class FeedItem {
    //Kötelező
    String title;

    //Opcionális
    String link;
    String description;
    Date pubDate;
    //Date pubDate;
    //String author;                //A szerző email címe
    //String category;              //Egy vagy több a hírhez kapcsolódó kategória
    //URL comments;                 //URL linkje a weboldalnak ami a hírhez tartozó kommenteket tartalmazza
    //FeedItemEnclosure enclosure;    //A híhez csatolt média objektum
    //String guid;                  //Egy String ami egyedileg azonosítja a hírt
    //URL source;                   //Az hírt tartalmazó RSS csatorna


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


    public FeedItem(String title){
        this.title = title;
    }
}
