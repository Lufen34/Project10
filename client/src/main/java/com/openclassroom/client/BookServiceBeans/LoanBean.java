package com.openclassroom.client.BookServiceBeans;

import java.util.GregorianCalendar;
import java.util.Objects;

public class LoanBean {
    private String      id;
    private BooksBean   book;
    private boolean     extended;
    private GregorianCalendar begin;
    private GregorianCalendar end;
    private UserBean    user;

    public LoanBean() {
        extended = false;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanBean loanBean = (LoanBean) o;
        return id.equals(loanBean.id) && book.equals(loanBean.book) && user.equals(loanBean.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, user);
    }
}