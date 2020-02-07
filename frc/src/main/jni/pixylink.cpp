#include "pixylink.h"


PixyI2cLink::PixyI2cLink(uint8_t address, frc::I2C::Port port)
    : wire(port, address) {
}

uint16_t PixyI2cLink::getWord() {
    uint8_t buffer[2];
    wire.ReadOnly(2, buffer);

    return (uint16_t) ((buffer[1] << 8) + buffer[0]);
}

uint8_t PixyI2cLink::getByte() {
    uint8_t byte;
    wire.ReadOnly(1, &byte);

    return byte;
}

int8_t PixyI2cLink::send(uint8_t *data, uint8_t len) {
    wire.WriteBulk(data, len);
    return len;
}