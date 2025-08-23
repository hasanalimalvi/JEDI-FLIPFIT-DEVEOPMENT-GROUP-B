package com.flipfit.exception;

/**
 *@Author : "Harshil"
 *@Parameters: "String username"
 *@Exceptions: "N/A"
 *@Description : "Exception thrown during user registration when the provided username already exists in the system."
 */
public class UsernameExistsException extends Exception {

    private String message;

    public UsernameExistsException(String username) {
        this.message = "Username : " + username + " already exists";
    }

    public String getMessage() {
        return message;
    }
}