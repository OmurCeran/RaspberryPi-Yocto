SUMMARY = "Qt based SSD1306 OLED menu demo"
DESCRIPTION = "Simple Qt app that renders a menu and outputs to SSD1306 using ssd1306_linux lib"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bac0b8c74cb3beee17f36b5c9138889b"

#FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

#SRC_URI = "file://oled-menu.pro \
#           file://main.cpp \
#           file://MainWindow.cpp \
#           file://MainWindow.h \
#           file://MainWindow.ui \
#           file://LICENSE"

#S = "${WORKDIR}"

#inherit qmake5

#DEPENDS += "qtbase ssd1306"

#do_install() {
#    install -d ${D}${bindir}
#    install -m 0755 oled-menu ${D}${bindir}/
#}

