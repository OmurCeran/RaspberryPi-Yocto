DESCRIPTION = "Auto load i2c kernel modules at boot"
LICENSE = "CLOSED"

inherit allarch

SRC_URI = "file://i2c.conf"

do_install() {
	install -d ${D}/etc/modules-load.d
	install -m 0644 ${WORKDIR}/i2c.conf ${D}/etc/modules-load.d/i2c.conf
}

FILES_${PN} += "/etc/modules-load.d/i2c.conf"
