package com.pixy;

public class PixyCamera {

    PixyCamera() {}

    public void setAutoWhiteBalanceEnabled(boolean enabled) {
        int result = PixyJni.camSetAutoWhiteBalance(enabled);
        PixyResult.throwIfError(result);
    }

    public boolean isAutoWhiteBalanceEnabled() {
        int result = PixyJni.camGetAutoWhiteBalance();
        PixyResult.throwIfError(result);
        return result == 1;
    }

    public Color getWhiteBalance() {
        long result = PixyJni.camGetWhiteBalanceValue();
        PixyResult.throwIfError((int) result);

        int red = (int) ((result >> 8) & 0xff);
        int green = (int) (result & 0xff);
        int blue = (int) ((result >> 16) & 0xff);
        return new Color(red, green, blue);
    }

    public void setWhiteBalance(Color whiteBalance) {
        int result = PixyJni.camSetWhiteBalanceValue(whiteBalance.getRed(),
                whiteBalance.getGreen(), whiteBalance.getBlue());
        PixyResult.throwIfError(result);
    }

    public void setAutoExposureCompensationEnabled(boolean enabled) {
        int result = PixyJni.camSetAutoExposureCompensation(enabled);
        PixyResult.throwIfError(result);
    }

    public boolean isAutoExposureCompensationEnabled() {
        int result = PixyJni.camGetAutoExposureCompensation();
        PixyResult.throwIfError(result);
        return result == 1;
    }

    public void setExposureCompensation(ExposureCompensation exposureCompensation) {
        int result = PixyJni.camSetExposureCompensation(exposureCompensation.getGain(),
                exposureCompensation.getCompensation());
        PixyResult.throwIfError(result);
    }

    public ExposureCompensation getExposureCompensation() {
        int result = PixyJni.camGetExposureCompensation();
        PixyResult.throwIfError(result);

        int gain = result & 0xff;
        int compensation = (result >> 8) & 0xffff;
        return new ExposureCompensation(gain, compensation);
    }

    public void setBrightness(int brightness) {
        int result = PixyJni.camSetBrightness(brightness);
        PixyResult.throwIfError(result);
    }

    public int getBrightness() {
        int result = PixyJni.camGetBrightness();
        PixyResult.throwIfError(result);
        return result;
    }
}
