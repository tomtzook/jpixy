package com.pixy;

public class Color {

    private final int mRed;
    private final int mGreen;
    private final int mBlue;

    public Color(int red, int green, int blue) {
        Preconditions.throwIfNotConstrained(red, 0, 255);
        Preconditions.throwIfNotConstrained(green, 0, 255);
        Preconditions.throwIfNotConstrained(blue, 0, 255);

        mRed = red;
        mGreen = green;
        mBlue = blue;
    }

    public int getRed() {
        return mRed;
    }

    public int getGreen() {
        return mGreen;
    }

    public int getBlue() {
        return mBlue;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%d", mRed, mGreen, mBlue);
    }
}
