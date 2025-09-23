FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
#SRC_URI += "file://0001-add-i2c1-spi-userspace-dts.patch"

SRC_URI:append = " file://bmp280-overlay.dtso;subdir=git/arch/arm64/boot/dts/overlays"
RPI_KERNEL_DEVICETREE_OVERLAYS:append = " overlays/bmp280-overlay.dtbo"

#KERNEL_DEVICETREE += "overlays/bmp280-overlay.dts"

#DEPENDS += "dtc-native"

#overlay execute
#EXTRA_OEMAKE += "dtbs"

#do_configure:append() {
#	cp ${WORKDIR}/bmp280-overlay.dts ${S}/arch/arm64/boot/dts/overlays/
#	perl -pi -e 's/^(dtbo-.*)/$1 bmp280.dtbo \\/' ${S}/arch/arm64/boot/dts/overlays/Makefile
#}

#do_compile:append() {
#	echo ">>> Compiling bmp280-overlay.dts"
#	mkdir -p ${WORKDIR}/overlays
#	
#	set -x
#	${STAGING_BINDIR_NATIVE}/dtc -@ -I dts -O dtb \
#		-o ${S}/arch/arm64/boot/dts/overlays/bmp280.dtbo \
#		${WORKDIR}/bmp280-overlay.dts
#	set +x
#}

#do_install:append() {
#	install -d ${D}/boot/
#	install -Dm0644 ${S}/arch/arm64/boot/dts/overlays/bmp280.dtbo ${D}/boot/overlays/bmp280.dtbo
#}

#do_deploy:append() {
#	install -d ${DEPLOYDIR}/
#	cp ${B}/arch/arm64/boot/dts/overlays/bmp280.dtbo ${D}/boot/
#	install -m 0644 ${B}/arch/arm64/boot/dts/overlays/bmp280.dtbo ${DEPLOYDIR}/bmp280.dtbo
#}

#IMAGE_BOOT_FILES += "bmp280.dtbo"
#FILES:${PN} += "/boot/bmp280.dtbo"

#RPI_EXTRA_BOOT_FILES += "overlays/bmp280.dtbo"
#RPI_KERNEL_DEVICETREE_OVERLAYS += "overlays/bmp280.dtbo"
