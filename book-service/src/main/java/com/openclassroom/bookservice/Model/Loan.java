package com.openclassroom.bookservice.Model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class Loan {
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String  id;
    private Books   book;
    private boolean extended = false;
    private GregorianCalendar begin;
    private GregorianCalendar end;
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

    public GregorianCalendar getBegin() {
        return begin;
    }

    public void setBegin(GregorianCalendar begin) {
        this.begin = begin;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;
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