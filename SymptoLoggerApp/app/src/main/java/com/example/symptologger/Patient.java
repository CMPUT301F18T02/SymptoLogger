package com.example.symptologger;

public class Patient extends User {
    public Patient() {
        super();
    }

    public void setFirstName(String first) {
        firstName = first;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String last) {
        lastName = last;
    }

    public String getLastName() {
        return lastName;
    }

    protected void createUserID(String userPrompt) {
        // TODO:
        if (userPrompt.length() < 8) {
            throw new RuntimeException("User ID must be at least 8 characters.");
        }
        super.setUserID(userPrompt);
    }
}
