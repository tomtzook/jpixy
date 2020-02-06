#include <jni.h>
#include <malloc.h>
#include "pixy.h"

#define ERROR_JNI_MEMORY_ALLOCATION (-1001)

extern "C" {

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_init
        (JNIEnv *env, jclass obj) {
    return (jint)pixy_init();
}

JNIEXPORT void JNICALL Java_com_pixy_PixyJni_close
        (JNIEnv *env, jclass obj) {
    pixy_close();
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_areBlocksNew
        (JNIEnv *env, jclass obj) {
    return (jint)pixy_blocks_are_new();
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_getBlocks
        (JNIEnv *env, jclass obj, jint maxBlocks, jobjectArray data) {
    struct Block* blocks = (struct Block*) malloc(maxBlocks * sizeof(Block));
    if (NULL == blocks) {
        return ERROR_JNI_MEMORY_ALLOCATION;
    }

    jint result = (jint) pixy_get_blocks((uint16_t) maxBlocks, blocks);
    if (result >= 0) {
        for (int i = 0; i < result; ++i) {
            jintArray blockData = (jintArray) env->GetObjectArrayElement(data, i);
            jint* arrayData = (jint*) env->GetPrimitiveArrayCritical(blockData, NULL);

            struct Block block = blocks[i];
            arrayData[0] = block.type;
            arrayData[1] = block.signature;
            arrayData[2] = block.x;
            arrayData[3] = block.y;
            arrayData[4] = block.width;
            arrayData[5] = block.height;
            arrayData[6] = block.angle;

            env->ReleasePrimitiveArrayCritical(blockData, arrayData, 0);
        }
    }

    return result;
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_getFirmwareVersion
        (JNIEnv *env, jclass obj, jintArray versionData) {
    uint16_t major;
    uint16_t minor;
    uint16_t build;
    int result = pixy_get_firmware_version(&major, &minor, &build);

    jint* arrayData = (jint*) env->GetPrimitiveArrayCritical(versionData, NULL);

    arrayData[0] = (jint) major;
    arrayData[1] = (jint) minor;
    arrayData[2] = (jint) build;

    env->ReleasePrimitiveArrayCritical(versionData, arrayData, 0);

    return (jint) result;
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_ledSetRgb
        (JNIEnv *env, jclass obj, jint red, jint green, jint blue) {
    return (jint) pixy_led_set_RGB((uint8_t)red, (uint8_t)green, (uint8_t)blue);
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_ledSetMaxCurrent
        (JNIEnv *env, jclass obj, jlong currentUamps) {
    return (jint) pixy_led_set_max_current((uint32_t) currentUamps);
}

JNIEXPORT jlong JNICALL Java_com_pixy_PixyJni_ledGetMaxCurrent
        (JNIEnv *env, jclass obj) {
    return (jlong) pixy_led_get_max_current();
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camSetAutoWhiteBalance
        (JNIEnv *env, jclass obj, jboolean enabled) {
    return (jint) pixy_cam_set_auto_white_balance((uint8_t) enabled);
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camGetAutoWhiteBalance
        (JNIEnv *env, jclass obj) {
    return (jint) pixy_cam_get_auto_white_balance();
}

JNIEXPORT jlong JNICALL Java_com_pixy_PixyJni_camGetWhiteBalanceValue
        (JNIEnv *env, jclass obj) {
    return (jlong) pixy_cam_get_white_balance_value();
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camSetWhiteBalanceValue
        (JNIEnv *env, jclass obj, jint red, jint green, jint blue) {
    return (jint) pixy_cam_set_white_balance_value((uint8_t) red, (uint8_t) green, (uint8_t) blue);
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camSetAutoExposureCompensation
        (JNIEnv *env, jclass obj, jboolean enabled) {
    return (jint) pixy_cam_set_auto_exposure_compensation((uint8_t) enabled);
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camGetAutoExposureCompensation
        (JNIEnv *env, jclass obj) {
    return (jint) pixy_cam_get_auto_exposure_compensation();
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camSetExposureCompensation
        (JNIEnv *env, jclass obj, jint gain, jint compensation) {
    return (jint) pixy_cam_set_exposure_compensation((uint8_t) gain, (uint16_t) compensation);
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camGetExposureCompensation
        (JNIEnv *env, jclass obj) {
    uint8_t gain;
    uint16_t compensation;

    jint result = (jint) pixy_cam_get_exposure_compensation(&gain, &compensation);
    if (result >= 0) {
        result = (gain & 0xff) & ((compensation & 0xff) << 16);
    }

    return result;
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camSetBrightness
        (JNIEnv *env, jclass obj, jint brightness) {
    return (jint) pixy_cam_set_brightness((uint8_t) brightness);
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_camGetBrightness
        (JNIEnv *env, jclass obj) {
    return (jint) pixy_cam_get_brightness();
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_rcsGetPosition
        (JNIEnv *env, jclass obj, jint channel) {
    return (jint) pixy_rcs_get_position((uint8_t) channel);
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_rcsSetPosition
        (JNIEnv *env, jclass obj, jint channel, jint position) {
    return (jint) pixy_rcs_set_position((uint8_t) channel, (uint16_t) position);
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_rcsSetFrequency
        (JNIEnv *env, jclass obj, jint frequency) {
    return (jint) pixy_rcs_set_frequency((uint16_t) frequency);
}

}