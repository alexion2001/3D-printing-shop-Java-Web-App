package com.unibuc.ex1curs11.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super("An entry with the same data already exists.");
    }
}