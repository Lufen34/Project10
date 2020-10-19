package com.openclassroom.client.BookServiceBeans;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class BookAndUser {
    @NotNull(message = "First parameter can not be null")
    BooksBean book;
    @NotNull(message = "First parameter can not be null")
    UserBean user;

    public BookAndUser(@JsonProperty("book") BooksBean book, @JsonProperty("user") UserBean user) {
        this.book = book;
        this.user = user;
    }

    public BooksBean getBook() {
        return book;
    }

    public void setBook(BooksBean book) {
        this.book = book;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
