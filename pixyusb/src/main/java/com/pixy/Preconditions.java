package com.pixy;

public class Preconditions {

    private Preconditions() {}

    public static boolean constrained(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static void throwIfNotConstrained(int value, int min, int max) {
        if (!constrained(value, min, max)) {
            throw new IllegalArgumentException(String.format("Out of range %d [%d, %d]",
                    value, min, max));
        }
    }
}
