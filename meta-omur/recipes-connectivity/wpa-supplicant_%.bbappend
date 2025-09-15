FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://wpa_supplicant-wlan0.conf"
SRC_URI += "file://wlan0.network"

do_install:append() {
	install -Dm0644 ${WORKDIR}/wpa_supplicant-wlan0.conf \
		${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf

}

SYSTEMD_SERVICE:${PN} += "wpa_supplicant@wlan0.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
