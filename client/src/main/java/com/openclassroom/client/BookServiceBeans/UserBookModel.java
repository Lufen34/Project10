package com.openclassroom.client.BookServiceBeans;

import java.util.ArrayList;
import java.util.List;

public class UserBookModel extends UserBean {

    private List<LoanBean> rented = new ArrayList<>();

    public List<LoanBean> getRented() {
        return rented;
    }

    public void setRented(List<LoanBean> rented) {
        this.rented = rented;
    }
}
