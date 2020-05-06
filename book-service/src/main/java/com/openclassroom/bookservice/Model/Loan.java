package com.openclassroom.bookservice.Model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class Loan {
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String  id;
    private Books   book;
    private boolean extended = false;
    private Date    date = new Date();
    private User    user;

    public Loan() {
    }
    
    public Loan(Books book, User user) {
        this.book = book;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}