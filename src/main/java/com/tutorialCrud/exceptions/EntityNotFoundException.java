package com.tutorialCrud.exceptions;

import java.io.Serial;
import java.util.Arrays;

public class EntityNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
<<<<<<< HEAD
=======
    private final String[] args;
>>>>>>> origin/maxime

    public EntityNotFoundException(String... args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException " + Arrays.toString(args);
    }
}
