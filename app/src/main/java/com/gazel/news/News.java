package com.gazel.news;

public class News {
    private  String image;
    private  String title;
    private  String  description;
    private  String link;

    public News(String image, String title, String description, String link) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
