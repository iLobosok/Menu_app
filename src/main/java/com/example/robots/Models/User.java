package com.example.robots.Models;

public class User {
    private String name,email,paa,number;
    public User() {}

    public User(String name, String email, String paa, String number) {
        this.name = name;
        this.email = email;
        this.paa = paa;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaa() {
        return paa;
    }

    public void setPaa(String paa) {
        this.paa = paa;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}