#include "MainWindow.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    // Uygulamayı küçük OLED ekran çözünürlüğünde başlat
    MainWindow w;
    w.resize(128, 64);
    w.show();

    return app.exec();
}
