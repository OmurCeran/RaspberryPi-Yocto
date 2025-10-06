#include "MainWindow.h"
#include "ui_MainWindow.h"
#include <QMessageBox>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_button1_clicked()
{
    QMessageBox::information(this, "Menu", "Option 1 Selected");
}

void MainWindow::on_button2_clicked()
{
    QMessageBox::information(this, "Menu", "Option 2 Selected");
}

void MainWindow::on_button3_clicked()
{
    QMessageBox::information(this, "Menu", "Option 3 Selected");
}
