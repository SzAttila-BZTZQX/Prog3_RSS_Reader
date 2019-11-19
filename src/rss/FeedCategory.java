package rss;

import java.util.ArrayList;

public class FeedCategory {

    private Integer id;
    private String name;
    private ArrayList<Feed> feeds = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Feed> getFeeds() {
        return feeds;
    }

    public void addFeed(Feed feed) {
        feeds.add(feed);
    }


    public FeedCategory() {

    }

    public FeedCategory(String name){
        this.name = name;
    }
}
