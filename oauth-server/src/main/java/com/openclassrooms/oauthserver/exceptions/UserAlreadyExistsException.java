package com.openclassrooms.oauthserver.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
