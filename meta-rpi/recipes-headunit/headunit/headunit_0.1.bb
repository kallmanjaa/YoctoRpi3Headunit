SUMMARY = "headunit"
DESCRIPTION = "headunit"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=3e00ca6129dc8358315015204ab9fe15"

SRC_URI = "git://github.com/gartnera/headunit.git;branch=master;protocol=https"

SRCREV="${AUTOREV}"

SRC_URI[md5sum] = "3e00ca6129dc8358315015204ab9fe15"
SRC_URI[sha256sum] = "2c8a45ff7962cc252edde3c7bcab29085003a87b152bb409cc1b504be26ff0e0"

S = "${WORKDIR}/git"


DEPENDS += "pkgconfig \
		gtk+3 \
		glib-2.0 \
		libsdl \
		libsdl2-ttf \
		portaudio-v19 \
		protobuf-native \
		protobuf \
		gstreamer1.0 \
		gstreamer1.0-plugins-base \
		gstreamer1.0-plugins-bad \
		gstreamer1.0-libav \
		libunwind \
		libusb1 \
       "

RPROVIDES_${PN} += " openssl-dev \
					libunwind-dev \
					libsdl2-dev \
					libusb1-dev \
					libudev \
					gstreamer1.0-dev \
					gstreamer1.0-plugins-base-apps-dev \ 
					gstreamer1.0-plugins-bad-dev \
					"

# HASH for binary
TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE += "'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS}'"

do_compile () {
    
    oe_runmake -C ${S}/ubuntu
}

do_install(){
       install -d ${D}${bindir}
       install -m 0755 ${S}/ubuntu/headunit ${D}${bindir}/
	
}

SRC_URI += " file://headunit_start.service"
DISTRO_FEATURES_append = "systemd"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "headunit_start.service"


inherit systemd

do_install_append() {

             install -d ${D}${systemd_unitdir}/system/
             install -m 0644 ${WORKDIR}/headunit_start.service ${D}${systemd_unitdir}/system/
}

#Pack the path
FILES_${PN} += " ${systemd_unitdir}/system/headunit_start.service"

REQUIRED_DISTRO_FEATURES= "systemd"
