package com.pixy;

public class PixyRcs {

    public static final int MIN_POS = 0;
    public static final int MAX_POS = 1000;
    public static final int CENTER_POS = ((MAX_POS - MIN_POS) / 2);

    public static final int MIN_FREQUENCY_HZ = 20;
    public static final int MAX_FREQUENCY_HZ = 300;

    PixyRcs() {}

    public int getPosition(int channel) {
        int result = PixyJni.rcsGetPosition(channel);
        PixyResult.throwIfError(result);
        return result;
    }

    public void setPosition(int channel, int position) {
        Preconditions.throwIfNotConstrained(position, MIN_POS, MAX_POS);

        int result = PixyJni.rcsSetPosition(channel, position);
        PixyResult.throwIfError(result);
    }

    public void setFrequency(int frequency) {
        Preconditions.throwIfNotConstrained(frequency, MIN_FREQUENCY_HZ, MAX_FREQUENCY_HZ);

        int result = PixyJni.rcsSetFrequency(frequency);
        PixyResult.throwIfError(result);
    }
}
