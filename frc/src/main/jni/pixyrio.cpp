#include "pixyrio.h"

Pixy::Pixy(PixyI2cLink *link, size_t bufferSize)
    : m_link(link)
    , m_skipStart(false)
    , m_nextBlockType(BlockType::NORMAL)
    , m_bufferSize(bufferSize) {
    m_blockBuffer = new Block[bufferSize];

    m_delayTimer.Start();
}

Pixy::~Pixy() {
    m_delayTimer.Stop();
    
    delete m_link;
    delete[] m_blockBuffer;
}

int Pixy::getBlocks(uint16_t max_blocks) {
    if (max_blocks > m_bufferSize) {
        return ERROR_TOO_MANY_BLOCKS_REQUESTED;
    }

    return getBlocks(max_blocks, m_blockBuffer);
}

int Pixy::getBlocks(uint16_t max_blocks, Block* blocks) {
    uint8_t i;
    uint16_t word;
    uint16_t checksum;
    uint16_t sum;
    uint16_t blockCount;
    Block* currentBlock;
    BlockData* currentBlockData;

    if (!m_skipStart) {
        if (!getStart()) {
            // error?
            return 0;
        }
    } else {
        m_skipStart = false;
    }

    for (blockCount = 0; blockCount < max_blocks;) {
        currentBlock = blocks + blockCount;

        checksum = m_link->getWord();
        if (PIXY_START_WORD == checksum) {
            m_skipStart = true;
            m_nextBlockType = BlockType::NORMAL;
            return blockCount;
        } else if (PIXY_START_WORD_CC == checksum) {
            m_skipStart = true;
            m_nextBlockType = BlockType::COLOR_CODE;
            return blockCount;
        } else if (0 == checksum) {
            return 0;
        }

        currentBlockData = &(currentBlock->data);
        for (i = 0, sum = 0; i < sizeof(Block) / sizeof(uint16_t); ++i) {
            if (BlockType::NORMAL == currentBlock->type && i >= 5) {
                currentBlockData->angle = 0;
                break;
            }

            word = m_link->getWord();
            sum += word;
            *((uint16_t*)currentBlockData + i) = word;
        }

        currentBlock->type = m_nextBlockType;

        if (checksum == sum) {
            ++blockCount;
        } else {
            // checksum error
        }

        word = m_link->getWord();
        if (PIXY_START_WORD == word) {
            m_nextBlockType = BlockType::NORMAL;
        } else if (PIXY_START_WORD_CC == word) {
            m_nextBlockType = BlockType::COLOR_CODE;
        } else {
            return blockCount;
        }
    }
}

Block* Pixy::getBlockFromBuffer(size_t index) {
    return m_blockBuffer + index;
}

bool Pixy::getStart() {
    uint16_t word;
    uint16_t lastWord = 0xffff;

    while (true) {
        word = m_link->getWord();
        if (0 == word && 0 == lastWord) {
            m_delayTimer.Reset();
            while (!m_delayTimer.HasPeriodPassed(0.00005)) {
                // Wait 50 microseconds
            }

            return false;
        } else if (PIXY_START_WORD == word && PIXY_START_WORD == lastWord) {
            m_nextBlockType = BlockType::NORMAL;
            return true;
        } else if (PIXY_START_WORD_CC == word && PIXY_START_WORD == lastWord) {
            m_nextBlockType = BlockType::COLOR_CODE;
            return true;
        } else if (PIXY_START_WORDX == word) {
            m_link->getByte(); // resync
        }

        lastWord = word;
    }
}

