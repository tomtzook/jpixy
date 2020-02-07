#ifndef PIXYRIO_PIXYLINK_H
#define PIXYRIO_PIXYLINK_H

#include <frc/I2C.h>
#include "pixydefs.h"

class PixyI2cLink {
public:
    PixyI2cLink(uint8_t address=PIXY_I2C_DEFAULT_ADDR, frc::I2C::Port port=frc::I2C::Port::kOnboard);

    uint16_t getWord();
    uint8_t getByte();
    int8_t send(uint8_t* data, uint8_t len);

private:
    frc::I2C wire;
};


#endif //PIXYRIO_PIXYLINK_H
