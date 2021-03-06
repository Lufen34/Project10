package com.openclassroom.client.BookServiceBeans;


import java.util.*;

public class UserBean {
    private static final long serialVersionUID = -2777427344548523655L;
    private String id;
    private String name;
    private String email;
    private String address;
    private String login;
    private String password;

    private Map<String, Boolean> listBooksToAcceptReservations = new HashMap<>();

    public UserBean() {
        // container
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getListBooksToAcceptReservations() {
        return listBooksToAcceptReservations;
    }

    public void setListBooksToAcceptReservations(Map<String, Boolean> listBooksToAcceptReservations) {
        this.listBooksToAcceptReservations = listBooksToAcceptReservations;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return id.equals(userBean.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
