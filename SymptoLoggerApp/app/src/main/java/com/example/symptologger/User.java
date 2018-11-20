package com.example.symptologger;

/**
 * <p>
 *     User model.
 *     Contains getter and setter for id, first and last name, email and cell number.
 * </p>
 */

public class User {
    private String firstName;
    private String lastName;
    private String id;
    private String email;
    private String cell;
    private String user_type;

    /**
     * Empty constructor.
     */
    public User() {}

    /**
     * Constructor with parameters
     * @param id id of a user
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param email email address of the user
     * @param cell cell number of the user
     * @param user_type type of user
     */
    public User(String id, String firstName, String lastName, String email, String cell, String user_type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cell = cell;
        this.user_type = user_type;
    }

    /**
     * Set first name of current user
     * @param first first name given by app user
     */
    public void setFirstName(String first) {
        this.firstName = first;
    }

    /**
     * Get first name
     * @return first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Set last name of current user
     * @param last last name given by app user
     */
    public void setLastName(String last) {
        this.lastName = last;
    }

    /**
     * Get last name
     * @return last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Get full name
     * @return first name + space + last name
     */
    public String getFullName() { return this.firstName + " " + this.lastName; }

    /**
     * Get ID
     * @return ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get email
     * @return email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get cell number
     * @return cell number as string
     */
    public String getCell() {
        return this.cell;
    }

    /**
     * Set ID
     * @param id
     */
    public void setId(String id)throws UserIDTooShortException {
        if (id.length() >= 8){
            this.id = id;
        } else {
            throw new UserIDTooShortException();
        }
    }

    /**
     * Set email
     * @param email email address
     */
    // TODO: email validator?
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set user type
     * @param user_type type of user
     */
    public void setUser_type(String user_type){
        this.user_type = user_type;
    }

    public String getUser_type() {
        return this.user_type;
    }

    /**
     * Set cell
     * @param cell north american phone number
     */
    // TODO: cell validator?
    public void setCell(String cell) {
        this.cell = cell;
    }
}
