cmake_minimum_required(VERSION 2.8)

project(rock-paper-scissors C)

set(CMAKE_BINARY_DIR ${CMAKE_SOURCE_DIR}/bin)
set(EXECUTABLE_OUTPUT_PATH ${CMAKE_BINARY_DIR})

add_executable(${PROJECT_NAME} ${CMAKE_SOURCE_DIR}/main.c)
