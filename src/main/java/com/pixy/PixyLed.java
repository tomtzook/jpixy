package com.pixy;

public class PixyLed {

    PixyLed() {}

    public void setRgb(Color color) {
        int result = PixyJni.ledSetRgb(color.getRed(),
                color.getGreen(), color.getBlue());
        PixyResult.throwIfError(result);
    }

    public void setMaxCurrent(long currentUamps) {
        int result = PixyJni.ledSetMaxCurrent(currentUamps);
        PixyResult.throwIfError(result);
    }

    public long getMaxCurrent() {
        long result = PixyJni.ledGetMaxCurrent();
        PixyResult.throwIfError((int) result);
        return result;
    }
}
