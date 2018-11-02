package com.example.symptologger;

public abstract class User {
    String firstName;
    String lastName;
    private String userID;

    public User() {}

    public abstract void setFirstName(String first);

    public abstract String getFirstName();

    public abstract void setLastName(String last);

    public abstract String getLastName();

    protected abstract void createUserID(String userPrompt);

    void setUserID(String validID) {
        userID = validID;
    }

    String getUserID() {
        return userID;
    }


}
