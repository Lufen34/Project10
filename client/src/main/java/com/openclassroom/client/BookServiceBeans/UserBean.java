package com.openclassroom.client.BookServiceBeans;



public class UserBean {
    private static final long serialVersionUID = -2777427344548523655L;
    private String id;
    private String name;
    private String address;
    private String login;
    private String password;

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
}