package com.pixy.frc;

public class PixyInstance {

    private static Pixy sInstance;

    public static Pixy getDefault() {
        if (sInstance == null) {
            synchronized (PixyInstance.class) {
                if (sInstance == null) {
                    long result = PixyJni.init();
                    PixyResult.throwIfError((int) result);

                    sInstance = new Pixy(result);
                }
            }
        }

        return sInstance;
    }
}
