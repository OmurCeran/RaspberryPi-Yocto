# Custom Yocto BSP for Raspberry Pi 3

A custom Yocto Project layer for Raspberry Pi 3 (64-bit) with optimized 
configuration and custom application recipes.

## Features
- Custom meta-layer (meta-custom) with application recipes
- u8g2 graphics library integration for OLED displays
- Device tree configurations for I2C/SPI peripherals
- Custom image recipe (custom-image-omur)

## Build Instructions
```bash
# Clone poky
git clone -b scarthgap git://git.yoctoproject.org/poky
cd poky

# Clone meta-raspberrypi
git clone -b scarthgap https://github.com/agherzan/meta-raspberrypi.git

# Clone this layer
git clone https://github.com/OmurCeran/RaspberryPi-Yocto.git meta-custom

# Initialize build environment
source oe-init-build-env

# Add layers
bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-custom

# Build image
bitbake custom-image-omur
```

## Layer Structure
```
meta-custom/
├── conf/
│   └── layer.conf
├── recipes-apps/
│   └── test-u8g2/
└── recipes-graphics/
    └── u8g2/
```

## Hardware Tested
- Raspberry Pi 3 Model B+
- SSD1306 128x64 OLED Display (I2C)

## Technologies
- Yocto Project (Kirkstone)
- BitBake
- Device Tree (DTS)
- Embedded Linux
- C/C++

## Author
Ömür Ceran - [LinkedIn](https://linkedin.com/in/omur-ceran/)
```

#### file tree
```
meta-custom/
├── README.md
├── conf/
│   └── layer.conf
├── recipes-graphics/
│   └── u8g2/
│       └── u8g2_1.0.bb
├── recipes-apps/
│   └── test-u8g2/
│       ├── test-u8g2_1.0.bb
│       └── files/
│           └── test-u8g2.c
└── recipes-core/
    └── images/
        └── custom-image-omur.bb
