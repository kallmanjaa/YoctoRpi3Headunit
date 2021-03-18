# base the image on minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL_append += "\
		kernel-rpi \
		openssh \
		openssl \
		ninja \
		zlib \
		gdb \
		dbus \
		fontconfig \
		net-tools \
		libdrm-dev \
		libxdamage-dev \
		wayland \
		libxext \
		egl \
		gperf \
		alsa-lib \
		dbus \
		nss \
		ttf-dejavu-serif \
		networkmanager \
		dbus \
		headunit \
		curl \
		gstreamer1.0 \
		gstreamer1.0-plugins-bad \
		gstreamer1.0-plugins-base-apps \
		gstreamer1.0-plugins-base \
		gstreamer1.0-libav \
		gstreamer1.0-plugins-good \
		gstreamer1.0-plugins-base-alsa \
		"

IMAGE_FEATURES += " x11"

DEPENDS += "boot"

USE_DEPMOD = "0"

IMAGE_BOOT_FILES = "\
					boot_partition/kernel.img;kernel.img \
					boot_partition/overlays/*;overlays/ \
					boot_partition/bcm2710-rpi-3-b.dtb;bcm2710-rpi-3-b.dtb\
					boot_partition/bootcode.bin;bootcode.bin \
					boot_partition/cmdline.txt;cmdline.txt \
					boot_partition/config.txt;config.txt \
					boot_partition/fixup.dat;fixup.dat \
					boot_partition/start.elf;start.elf \
					"
