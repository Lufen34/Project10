package com.openclassroom.bookservice.Model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class Loan {
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String  id;
    private Books   book;
    private boolean Extended = false;
    private Date    date = new Date();

    public Loan() {
    }
    
    public Loan(Books book) {
        this.book = book;
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
        return Extended;
    }

    public void setExtended(boolean Extended) {
        this.Extended = Extended;
    }
}