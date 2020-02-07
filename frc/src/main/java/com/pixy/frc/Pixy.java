package com.pixy.frc;

import java.util.ArrayList;
import java.util.Collection;

public class Pixy implements AutoCloseable {

    private static final int DEFAULT_BLOCK_DATA_CACHE_SIZE = 10;

    private final long mNativePtr;

    private int[][] mBlockDataCache;

    Pixy(long nativePtr) {
        mNativePtr = nativePtr;
        mBlockDataCache = new int[DEFAULT_BLOCK_DATA_CACHE_SIZE][Block.DATA_SIZE];
    }

    public Collection<Block> getBlocks(int maxBlocks) {
        if (maxBlocks > mBlockDataCache.length) {
            mBlockDataCache = new int[maxBlocks][Block.DATA_SIZE];
        }

        int result = PixyJni.getBlocks(mNativePtr, maxBlocks, mBlockDataCache);
        PixyResult.throwIfError(result);

        Collection<Block> blocks = new ArrayList<>(result);
        for (int i = 0; i < result; i++) {
            blocks.add(Block.fromIntArray(mBlockDataCache[i]));
        }

        return blocks;
    }

    @Override
    public void close() {
        PixyJni.close(mNativePtr);
    }
}
