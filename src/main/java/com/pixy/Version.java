package com.pixy;

public class Version {

    private final int mMajor;
    private final int mMinor;
    private final int mBuild;

    public Version(int major, int minor, int build) {
        mMajor = major;
        mMinor = minor;
        mBuild = build;
    }

    public int getMajor() {
        return mMajor;
    }

    public int getMinor() {
        return mMinor;
    }

    public int getBuild() {
        return mBuild;
    }
}
