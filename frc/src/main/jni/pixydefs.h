#ifndef PIXYRIO_PIXYDEFS_H
#define PIXYRIO_PIXYDEFS_H

#include <cstdint>

#define PIXY_I2C_DEFAULT_ADDR  0x54
#define PIXY_INITIAL_ARRAYSIZE      30
#define PIXY_MAXIMUM_ARRAYSIZE      130
#define PIXY_START_WORD             0xaa55
#define PIXY_START_WORD_CC          0xaa56
#define PIXY_START_WORDX            0x55aa
#define PIXY_MAX_SIGNATURE          7
#define PIXY_DEFAULT_ARGVAL         0xffff

enum {
    ERROR_JNI_MEMORY_ALLOCATION = -1001,
    ERROR_TOO_MANY_BLOCKS_REQUESTED = -1002
};

struct BlockData {
    uint16_t signature;
    uint16_t x;
    uint16_t y;
    uint16_t width;
    uint16_t height;
    uint16_t angle;
};

enum BlockType {
    NORMAL,
    COLOR_CODE
};

struct Block {
    BlockData data;
    BlockType type;
};

#endif //PIXYRIO_PIXYDEFS_H
