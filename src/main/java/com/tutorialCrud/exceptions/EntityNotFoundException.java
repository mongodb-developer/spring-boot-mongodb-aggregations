package com.tutorialCrud.exceptions;

import java.io.Serial;
import java.util.Arrays;

public class EntityNotFoundException extends RuntimeException {
    private final String[] args;

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String... args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException " + Arrays.toString(args);
    }
}
