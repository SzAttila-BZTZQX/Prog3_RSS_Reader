package rss;

import java.util.ArrayList;

public class Category {
    String name;
    ArrayList<Feed> feeds = new ArrayList<>();

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

    public Category(String name){
        this.name = name;
    }
}
