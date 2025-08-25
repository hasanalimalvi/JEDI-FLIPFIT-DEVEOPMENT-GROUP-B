package com.flipfit.exception;

/**
 *@author : "Harshil"
 *@parameters: "String username"
 *@exceptions: "N/A"
 *@description : "Exception thrown during user registration when the provided username already exists in the system."
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