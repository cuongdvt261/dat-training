package com.example.restfulapi.models;

public class UserInfo {
    private String name;
    private int age;
    private String email;
    private String phoneRegion;
    private String address;

    public UserInfo(String name, int age, String email, String phoneRegion, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneRegion = phoneRegion;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneRegion() {
        return phoneRegion;
    }

    public String getAddress() {
        return address;
    }
}

