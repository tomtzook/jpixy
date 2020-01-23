package com.pixy;

import java.util.ArrayList;
import java.util.Collection;

public class Pixy implements AutoCloseable {

    public static final int MAX_SIGNATURE = 7;

    public static final int MIN_X = 0;
    public static final int MAX_X = 319;
    public static final int MIN_Y = 0;
    public static final int MAX_Y = 199;

    private static final int BLOCKS_ARE_NEW = 1;

    private final PixyLed mLed;
    private final PixyRcs mServo;
    private final PixyCamera mCamera;

    private Pixy(PixyLed led, PixyRcs servo, PixyCamera camera) {
        mLed = led;
        mServo = servo;
        mCamera = camera;
    }

    Pixy() {
        this (new PixyLed(), new PixyRcs(), new PixyCamera());
    }

    public PixyLed getLed() {
        return mLed;
    }

    public PixyRcs getServo() {
        return mServo;
    }

    public PixyCamera getCamera() {
        return mCamera;
    }

    public Version getFirmwareVersion() {
        int[] result = PixyJni.getFirmwareVersion();
        PixyResult.fromReturnCode(result[0]).throwIfError();
        return new Version(result[0], result[1], result[2]);
    }

    public boolean hasNewBlocks() {
        int result = PixyJni.areBlocksNew();
        return result == BLOCKS_ARE_NEW;
    }

    public Collection<Block> getBlocks(int maxBlocks) {
        int[][] blockData = new int[maxBlocks][Block.DATA_SIZE];
        int result = PixyJni.getBlocks(maxBlocks, blockData);
        PixyResult.fromReturnCode(result).throwIfError();

        Collection<Block> blocks = new ArrayList<>(result);
        for (int i = 0; i < result; i++) {
            blocks.add(Block.fromIntArray(blockData[i]));
        }

        return blocks;
    }

    @Override
    public void close() {
        PixyJni.close();
    }

    @Override
    public String toString() {
        return String.format("Version: %s, HasNewBlocks: %b", getFirmwareVersion(), hasNewBlocks());
    }
}
