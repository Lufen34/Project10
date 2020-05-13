package com.openclassroom.client.BookServiceBeans;

import java.io.Serializable;

public class BooksBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String author;
    private String publisher;
    private String title;
    private String isbn;
    private int    stock;

    public BooksBean() {
        // for container
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

    
    
}