package com.openclassroom.bookservice.Model;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class Books {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String author;
    private String publisher;
    private String title;


    public Books() {
        // Empty because of container
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Books(String author, String publisher, String title) {
        this.author = author;
        this.publisher = publisher;
        this.title = title;
    }
}