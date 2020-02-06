# jpixy
Java port for Pixy

## Building
The _java_ part is built using _gradle_, while the native part is built with _cmake_. To build for the current system, simply run `./gradlew`/`gradlew.bat clean build`, which will build the native part as well. Outputs can be found under `build` and `build/cmake`.

### Custom Toolchain
For using a custom toolchain to compile the native part, run _gradle_ with `-PTOOLCHAIN={path/to/cmake/toolchain/file}`. The _cmake_ toolchain file must define settings for the wanted toolchain. This can be useful for cross compilation.

## Usage

`Pixy` provides the basic interfacing to the camera. It is divided into modules, each providing different functionallity.

```Java
try (Pixy pixy = PixyInstance.getDefault()) { 
    System.out.println("Pixy version: " + pixy.getFirmwareVersion());

    int frame = 0;
    while (true) {
        if (!pixy.hasNewBlocks()) {
            Thread.yield();
        }

        Collection<Block> blocks = pixy.getBlocks(10);
        System.out.println("New Block: " + (++frame));
        for (Block block : blocks) {
            System.out.println("Block :: " + block);
        }
    }
}
```

### Loading Native
Before using `Pixy`, the native library must first be loaded, just like any other native library.
