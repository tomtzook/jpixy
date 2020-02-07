#include <jni.h>

#include "pixyrio.h"

JNIEXPORT jlong JNICALL Java_com_pixy_PixyJni_init
        (JNIEnv *env, jclass obj) {
    Pixy* pixy = new Pixy(new PixyI2cLink(), 100);
    return (jlong) pixy;
}

JNIEXPORT void JNICALL Java_com_pixy_PixyJni_close
        (JNIEnv *env, jclass obj, jlong ptr) {
    Pixy* pixy = reinterpret_cast<Pixy*>(ptr);
    delete pixy;
}

JNIEXPORT jint JNICALL Java_com_pixy_PixyJni_getBlocks
        (JNIEnv *env, jclass obj, jlong ptr, jint maxBlocks, jobjectArray data) {
    Pixy* pixy = reinterpret_cast<Pixy*>(ptr);

    jint result = (jint) pixy->getBlocks((uint16_t) maxBlocks);
    if (result >= 0) {
        for (int i = 0; i < result; ++i) {
            jintArray blockData = (jintArray) env->GetObjectArrayElement(data, i);
            jint* arrayData = (jint*) env->GetPrimitiveArrayCritical(blockData, nullptr);

            Block* block = pixy->getBlockFromBuffer(i);
            arrayData[0] = block->type;
            arrayData[1] = block->data.signature;
            arrayData[2] = block->data.x;
            arrayData[3] = block->data.y;
            arrayData[4] = block->data.width;
            arrayData[5] = block->data.height;
            arrayData[6] = block->data.angle;

            env->ReleasePrimitiveArrayCritical(blockData, arrayData, 0);
        }
    }

    return result;
}