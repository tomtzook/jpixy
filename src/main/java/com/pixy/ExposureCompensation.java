package com.pixy;

public class ExposureCompensation {

    private final int mGain;
    private final int mCompensation;

    public ExposureCompensation(int gain, int compensation) {
        mGain = gain;
        mCompensation = compensation;
    }

    public int getGain() {
        return mGain;
    }

    public int getCompensation() {
        return mCompensation;
    }
}
