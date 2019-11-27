package rss;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/** Az RSS folyamhoz tartozó kötelező, ill. opcionális propertyk */

public class Feed {
    private Integer id;
    private Integer categoryId;
    private String favIconUrl;  // A feedhez csatolt kép URL címe
    private String rssUrl;
    private ArrayList<FeedItem> feedItems = new ArrayList<>();

    // Kötelező
    private String title;
    private String link;
    private String description;

    // Opcionális
    private Date pubDate;   // A legújabb hírhez tartozó dátum


    public Integer getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Integer getCategoryId() { return categoryId; }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public void setRssUrl(String rssUrl) {
        this.rssUrl = rssUrl;
    }

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

    public String getPubDate() {
        //return pubDate;

        // A legújabb feedItem dátuma
        FeedItem feedItem = feedItems.stream().max(Comparator.comparing(FeedItem::getPubDate)).orElse(feedItems.get(0));
        DateFormat dateFormat = new SimpleDateFormat("E, d MMM y HH:mm:ss Z", Locale.US);
        return dateFormat.format(feedItem.getPubDate());
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setPubDate(String pubDate){
        DateFormat dateFormat = new SimpleDateFormat("E, d MMM y HH:mm:ss Z", Locale.US);
        try{
            Date date = dateFormat.parse(pubDate);
            this.pubDate = date;
        } catch (ParseException ex){
            System.out.printf(ex.getMessage());
        }
    }

    public String getFavIconUrl() { return favIconUrl; }

    public void setFavIconUrl(String imageUrl) { this.favIconUrl = imageUrl; }

    public ArrayList<FeedItem> getFeedItems(){
        return feedItems;
    }

    public void addFeedItem(FeedItem feedItem) { this.feedItems.add(feedItem); }

    public void addFeedItems(List<FeedItem> feedItem) { this.feedItems.addAll(feedItem); }


    public Feed(){
    }

    public Feed(String title, String link, String description, String rssUrl){
        this.title = title;
        this.link = link;
        this.description = description;
        this.rssUrl = rssUrl;
    }

    public Feed(String title, String link, String description, String rssUrl, Date pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.rssUrl = rssUrl;
        this.pubDate = pubDate;

        //Date date = feedItems.stream().max((p1, p2) -> (p1.getPubDate() - p2.getPubDate())).get();
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
