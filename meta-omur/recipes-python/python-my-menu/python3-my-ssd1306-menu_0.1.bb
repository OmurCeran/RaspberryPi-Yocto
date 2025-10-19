SUMMARY = "A simple Python menu application using luma.oled"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# Source code path 
SRC_URI = "file://my_ssd1306_oled_menu.py"
S = "${WORKDIR}"

inherit python3-dir
# Runtime dependency
RDEPENDS_${PN} += " \
    python3-core \
    python3-pillow \
    python3-luma-oled \
    python3-smbus2 \
    python3-cbor2 \
    i2c-tools \
    ttf-dejavu \
"
do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/my_ssd1306_oled_menu.py ${D}${bindir}/my-ssd1306-oled-menu-app
}

FILES_${PN} = "${bindir}/my-ssd1306-oled-menu-app"
