package com.openclassrom.batchservice.bookservicebeans;

public class BooksBean {
    private static final long serialVersionUID = 1L;
    private String id;
    private String authors;
    private String publisher;
    private String title;
    private String isbn;
    private int    stock;
    private int    left;

    public BooksBean() {
        // for container
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
}
