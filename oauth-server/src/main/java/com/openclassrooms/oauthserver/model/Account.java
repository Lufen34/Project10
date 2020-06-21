package com.openclassrooms.oauthserver.model;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Account implements UserDetails {

    private static final long serialVersionUID = 5362917154962892330L;
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String address;
    private String login;
    private String password;
    private ERoles role;

    public ERoles getRole() {
        return role;
    }

    public void setRole(ERoles role) {
        this.role = role;
    }

    private List<Authorities> authorities = new ArrayList<>();

    public Account() {
    }

    public Account(String name, String address, String login, String password) {
        this.name = name;
        this.address = address;
        this.login = login;
        this.password = password;
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

    @Override
    public List<Authorities> getAuthorities() {
        /*List<String> authoritiesList = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            authoritiesList.add(authority.getAuthority());
        }
        List<GrantedAuthority> authorities2 = new ArrayList<>();
        authoritiesList.forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add((Authorities)authority);
        });
        return authorities2;*/
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    /*public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }*/

}