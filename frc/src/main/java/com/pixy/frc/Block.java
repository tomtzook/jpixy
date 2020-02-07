package com.pixy.frc;

public class Block {

    public static final int DATA_SIZE = 7;

    public enum Type {
        NORMAL(0),
        COLOR_CODE(1)
        ;

        private final int mValue;

        Type(int value) {
            mValue = value;
        }

        public int value() {
            return mValue;
        }

        public static Type fromInt(int value) {
            for (Type type : values()) {
                if (type.value() == value) {
                    return type;
                }
            }

            throw new EnumConstantNotPresentException(Type.class, String.valueOf(value));
        }
    }

    private final Type mType;
    private final int mSignature;
    private final int mX;
    private final int mY;
    private final int mWidth;
    private final int mHeight;
    private final int mAngle;

    public Block(Type type, int signature, int x, int y, int width, int height, int angle) {
        mType = type;
        mSignature = signature;
        mX = x;
        mY = y;
        mWidth = width;
        mHeight = height;
        mAngle = angle;
    }

    public static Block fromIntArray(int[] data) {
        if (data.length != DATA_SIZE) {
            throw new IllegalArgumentException("array length is not as expected");
        }

        return new Block(
                Type.fromInt(data[0]),
                data[1], data[2], data[3], data[4], data[5], data[6]);
    }

    public Type type() {
        return mType;
    }

    public int signature() {
        return mSignature;
    }

    public int x() {
        return mX;
    }

    public int y() {
        return mY;
    }

    public int width() {
        return mWidth;
    }

    public int height() {
        return mHeight;
    }

    public int angle() {
        return mAngle;
    }

    @Override
    public String toString() {
        return String.format("sig: %d (%s octal), x: %d, y: %d, width: %d, height: %d, angle: %d",
                mSignature, Integer.toOctalString(mSignature),
                mX, mY, mWidth, mHeight, mAngle);
    }
}
