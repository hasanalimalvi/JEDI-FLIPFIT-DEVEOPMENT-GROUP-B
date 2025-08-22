package com.flipfit.exception;

public class UsernameExistsException extends Exception {

    private String message;

    public UsernameExistsException(String username) {
        this.message = "Username : " + username + " already exists";
    }

    public String getMessage() {
        return message;
    }
}
