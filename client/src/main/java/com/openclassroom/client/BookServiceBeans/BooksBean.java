package com.openclassroom.client.BookServiceBeans;

import java.io.Serializable;
import java.util.*;

public class BooksBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String authors;
    private String title;
    private String isbn;
    private int    stock;
    private int    left;

    private List<UserBookModel> UserListReservations = new ArrayList<>();

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

    public List<UserBookModel> getUserListReservations() {
        return UserListReservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksBean booksBean = (BooksBean) o;
        return id.equals(booksBean.id) && authors.equals(booksBean.authors) && title.equals(booksBean.title) && isbn.equals(booksBean.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authors, title, isbn);
    }
}
