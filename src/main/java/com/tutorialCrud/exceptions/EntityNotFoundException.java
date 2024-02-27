package com.tutorialCrud.exceptions;

import java.io.Serial;
import java.util.Arrays;

public class EntityNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String[] args;

    public EntityNotFoundException(String... args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException " + Arrays.toString(args);
    }
}
