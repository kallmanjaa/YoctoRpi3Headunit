# Hobby/Learning
    Bring up rpi3 as an car headunit

# Notes:
    The rpi image is based on core-image-minimal 

# Kernel:
    used kernel version is 5.5y

# Image file
    wic file need to be flashed to sdcard using dd command or your favorite tools like rufus
    Image file: <path-to-src>/build/tmp/deploy/images/rpi3/rpi3-image-rpi3.wic

# Next steps/TODO:
    Building Headunit is ok
    Fine tune kernel recipie, implemented in hurry.. hahahah
    Next test it on device, if some problem fix them
    Next Project/learning: understand how Andorid Auto protocol is working from the source code

# Get started

## Manifest
    repo init -u https://github.com/kallmanjaa/YoctoRpi3Headunit.git -b feature/manifest -m manifest.xml
    repo sync

## Docker
    used docker to replicate same build env everywhere
    ubuntu 18.04
    docker build -t kalmanjaa_yocto .
    docker run --rm --user=kallmanjaa --name test_yocto -v ${PWD}/../YoctoRpi3Headunit:/YoctoRpi3Headunit -it kalmanjaa_yocto /bin/bash
    
## Usage 
    In the docker env,
    just run ./build.sh, everything is taken care
    
## Reference
    https://git.yoctoproject.org/git/meta-raspberrypi

## HEADUNIT Source code [REFERENCE] :
    https://github.com/gartnera/headunit.git
    branch: master 

## NOTE:
    This is just a hobby project, do not use it on your real driving car.
    
## DEMO:

https://user-images.githubusercontent.com/39487123/111698473-288f7080-8837-11eb-9793-e86639cec2ca.mp4

