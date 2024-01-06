package com.unibuc.ex1curs11.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("The search with id " + id + " doesn't exist.");
    }
}