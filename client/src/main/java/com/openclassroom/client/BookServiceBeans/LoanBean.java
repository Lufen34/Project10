package com.openclassroom.client.BookServiceBeans;

import java.util.Date;

public class LoanBean {
    private String      id;
    private BooksBean   book;
    private boolean     extended;
    private Date        date;
    private UserBean    user;



    
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public LoanBean() {
        extended = false;
    }

    
}