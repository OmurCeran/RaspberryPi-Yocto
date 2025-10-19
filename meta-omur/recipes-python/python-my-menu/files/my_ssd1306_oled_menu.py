#!/usr/bin/env python3

import time
import sys
from luma.core.interface.serial import i2c
from luma.core.render import canvas
from luma.oled.device import ssd1306
from PIL import ImageFont

def main():
    # Set up I2C interface (port 1, address 0x3C)
    try:
        serial = i2c(port=1, address=0x3C)
        device = ssd1306(serial, width=128, height=64)
    except Exception as e:
        print(f"Error initializing display: {e}")
        print("Please ensure I2C is enabled and the display is connected correctly.")
        sys.exit(1)

    # Menu items
    menu_items = ["Network Info", "System Status", "Restart", "Shutdown"]
    selected_item = 0 # The first item is selected initially

    # Load a font (this font file needs to be on the system)
    try:
        font = ImageFont.truetype("DejaVuSans.ttf", 12)
    except IOError:
        font = ImageFont.load_default() # If not found, use the default

    print("Starting menu display. Press CTRL+C to exit.")
    try:
        while True:
            with canvas(device) as draw:
                # Draw the menu items
                for i, item in enumerate(menu_items):
                    y_pos = i * 15 # 15 pixels of space between each item
                   
                    if i == selected_item:
                        # Highlight the selected item
                        draw.rectangle((0, y_pos, device.width, y_pos + 15), outline="white", fill="white")
                        draw.text((2, y_pos + 1), item, font=font, fill="black")
                    else:
                        # Draw the other items normally
                        draw.text((2, y_pos + 1), item, font=font, fill="white")
           
            # Move selection to the next item (simulation instead of button reading)
            time.sleep(2)
            selected_item = (selected_item + 1) % len(menu_items)
           
    except KeyboardInterrupt:
        print("\nExiting menu display.")
        device.cleanup()

if __name__ == "__main__":
    main()

