SUMMARY = "U8g2 library test application"
DESCRIPTION = "Simple test application to verify u8g2 library installation"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# u8g2 libray dependency
DEPENDS = "u8g2"
RDEPENDS_${PN} = "u8g2"

SRC_URI = "file://my_u8g2_app.c"

S = "${WORKDIR}"


do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} \
    	-I${STAGING_INCDIR}/u8g2 \
    	-L${STAGING_LIBDIR} \
        -o my_u8g2_app my_u8g2_app.c \
        -lu8g2 -li2c
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/my_u8g2_app ${D}${bindir}/my-u8g2-app
}

FILES_${PN} = "${bindir}/my_u8g2_app"
