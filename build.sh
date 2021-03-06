#!/bin/bash
set -ex -o pipefail

export CURRENT_DIR="${PWD}"

GIT_POKY="https://git.yoctoproject.org/git/poky"
GIT_OE="https://git.openembedded.org/meta-openembedded"

clone_poky_sumo(){
	git clone -b "sumo" "${GIT_POKY}"
	git clone -b "sumo" "${GIT_OE}"
}

build_env_setup(){
	source "${CURRENT_DIR}/poky/oe-init-build-env" 
}

local_conf(){
	cat >> "${CURRENT_DIR}/build/conf/local.conf" <<-EOL
	MACHINE = "rpi3"
	PACKAGE_CLASSES = "package_deb"
	BB_NUMBER_THREADS = "9"
	PARALLEL_MAKE = "-j 9"
	GPU_MEM = "16"
	IMAGE_FSTYPES = "tar.gz wic"
	DISTRO_FEATURES_append = " systemd "
	DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
	VIRTUAL-RUNTIME_init_manager = "systemd"
	VIRTUAL-RUNTIME_initscripts = ""
	LICENSE_FLAGS_WHITELIST = "commercial"
	PREFERRED_PROVIDER_gstreamer1.0-plugins-bad = "gstreamer1.0-plugins-bad"
	PREFERRED_PROVIDER_libusb1 = "libusb1"
	PREFERRED_PROVIDER_libunwind = "libunwind"
	PREFERRED_PROVIDER_libsdl2 = "libsdl2"
	PREFERRED_PROVIDER_gstreamer1.0 = "gstreamer1.0"
	EOL
}

bblayers_conf(){
	cat > "${CURRENT_DIR}/build/conf/bblayers.conf" <<-EOL
	POKY_BBLAYERS_CONF_VERSION = "2"

	BBPATH = "\${TOPDIR}"
	BBFILES ?= ""

	BBLAYERS ?= " \
	${CURRENT_DIR}/poky/meta \
	${CURRENT_DIR}/poky/meta-poky \
	${CURRENT_DIR}/poky/meta-yocto-bsp \
	${CURRENT_DIR}/meta-openembedded/meta-oe \
	${CURRENT_DIR}/meta-openembedded/meta-python \
	${CURRENT_DIR}/meta-openembedded/meta-networking \
	${CURRENT_DIR}/meta-rpi \
	"
	EOL
}

bitbake_command(){

	bitbake rpi3-image

}

main(){
	# clone_poky_sumo
	build_env_setup
	bblayers_conf
	local_conf
	bitbake_command

}


# shellcheck disable=SC2128
[[ "$0" == "$BASH_SOURCE" ]] && main "$@"
