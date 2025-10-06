#include <QApplication>
#include <QImage>
#include <QPainter>
#include <QString>
#include <QFont>
#include <unistd.h>

extern "C" {
#include "ssd1306.h"
}

#define WIDTH 128
#define HEIGHT 64

int main(int argc, char *argv[]) {
	QApplication app(argc,argv);
	
	if(ssd1306_init("/dev/i2c-1",0x3C) != 0) {
	printf("SSD1306 init failed!\n");
	return -1;
	}

	int currentIndex = 0;
	QString menuItems[5] = {"LED1 ON", "LED2 ON", "LED3 ON", "ALL OFF","EXIT"};
	
	while(true){
	QImage img(WIDTH , HEIGHT , QImage::Format_Mono);
	img.fill(Qt::color0);

	QPainter p(&img);
	p.setPen(Qt::color1);
	p.setFont(QFont("Monospace",10));

	for(int i=0; i<5; i++){
		if(i == currentIndex){
		 p.fillRect(0,i*12,WIDTH,12,Qt:color1);
		 p.setPen(Qt:color0);
		} else {
		 p.setPen(Qt:color1);
		}
		p.drawText(2,(i+1)*12 - 2,menuItems[i]);
	}
	p.end();

	ssd1306_clear();
	ssd1306_draw_bitmap((uint8_t*)img.bits(),WIDTH,HEIGHT);
	ssd1306_display();

	sleep(1);
	currentIndex = (currentIndex + 1) % 5;
}

	return app.exec();
}
