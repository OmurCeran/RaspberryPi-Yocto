# Custom Yocto BSP for Raspberry Pi 3

A custom Yocto Project distribution for Raspberry Pi 3 (64-bit) with Python support and luma.oled library integration for SSD1306 OLED displays.

## Project Overview

This project demonstrates building a custom embedded Linux distribution using the Yocto Project, focusing on:
- Minimal rootfs optimization
- Python 3 runtime environment
- I2C peripheral configuration via device tree
- Integration of third-party Python libraries (luma.oled)
- Custom application development for embedded displays

## Features

- **Custom Yocto Layer** (meta-custom) with recipes for Python applications
- **luma.oled Integration** - Python library for SSD1306 OLED display control
- **Device Tree Configuration** - I2C interface enabled for display communication
- **Minimal Image** - Optimized rootfs with only essential packages
- **Python 3 Support** - Full Python runtime with pip package management
- **Custom Application** - Demo application for OLED display testing

## Technologies Used

- **Yocto Project** (Scarthgap 5.0)
- **BitBake** - Build system and task executor
- **Device Tree** (DTS/DTSI) - Hardware description and I2C configuration
- **Python 3** - High-level programming language
- **luma.oled** - Python library for monochrome OLED displays
- **I2C Protocol** - Communication with SSD1306 display

## Layer Structure

```
meta-custom/
├── README.md
├── conf/
│   └── layer.conf
├── recipes-core/
│   └── images/
│       └── custom-image-omur.bb
├── recipes-devtools/
    └── python/
        └── python3-luma-oled_%.bbappend
```

## Build Instructions

### Prerequisites
```bash
# Install dependencies (Ubuntu/Debian)
sudo apt-get install gawk wget git diffstat unzip texinfo gcc build-essential \
chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils \
iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint3 xterm
```

### Setup Build Environment

```bash
# Clone Poky (Yocto reference distribution)
git clone -b scarthgap git://git.yoctoproject.org/poky.git
cd poky

# Clone meta-raspberrypi BSP layer
git clone -b scarthgap https://github.com/agherzan/meta-raspberrypi.git

# Clone meta-openembedded (for Python dependencies)
git clone -b scarthgap https://github.com/openembedded/meta-openembedded.git

# Clone this custom layer
git clone https://github.com/OmurCeran/RaspberryPi-Yocto.git meta-custom

# Initialize build environment
source oe-init-build-env

# Add layers to build configuration
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-custom
```

### Configure Build

Edit `conf/local.conf`:
```bash
# Set machine
MACHINE = "raspberrypi3-64"

# Enable I2C support
ENABLE_I2C = "1"

# Add Python and luma.oled to image
IMAGE_INSTALL_append = " python3 python3-pip python3-luma-oled oled-demo"
```

### Build Image

```bash
# Build the custom image
bitbake custom-image-omur

# Image will be created in:
# tmp/deploy/images/raspberrypi3-64/custom-image-omur-raspberrypi3-64.wic.gz
```

## Flash to SD Card

```bash
# Decompress and flash to SD card (replace /dev/sdX with your SD card device)
sudo bmaptool copy tmp/deploy/images/raspberrypi3-64/custom-image-omur-raspberrypi3-64.rootfs-20250902205531.wic.bz2 /dev/sdX
```

## Hardware Setup

### Required Components
- Raspberry Pi 3 Model B/B+
- SSD1306 128x64 OLED Display (I2C interface)
- Jumper wires

### Wiring Diagram

| SSD1306 Pin | Raspberry Pi Pin | Description |
|-------------|------------------|-------------|
| VCC | Pin 1 (3.3V) | Power supply |
| GND | Pin 6 (GND) | Ground |
| SCL | Pin 5 (GPIO 3 / SCL) | I2C Clock |
| SDA | Pin 3 (GPIO 2 / SDA) | I2C Data |

**I2C Address:** 0x3C (default for most SSD1306 modules)

## Testing on Device

After booting the Raspberry Pi with the custom image:

```bash
# Check if I2C is enabled
ls /dev/i2c*
# Expected output: /dev/i2c-1

# Verify I2C device detection (install i2c-tools if needed)
i2cdetect -y 1
# Should show device at address 0x3C (3c)

# Test SSD1306
./python3-my-ssd1306-menu
```

## What I Learned

Through this project, I gained hands-on experience with:

- **Yocto Project Workflow**: Creating custom layers, writing BitBake recipes, managing dependencies
- **BSP Development**: Device tree configuration, enabling kernel modules, peripheral integration
- **Embedded Linux**: Cross-compilation, rootfs optimization, package management
- **I2C Protocol**: Hardware communication, device addressing, driver configuration
- **Python Packaging**: Creating recipes for Python applications and libraries in Yocto
- **Build Optimization**: Reducing image size, minimizing boot time, dependency resolution

This self-directed project helped me transition from using pre-built distributions (like Raspbian) to understanding the complete Linux BSP development cycle from bootloader to application layer.

## Troubleshooting

### I2C Device Not Detected
```bash
# Check if I2C kernel module is loaded
lsmod | grep i2c

# Enable I2C manually
sudo raspi-config
# Interface Options -> I2C -> Enable
```

### Permission Denied on /dev/i2c-1
```bash
# run with sudo
sudo python3 oled_demo.py
```

## Resources

- [Yocto Project Documentation](https://docs.yoctoproject.org/)
- [meta-raspberrypi Layer](https://github.com/agherzan/meta-raspberrypi)
- [luma.oled Documentation](https://luma-oled.readthedocs.io/)
- [SSD1306 Datasheet](https://cdn-shop.adafruit.com/datasheets/SSD1306.pdf)

## Future Improvements

- [ ] Add support for different OLED display types (SSD1331, SH1106)
- [ ] Implement systemd service for auto-start on boot
- [ ] Create web interface for remote OLED control
- [ ] Add sensor integration (temperature, humidity display)
- [ ] Optimize Python runtime footprint
- [ ] Implement custom kernel module for display driver

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

**Ömür Ceran**
- LinkedIn: [linkedin.com/in/omur-ceran](https://linkedin.com/in/omur-ceran/)
- Email: ceran.omur@gmail.com
- GitHub: [@OmurCeran](https://github.com/OmurCeran)

---

If you find this project helpful, please consider giving it a star!

## Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/OmurCeran/RaspberryPi-Yocto/issues).

## Acknowledgments

- Yocto Project community for excellent documentation
- meta-raspberrypi maintainers
- luma.oled developers for the Python library
