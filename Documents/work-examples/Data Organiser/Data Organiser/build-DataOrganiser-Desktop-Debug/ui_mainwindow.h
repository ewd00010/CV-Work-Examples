/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.15.6
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QAction *action_Open;
    QAction *action_New;
    QAction *action_Recently_Opened;
    QAction *action_Save;
    QAction *action_Save_All;
    QAction *action_Save_as;
    QAction *action_About;
    QAction *action_Instructions;
    QAction *action_change_file_path;
    QAction *action_Clean_Settings;
    QAction *actionTally_Settings;
    QWidget *centralwidget;
    QTextEdit *textEdit;
    QPushButton *cleanButton;
    QPushButton *tallyIntButton;
    QPushButton *tallyWordButton;
    QPushButton *findDatesButton;
    QStatusBar *statusbar;
    QMenuBar *menubar;
    QMenu *menu_File;
    QMenu *menu_Settings;
    QMenu *menu_Help;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(910, 600);
        action_Open = new QAction(MainWindow);
        action_Open->setObjectName(QString::fromUtf8("action_Open"));
        action_New = new QAction(MainWindow);
        action_New->setObjectName(QString::fromUtf8("action_New"));
        action_New->setCheckable(false);
        action_Recently_Opened = new QAction(MainWindow);
        action_Recently_Opened->setObjectName(QString::fromUtf8("action_Recently_Opened"));
        action_Save = new QAction(MainWindow);
        action_Save->setObjectName(QString::fromUtf8("action_Save"));
        action_Save_All = new QAction(MainWindow);
        action_Save_All->setObjectName(QString::fromUtf8("action_Save_All"));
        action_Save_as = new QAction(MainWindow);
        action_Save_as->setObjectName(QString::fromUtf8("action_Save_as"));
        action_About = new QAction(MainWindow);
        action_About->setObjectName(QString::fromUtf8("action_About"));
        action_Instructions = new QAction(MainWindow);
        action_Instructions->setObjectName(QString::fromUtf8("action_Instructions"));
        action_change_file_path = new QAction(MainWindow);
        action_change_file_path->setObjectName(QString::fromUtf8("action_change_file_path"));
        action_Clean_Settings = new QAction(MainWindow);
        action_Clean_Settings->setObjectName(QString::fromUtf8("action_Clean_Settings"));
        actionTally_Settings = new QAction(MainWindow);
        actionTally_Settings->setObjectName(QString::fromUtf8("actionTally_Settings"));
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        textEdit = new QTextEdit(centralwidget);
        textEdit->setObjectName(QString::fromUtf8("textEdit"));
        textEdit->setEnabled(true);
        textEdit->setGeometry(QRect(10, 10, 881, 501));
        textEdit->setAcceptRichText(false);
        cleanButton = new QPushButton(centralwidget);
        cleanButton->setObjectName(QString::fromUtf8("cleanButton"));
        cleanButton->setGeometry(QRect(10, 520, 101, 29));
        tallyIntButton = new QPushButton(centralwidget);
        tallyIntButton->setObjectName(QString::fromUtf8("tallyIntButton"));
        tallyIntButton->setGeometry(QRect(120, 520, 101, 29));
        tallyWordButton = new QPushButton(centralwidget);
        tallyWordButton->setObjectName(QString::fromUtf8("tallyWordButton"));
        tallyWordButton->setGeometry(QRect(230, 520, 101, 29));
        findDatesButton = new QPushButton(centralwidget);
        findDatesButton->setObjectName(QString::fromUtf8("findDatesButton"));
        findDatesButton->setGeometry(QRect(340, 520, 101, 29));
        MainWindow->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        MainWindow->setStatusBar(statusbar);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 910, 26));
        menu_File = new QMenu(menubar);
        menu_File->setObjectName(QString::fromUtf8("menu_File"));
        menu_Settings = new QMenu(menubar);
        menu_Settings->setObjectName(QString::fromUtf8("menu_Settings"));
        menu_Help = new QMenu(menubar);
        menu_Help->setObjectName(QString::fromUtf8("menu_Help"));
        MainWindow->setMenuBar(menubar);

        menubar->addAction(menu_File->menuAction());
        menubar->addAction(menu_Settings->menuAction());
        menubar->addAction(menu_Help->menuAction());
        menu_File->addAction(action_Open);
        menu_File->addAction(action_New);
        menu_File->addAction(action_Recently_Opened);
        menu_File->addSeparator();
        menu_File->addAction(action_Save_as);
        menu_File->addAction(action_Save);
        menu_Settings->addAction(action_change_file_path);
        menu_Settings->addSeparator();
        menu_Settings->addAction(action_Clean_Settings);
        menu_Settings->addAction(actionTally_Settings);
        menu_Help->addAction(action_About);
        menu_Help->addSeparator();
        menu_Help->addAction(action_Instructions);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "Data Organiser", nullptr));
        action_Open->setText(QCoreApplication::translate("MainWindow", "&Open", nullptr));
        action_New->setText(QCoreApplication::translate("MainWindow", "&New...", nullptr));
        action_Recently_Opened->setText(QCoreApplication::translate("MainWindow", "&Recent...", nullptr));
        action_Save->setText(QCoreApplication::translate("MainWindow", "&Save", nullptr));
        action_Save_All->setText(QCoreApplication::translate("MainWindow", "&Save All", nullptr));
        action_Save_as->setText(QCoreApplication::translate("MainWindow", "&Save as", nullptr));
        action_About->setText(QCoreApplication::translate("MainWindow", "&About", nullptr));
        action_Instructions->setText(QCoreApplication::translate("MainWindow", "&Instructions", nullptr));
        action_change_file_path->setText(QCoreApplication::translate("MainWindow", "&change file path", nullptr));
        action_Clean_Settings->setText(QCoreApplication::translate("MainWindow", "&Clean Settings", nullptr));
        actionTally_Settings->setText(QCoreApplication::translate("MainWindow", "&Tally Int Settings", nullptr));
        cleanButton->setText(QCoreApplication::translate("MainWindow", "Clean Data", nullptr));
        tallyIntButton->setText(QCoreApplication::translate("MainWindow", "Tally Integers", nullptr));
        tallyWordButton->setText(QCoreApplication::translate("MainWindow", "Tally Words", nullptr));
        findDatesButton->setText(QCoreApplication::translate("MainWindow", "Find Dates", nullptr));
        menu_File->setTitle(QCoreApplication::translate("MainWindow", "&File", nullptr));
        menu_Settings->setTitle(QCoreApplication::translate("MainWindow", "&Settings", nullptr));
        menu_Help->setTitle(QCoreApplication::translate("MainWindow", "&Help", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
