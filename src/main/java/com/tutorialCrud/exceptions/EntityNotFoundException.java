package com.tutorialCrud.exceptions;

import java.io.Serial;
import java.util.Arrays;

public class EntityNotFoundException extends RuntimeException {
    private final String[] args;

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(Class<?> entityClass, Object id, String[] args) {
        super(String.format("%s was not found for parameter %s", entityClass.getSimpleName(), id));
        this.args = args;
    }

    public EntityNotFoundException(String... args) {
        super(String.format("%s was not found for parameter %s" ));
        this.args = args;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException " + Arrays.toString(args);
    }
}
