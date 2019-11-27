package rss;

import java.util.ArrayList;
import java.util.List;

public class FeedCategory {

    private Integer id;
    private String name;
    private List<Feed> feeds = new ArrayList<>();

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

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void addFeeds(List<Feed> feeds) {
        this.feeds.addAll(feeds);
    }


    public FeedCategory() {

    }

    public FeedCategory(String name){
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
