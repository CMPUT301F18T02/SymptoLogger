package com.example.symptologger;

public class User {
    String firstName;
    String lastName;
    private String userID;

    public User() {}

    public void setFirstName(String first) {}

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String last) {}

    public String getLastName() {
        return lastName;
    }

    protected void createUserID(String userPrompt) {}

    void setUserID(String validID) {
        userID = validID;
    }

    String getUserID() {
        return userID;
    }
}
