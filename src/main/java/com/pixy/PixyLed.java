package com.pixy;

public class PixyLed {

    PixyLed() {}

    public void setRgb(Color color) {
        int result = PixyJni.ledSetRgb(color.getRed(),
                color.getGreen(), color.getBlue());
        PixyResult.fromReturnCode(result).throwIfError();
    }

    public void setMaxCurrent(long currentUamps) {
        int result = PixyJni.ledSetMaxCurrent(currentUamps);
        PixyResult.fromReturnCode(result).throwIfError();
    }

    public long getMaxCurrent() {
        long result = PixyJni.ledGetMaxCurrent();
        PixyResult.fromReturnCode((int) result).throwIfError();
        return result;
    }
}
