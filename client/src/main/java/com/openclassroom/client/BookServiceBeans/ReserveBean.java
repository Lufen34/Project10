package com.openclassroom.client.BookServiceBeans;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.GregorianCalendar;
import java.util.Objects;

public class ReserveBean {
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String  id;
    private BooksBean   book;
    private boolean extended = false;
    private GregorianCalendar begin;
    private GregorianCalendar end;
    private UserBean    user;

    public ReserveBean() {
    }

    public ReserveBean(BooksBean book, UserBean user) {
        this.book = book;
        this.user = user;
    }

    public ReserveBean(String id, BooksBean book, boolean extended, GregorianCalendar begin, GregorianCalendar end, UserBean user) {
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

    public BooksBean getBook() {
        return book;
    }

    public void setBook(BooksBean book) {
        this.book = book;
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReserveBean that = (ReserveBean) o;
        return book.equals(that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }
}
