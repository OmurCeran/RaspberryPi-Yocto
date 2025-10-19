SUMMARY = "U8g2 graphics library for monochrome displays"
DESCRIPTION = "U8g2 is a universal graphics library for monochrome displays with I2C/SPI interfaces."
HOMEPAGE = "https://github.com/olikraus/u8g2"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=60ad2f903f7c20bef7ad46d4e664d2c2"

SRC_URI = "git://github.com/olikraus/u8g2.git;protocol=https;branch=master"
SRCREV = "e496b9971b3e7a80c0f038668cacde43bb45c3b7"

S = "${WORKDIR}/git"

DEPENDS += "i2c-tools linux-libc-headers"
RDEPENDS_${PN} += "i2c-tools virtual/kernel"

do_compile() {
    mkdir -p ${B}/lib ${B}/obj
   
    # Compile all C files to object files
cd ${B}/obj/
for file in ${S}/csrc/*.c ${S}/sys/raspi_gpio/hal/*.c ${S}/sys/linux-i2c/common/*.c; do
    ${CC} ${CFLAGS} ${LDFLAGS} -fPIC \
    -I${STAGING_INCDIR} \
    -I${STAGING_KERNEL_DIR} \
    -I${S}/csrc \
    -I${S}/sys/raspi-config/hal \
    -I${S}/sys/linux-i2c/common \
    -c "$file"
done

    # Create static library
    ${AR} rcs ${B}/lib/libu8g2.a ${B}/obj/*.o
   
    # Create shared library with proper linking
    ${CC} ${CFLAGS} ${LDFLAGS} -shared -Wl,-soname,libu8g2.so.1 \
-o ${B}/lib/libu8g2.so.1.0.0 ${B}/obj/*.o

#Create symbolic links
cd ${B}/lib
ln -sf libu8g2.so.1.0.0 libu8g2.so.1
ln -sf linu8g2.so.1 libu8g2.so
}

do_install() {
    # Create directories
mkdir -p ${D}/usr/include/u8g2
mkdir -p ${D}/usr/lib
   
    # Install header files
    for dir in csrc cppsrc sys/arm-linux/port sys/linux-i2c/common sys/raspi_gpio/hal  sys/linux-i2c/common; do
    	if [ -d "${S}/${dir}" ]; then
    		find ${S}/${dir} -type f -name "*.h" -exec install -m 0644 {} ${D}/usr/include/u8g2/ \;
    	fi
     done
   
    # Install static library
    install -m 0644 ${B}/lib/libu8g2.a ${D}/usr/lib/
   
    # Install shared library
    install -m 0755 ${B}/lib/libu8g2.so.1.0.0 ${D}/usr/lib/

# Create symbolic links in install directory
cd ${D}/usr/lib
ln -sf libu8g2.so.1.0.0 libu8g2.so.1
ln -sf libu8g2.so.1 libu8g2.so

}
#Pervenet QA warnings about .so files
INSANE_SKIP_${PN} = "dev-so"
INSANE_SKIP_${PN}-dev = "dev-so"


# Export files to package
FILES_${PN} = "${libdir}/libu8g2.so.1* ${libdir}/libu8g2.so"
FILES_${PN}-dev = "${includedir}/u8g2 ${libdir}/libu8g2.a* ${libdir}/libu8g2.so"

# Create symlink for shared library if needed (optional)
pkg_postinst_${PN}() {
    ldconfig
}
