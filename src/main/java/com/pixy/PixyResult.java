package com.pixy;

public enum PixyResult {
    SUCCESS(0),
    ERROR_USB_IO(-1),
    ERROR_USB_BUSY(-6),
    ERROR_USB_NO_DEVICE(-4),
    ERROR_USB_NOT_FOUND(-5),
    ERROR_INVALID_PARAMETER(-150),
    ERROR_CHIRP(-151),
    ERROR_INVALID_COMMAND(-152),
    ERROR_JNI_MEMORY_ALLOCATION(-1001),
    UNKNOWN_ERROR(Integer.MIN_VALUE)
    ;
    private final int mErrorCode;

    PixyResult(int errorCode) {
        mErrorCode = errorCode;
    }

    public int errorCode() {
        return mErrorCode;
    }

    public void throwIfError() {
        if (mErrorCode != SUCCESS.mErrorCode) {
            throw new PixyException(name(), mErrorCode);
        }
    }

    public static void throwIfError(int returnCode) {
        PixyResult result = fromReturnCode(returnCode);
        if (result == UNKNOWN_ERROR) {
            throw new PixyException(UNKNOWN_ERROR.name(), returnCode);
        }

        result.throwIfError();
    }

    private static PixyResult fromReturnCode(int result) {
        if (result >= 0) {
            return SUCCESS;
        }

        return fromErrorCode(result);
    }

    private static PixyResult fromErrorCode(int errorCode) {
        for (PixyResult result : values()) {
            if (result.errorCode() == errorCode) {
                return result;
            }
        }

        return UNKNOWN_ERROR;
    }
}
