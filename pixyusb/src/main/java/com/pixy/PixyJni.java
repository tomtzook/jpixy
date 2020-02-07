package com.pixy;

public class PixyJni {

    public static native int init();
    public static native void close();

    public static native int getFirmwareVersion(int[] versionInfo);

    public static native int areBlocksNew();
    public static native int getBlocks(int maxBlocks, int[][] dataArray);

    public static native int ledSetRgb(int red, int green, int blue);
    public static native int ledSetMaxCurrent(long currentUa);
    public static native long ledGetMaxCurrent();

    public static native int camSetAutoWhiteBalance(boolean enabled);
    public static native int camGetAutoWhiteBalance();
    public static native long camGetWhiteBalanceValue();
    public static native int camSetWhiteBalanceValue(int red, int green, int blue);

    public static native int camSetAutoExposureCompensation(boolean enabled);
    public static native int camGetAutoExposureCompensation();

    public static native int camSetExposureCompensation(int gain, int compensation);
    public static native int camGetExposureCompensation();

    public static native int camSetBrightness(int brightness);
    public static native int camGetBrightness();

    public static native int rcsGetPosition(int channel);
    public static native int rcsSetPosition(int channel, int position);
    public static native int rcsSetFrequency(int frequency);
}
