SUMMARY = "U8g2 graphics library for monochrome displays"
DESCRIPTION = "U8g2 is a universal graphics library for monochrome displays with I2C/SPI interfaces."
HOMEPAGE = "https://github.com/olikraus/u8g2"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=60ad2f903f7c20bef7ad46d4e664d2c2"

PROVIDE = "u8g2"

SRC_URI = "git://github.com/olikraus/u8g2.git;protocol=https;branch=master"
SRCREV = "e496b9971b3e7a80c0f038668cacde43bb45c3b7"
SRC_URI[sha256sum] = "d95af1a84622bcbfc23d2e5eba6ae836cbd6f54b42e1e495716afcd6d7a906a9"

S = "${WORKDIR}/git"
M = "/home/omurceran/Desktop/poky-rpi/build/tmp/work/cortexa53-poky-linux/u8g2/1.0/git"
N = "/home/omurceran/Desktop/poky-rpi/build/tmp/work/cortexa53-poky-linux/u8g2/1.0/image"
#inherit cmake

FILES_${PN} += "/usr/include/u8g2"

# Header-only kütüphane olduğu için sadece include dizinine kuruyoruz
do_install() {
	mkdir -p ${N}${includedir}/u8g2
	cp -v ${M}/csrc/*.h ${N}${includedir}/u8g2/
}

