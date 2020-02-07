cmake_minimum_required (VERSION 2.8)
project (pixyrio CXX)

find_package(JNI REQUIRED)
find_package(wpilib REQUIRED)

add_library(pixyrio SHARED
        main/jni/pixyriojni.cpp
        main/jni/pixylink.cpp
        main/jni/pixyrio.cpp)

target_link_libraries(pixyrio wpilibc hal wpiutil)

include_directories(
        ${JNI_INCLUDE_DIRS}
        ${wpilib_INCLUDE_DIRS}
        libpixy/
        main/jni/)
