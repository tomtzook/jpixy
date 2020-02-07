package com.pixy;

public class PixyException extends RuntimeException {

    public PixyException(String name, int errorCode) {
        super(String.format("(%d) %s", errorCode, name));
    }
}
