#ifndef PIXYRIO_PIXYRIO_H
#define PIXYRIO_PIXYRIO_H

#include <frc/Timer.h>
#include "pixydefs.h"
#include "pixylink.h"

class Pixy {
public:
    Pixy(PixyI2cLink* link, size_t bufferSize);
    ~Pixy();

    int getBlocks(uint16_t max_blocks);
    int getBlocks(uint16_t max_blocks, Block* blocks);

    Block* getBlockFromBuffer(size_t index);
private:
    bool getStart();

    PixyI2cLink* m_link;
    bool m_skipStart;
    BlockType m_nextBlockType;

    Block* m_blockBuffer;
    size_t m_bufferSize;

    frc::Timer m_delayTimer;
};

#endif //PIXYRIO_PIXYRIO_H
