package com.pixy.frc;

public class PixyJni {

    public static native long init();
    public static native void close(long ptr);

    public static native int getBlocks(long ptr, int maxBlocks, int[][] dataArray);
}
