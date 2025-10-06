#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <linux/i2c-dev.h>

int main(void) {
int file;
const char *bus = "/dev/i2c-1";
int addr = 0x28;
char buf[8];

if((file = open(bus, O_RDWR)) < 0) {
        perror("Failed to open the i2c bus");
        exit(1);
}

if(ioctl(file,I2C_SLAVE,addr) < 0) {
        perror("Failed to acquire bus access and/or talk to slave");
        exit(1);
}

if(read(file,buf,10) != 10) {
        perror("Failed to read from the i2c bus");
} else {
        printf("Data read: %d\n" , buf[1]);
        printf("Data read: %d\n" , buf[2]);
        printf("Data read: %d\n" , buf[3]);
        printf("Data read: %d\n" , buf[4]);
        printf("Data read: %d\n" , buf[5]);
}
close(file);
return 0;
}
