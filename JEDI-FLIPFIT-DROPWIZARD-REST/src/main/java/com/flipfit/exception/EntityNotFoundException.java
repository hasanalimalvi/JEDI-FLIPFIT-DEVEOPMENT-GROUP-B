package com.flipfit.exception;

/**
 *@author : "Hasanali"
 *@parameters: "int entityId, String entityName"
 *@exceptions: "N/A"
 *@description : "Exception thrown when a requested entity is not found in the database. The message is constructed to be descriptive with the entity's ID and name."
 */
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