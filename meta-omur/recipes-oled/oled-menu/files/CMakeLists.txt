cmake_minimum_required(VERSION 3.5)
project(oled-menu LANGUAGES CXX)

set(CMAKE_AUTOMOC ON)
set(CMAKE_INCLUDE_CURRENT_DIR ON)

find_package(Qt5 COMPONENTS Core Gui Widgets REQUIRED)

include_directories(${CMAKE_SOURCE_DIR})

add_executable(oled-menu oled_menu.cpp)

target_link_libraries(oled-menu
	Qt5::Core
	Qt5::Gui
	Qt5::Widgets
	ssd1306
)
