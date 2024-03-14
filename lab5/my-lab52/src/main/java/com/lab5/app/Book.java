package com.lab5.app;
import java.util.Date;
 
public class Book {
	private String title;
	private String author;
	private Date published;

    public Book (String title, String author, Date published){
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublished() {
        return published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public void setPublished(Date p) {
        this.published = p;
    }

    public void setAuthor(String a) {
        this.author = a;
    }

    
}