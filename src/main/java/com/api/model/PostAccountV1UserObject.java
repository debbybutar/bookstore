package com.api.model;

import java.util.List;

public class PostAccountV1UserObject {
    // Success Response Fields
    private String userID;
    private String username;
    private List<Object> books;
    // Error Response Fields
    private String code;
    private String message;

    // Getters & Setters untuk semua kemungkinan
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Object> getBooks() {
        return books;
    }

    public void setBooks(List<Object> books) {
        this.books = books;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}