SUMMARY = "A console-only image that fully supports the target device \
hardware."

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit core-image

RPI_KERNEL_DEVICETREE_OVERLAYS:append = " overlays/bmp280-overlay.dtbo"

