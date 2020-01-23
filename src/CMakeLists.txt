cmake_minimum_required (VERSION 2.8)
project (pixyjni CXX)

set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_CURRENT_SOURCE_DIR}/../cmake")

find_package(JNI REQUIRED)

add_library(pixyjni SHARED
        main/jni/pixyjni.cpp)

target_link_libraries(pixyjni pixyusb)

find_package(libpixyusb REQUIRED)
find_package(libusb-1.0 REQUIRED)
find_package(Boost 1.49 COMPONENTS thread system chrono REQUIRED)

target_link_libraries(pixyjni ${Boost_LIBRARIES})
target_link_libraries(pixyjni ${LIBUSB_1_LIBRARY})

file(STRINGS "../cmake/VERSION" LIBPIXY_VERSION)
add_definitions(-D__LIBPIXY_VERSION__="${LIBPIXY_VERSION}")

include_directories(
        ${CMAKE_CURRENT_SOURCE_DIR}/../libpixy
        ${Boost_INCLUDE_DIR}
        ${LIBUSB_1_INCLUDE_DIRS}
        ${JNI_INCLUDE_DIRS})