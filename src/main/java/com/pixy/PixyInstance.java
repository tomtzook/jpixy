package com.pixy;

public class PixyInstance {

    private static Pixy sInstance;

    public static Pixy getDefault() {
        if (sInstance == null) {
            synchronized (PixyInstance.class) {
                if (sInstance == null) {
                    int result = PixyJni.init();
                    PixyResult.fromReturnCode(result).throwIfError();

                    sInstance = new Pixy();
                }
            }
        }

        return sInstance;
    }
}
