package com.openclassroom.bookservice.Model;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Books {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String authors;
    private String publisher;
    private String title;
    private String isbn;
    private int    stock;
    private int    left; 

    //TODO utiliser Queue au lieu de lister car FIFO (First in First Out)
    private List<User> UserListReservations = new ArrayList<>();

    public Books() {}

    public Books(String authors, String publisher, String title) {
        // testing purpose
        this.authors = authors;
        this.publisher = publisher;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String author) {
        this.authors = author;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public List<User> getUserListReservations() {
        return UserListReservations;
    }
}