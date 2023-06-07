package com.example.restfulapi.models;

public class NameInput {
    private String firstName;
    private String midName;
    private String lastName;
    
    public NameInput(String firstName, String midName, String lastName) {
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getMidName() {
        return midName;
    }
    
    public void setMidName(String midName) {
        this.midName = midName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
