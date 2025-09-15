FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://wpa_supplicant-wlan0.conf"
SRC_URI += "file://wlan0.network"

do_install:append() {
	#WPA supplicant config
	install -d ${D}${sysconfdir}/wpa_supplicant
	install -m 0644 ${WORKDIR}/wpa_supplicant-wlan0.conf \
		${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf
	#systemd-networkd config
	install -d ${D}${sysconfdir}/systemd/network
	install -m 0644 ${WORKDIR}/wlan0.network \
		${D}${sysconfdir}/systemd/network/wlan0.network
}

SYSTEMD_SERVICE:${PN} += "wpa_supplicant@wlan0.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

#Add files into package
FILES_${PN} += " \
	${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf \
	${sysconfdir}/systemd/network/wlan0.network \
"
