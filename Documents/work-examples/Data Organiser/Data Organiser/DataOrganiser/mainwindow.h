#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "qtextedit.h"
#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

extern QString userDefinedClean;
extern QString userDefinedInsert;

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_action_New_triggered();
    void on_action_Save_as_triggered();
    void on_action_Save_triggered();

    void on_action_Open_triggered();

    void on_action_change_file_path_triggered();

    void on_cleanButton_clicked();

    void on_tallyIntButton_clicked();

    void on_tallyWordButton_clicked();

    void on_findDatesButton_clicked();

    void on_action_Clean_Settings_triggered();

    void on_actionTally_Settings_triggered();

    void on_action_About_triggered();

    void on_action_Instructions_triggered();

private:
    Ui::MainWindow *ui;
    QString returnFileText(QString dir);
    void performSave(QString dir);
};
#endif // MAINWINDOW_H
