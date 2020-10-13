package com.openclassroom.bookservice.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class BookAndUser {
    @NotNull(message = "First parameter can not be null")
    private Books book;
    @NotNull(message = "First parameter can not be null")
    private User user;

    public BookAndUser(@JsonProperty("book") Books book, @JsonProperty("user") User user) {
        this.book = book;
        this.user = user;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
