package com.myweddingplanner.back.exception;

public class WeedingNotFoundException extends RuntimeException {
    public WeedingNotFoundException(Long id) {
        super("Wedding not found with id: " + id);}
}
