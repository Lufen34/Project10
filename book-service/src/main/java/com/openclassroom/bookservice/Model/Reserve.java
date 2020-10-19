package com.openclassroom.bookservice.Model;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.GregorianCalendar;

public class Reserve {
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String  id;
    private Books   book;
    private boolean extended = false;
    private GregorianCalendar begin;
    private GregorianCalendar end;
    private User    user;

    public Reserve() {
    }

    public Reserve(Books book, User user) {
        this.book = book;
        this.user = user;
    }

    public Reserve(String id, Books book, boolean extended, GregorianCalendar begin, GregorianCalendar end, User user) {
        this.id = id;
        this.book = book;
        this.extended = extended;
        this.begin = begin;
        this.end = end;
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
