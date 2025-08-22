package com.flipfit.exception;

public class EntityNotFoundException extends Exception {
    private String message;

    public EntityNotFoundException(int entityId, String entityName) {
        this.message = entityName + " with ID " + entityId + " was not found.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
