package com.unibuc.shop.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException() {
        super("An entry with the same data already exists.");
    }
}