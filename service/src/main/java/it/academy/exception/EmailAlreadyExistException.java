package it.academy.exception;

public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
