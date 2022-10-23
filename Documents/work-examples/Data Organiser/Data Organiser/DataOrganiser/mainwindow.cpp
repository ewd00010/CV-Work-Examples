#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QtCore>
#include <QFileDialog>
#include <QMessageBox>
#include <QInputDialog>
#include <QLabel>
#include <QPushButton>
#include <iostream>

#define TEXTEDIT  ui -> textEdit
#define TITLECHANGE(x, y) this->setWindowTitle("DataOrganiser: " + x.remove(0,y))
#define HOMEPATH QDir::homePath()
#define TEMPFILEPATH HOMEPATH + "/Desktop/QT stuff/Data Organiser/DataOrganiser/Temporary.txt"

QString fileInUse = TEMPFILEPATH;
QString userFilePath = HOMEPATH + "/Documents/UserFiles";
QString userDefinedClean;
QString userDefinedInsert;

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
/*
 * programmer defined methods
 */
QString MainWindow::returnFileText(QString dir) {
    QFile file(dir);
    file.open(QIODevice::ReadOnly | QIODevice::Text);
    QTextStream in(&file);
    QString doc = in.readAll();
    file.close();
    return doc;
}

void MainWindow::performSave(QString dir) {
    QFile file(dir);
    file.open(QIODevice::ReadWrite | QIODevice::Truncate);
    QTextStream edit(&file);
    edit << TEXTEDIT -> toPlainText();
    file.close();
}

/*
 * menu buttons
 * File
 */
void MainWindow::on_action_New_triggered()
{
    TEXTEDIT -> clear();
    TEXTEDIT -> insertHtml(returnFileText(fileInUse));
}

void MainWindow::on_action_Save_as_triggered()
{
    QFileDialog dialog(this);
    fileInUse = dialog.getSaveFileName(this, "Save File As", userFilePath, ".txt (*.txt)");
    performSave(fileInUse);
    QString temp = fileInUse;
    TITLECHANGE(temp, 31);
}


void MainWindow::on_action_Save_triggered()
{
    performSave(fileInUse);
}


void MainWindow::on_action_Open_triggered()
{
    QFileDialog dialog(this);
    fileInUse = dialog.getOpenFileName(this, "Open File As", userFilePath, ".txt (*.txt)");

    TEXTEDIT -> clear();
    TEXTEDIT -> insertHtml(returnFileText(fileInUse));

    QString temp = fileInUse;
    TITLECHANGE(temp, 31);
}
/*
 * Settings
 */
void MainWindow::on_action_change_file_path_triggered()
{
    QLabel *textLabel = new QLabel(this);
    textLabel->setFrameStyle(QFrame::StyledPanel | QFrame::Raised);
    QPushButton *textButton = new QPushButton(userFilePath);
    bool ok;

    QString text = QInputDialog::getText(this, userFilePath,
                                             "New Path:", QLineEdit::Normal,
                                             QDir::home().dirName(), &ok);

    if (ok && !text.isEmpty()) {
        userFilePath = text;
    }
}

void MainWindow::on_action_Clean_Settings_triggered()
{
    QLabel *textLabel = new QLabel(this);
    textLabel->setFrameStyle(QFrame::StyledPanel | QFrame::Raised);
    QPushButton *textButton = new QPushButton(userFilePath);
    bool ok;
    bool okk;

    QString cleanText = QInputDialog::getText(this, "Current regex:" + userDefinedClean,
                                             "New Regex:", QLineEdit::Normal, "", &ok);
    QString insertText = QInputDialog::getText(this, "Current regex:" + userDefinedInsert,
                                               "New value:", QLineEdit::Normal, "", &okk);
    if (ok && okk && !cleanText.isEmpty()) {
        userDefinedClean = cleanText;
        userDefinedInsert = insertText;
    }
}

void MainWindow::on_actionTally_Settings_triggered()
{

}

/*
 * Help
 */
void MainWindow::on_action_About_triggered()
{

}


void MainWindow::on_action_Instructions_triggered()
{

}

/*
 * on screen buttons
 */
void MainWindow::on_cleanButton_clicked()
{
    QString text = TEXTEDIT -> toPlainText();
    QString search;
    text.replace("  ", " "); text.replace("   ", " "); text.replace("    ", " "); // this should be user customisable

    if(userDefinedClean != "") {
        QRegularExpression check1(userDefinedClean);
        QRegularExpressionMatchIterator matchs = check1.globalMatch(text);
        while(matchs.hasNext()) {
            QRegularExpressionMatch match = matchs.next();
            search = match.captured(0);
            text.replace(search, userDefinedInsert);
        }
    }

    TEXTEDIT -> clear();
    TEXTEDIT -> insertHtml(text);
}


void MainWindow::on_tallyIntButton_clicked()
{
    QString resultant;
    QString text = " " + TEXTEDIT -> toPlainText() + " ";
    text.replace("^\\n$", " ");
    QMessageBox tallyIntPopup;
    int counter = 0;

    for(int i = 0; i < 9999999; i++){ // this should be user customisable
        if(text.contains(" " + QString::number(i) + " ")) {
            resultant.append(QString::number(i) + ": ");
            resultant.append(QString::number(text.count(" " + QString::number(i) + " ")) + "    ");
            if(counter % 3 == 2) {
                resultant.append("\n");
            }
            counter++;
        }
    }

    tallyIntPopup.setText(resultant);
    tallyIntPopup.exec();
}


void MainWindow::on_tallyWordButton_clicked()
{
    QString text = TEXTEDIT -> toPlainText();
    text.remove("^\\n$");
    QList<QString> words = text.split(" ");
    QVector<QString> temp;
    QString resultant;
    QMessageBox tallyWordPopup;
    int counter = 0;

    for(QString word : words) {
        if(!temp.contains(word)) {
            resultant.append(word + ": ");
            resultant.append(QString::number(words.count(word)) + "    ");
            if(counter % 3 == 2){
                resultant.append("\n");
            }
            counter++;
            temp.append(word);
        }
    }

    tallyWordPopup.setText(resultant);
    tallyWordPopup.exec();
}


void MainWindow::on_findDatesButton_clicked()
{
    QString text = TEXTEDIT -> toPlainText();
    text.replace("\n", " ");
    QRegularExpression check1("(\\d\\d)/(\\d\\d)/(\\d\\d\\d\\d)|(\\d\\d)-(\\d\\d)-(\\d\\d\\d\\d)|(\\d\\d) (\\d\\d) (\\d\\d\\d\\d)");
    QRegularExpressionMatchIterator matchs = check1.globalMatch(text);
    QString resultant;
    int counter = 0;
    QMessageBox findDatesPopup;
    QString date;

    while(matchs.hasNext()) {
        QRegularExpressionMatch match = matchs.next();
        date = match.captured(0);
        resultant.append("<" + date + "> ");
        if(counter % 3 == 2){
            resultant.append("\n");
        }
        counter++;
    }
    resultant.append("\nTotal Amount Of Dates: " + QString::number(counter));

    findDatesPopup.setText(resultant);
    findDatesPopup.exec();
}
