
// Generated by Together


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * An interface to SAAMS:
 * Maintenance Inspector Screen:
 * Inputs events from the Maintenance Inspector, and displays aircraft information.
 * This class is a controller for the AircraftManagementDatabase: sending it messages to change the aircraft status information.
 * This class also registers as an observer of the AircraftManagementDatabase, and is notified whenever any change occurs in that <<model>> element.
 * See written documentation.
 *
 * @stereotype boundary/view/controller
 * @url element://model:project::SAAMS/design:node:::id4tg7xcko4qme4cko4swuu.node146
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 * @url element://model:project::SAAMS/design:view:::id4tg7xcko4qme4cko4swuu
 * @url element://model:project::SAAMS/design:node:::id15rnfcko4qme4cko4swib.node107
 * @url element://model:project::SAAMS/design:view:::id3y5z3cko4qme4cko4sw81
 */

public class MaintenanceInspector extends JFrame implements Observer, ActionListener, ListSelectionListener {
    /**
     * The Maintenance Inspector Screen interface has access to the AircraftManagementDatabase.
     *
     * @clientCardinality 1
     * @supplierCardinality 1
     * @label accesses/observes
     * @directed
     */
    private AircraftManagementDatabase AMDatabase;

    private JLabel lblTitle;
    private JPanel MainFormPanel;
    private JLabel lblFlightCode;
    private JLabel lblPassengers;
    private JButton reportFaultsButton;
    private JButton completeButton;
    private JTextArea commentArea;
    private JPanel rootPanel;
    private JLabel lbl_currStatus;
    private JLabel lbl_status;
    private JLabel lbl_currLocation;
    private JLabel lbl_location;
    private JList flightList;
    private JTable table;
    private String[] tableHeaders = {"Flights"};


    public MaintenanceInspector(AircraftManagementDatabase AMDatabase) {
        super("Maintenance Supervisor Screen");

        //Database Handling
        this.AMDatabase = AMDatabase;
        AMDatabase.addObserver(this);

        //GUI Handling
        setupGUI();
    }

    public void completeRepair(int mCode) {
        AMDatabase.getMR(mCode).completeRepair();
    }


    public void setupGUI() {
        add(rootPanel);
        setVisible(true);
        clearInterface();

        reportFaultsButton.addActionListener(this);
        completeButton.addActionListener(this);

        //TODO are these necessary? vvv
        setSize(500, 335);
        setLocation(0, 40);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblTitle.setText("Maintenance Inspector");
        MainFormPanel = new JPanel();
        MainFormPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(MainFormPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        MainFormPanel.add(panel2);
        lblFlightCode = new JLabel();
        lblFlightCode.setEnabled(true);
        lblFlightCode.setText("Maintenance(s) required");
        panel2.add(lblFlightCode, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblPassengers = new JLabel();
        lblPassengers.setText("Comment");
        panel2.add(lblPassengers, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JList list1 = new JList();
        panel2.add(list1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(150, 150), new Dimension(150, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(30, -1), null, null, 0, false));
        commentArea = new JTextArea();
        panel2.add(commentArea, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        reportFaultsButton = new JButton();
        reportFaultsButton.setText("Report faults");
        panel2.add(reportFaultsButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        completeButton = new JButton();
        completeButton.setText("Complete");
        panel2.add(completeButton, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(30, -1), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(30, -1), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    public String getMRFlightInfo(ManagementRecord mr) {
        return mr.getFlightCode();
    }


    private void updateGUI() {
        updateFlightList();
        validate();
        repaint();
    }

    private void updateFlightList() {
        ManagementRecord MR = new ManagementRecord();
        ManagementRecord[] relevantMRs =
                AMDatabase.getWithStatuses(MR.READY_CLEAN_AND_MAINT, MR.CLEAN_AWAIT_MAINT, MR.OK_AWAIT_CLEAN,
                        MR.READY_REFUEL, MR.AWAIT_REPAIR, MR.FAULTY_AWAIT_CLEAN);

        String[] listData = new String[relevantMRs.length];
        for (int i=0;i<relevantMRs.length; i++)
            listData[i] = getMRFlightInfo(relevantMRs[i]);

        flightList.setListData(listData);
        flightList.validate();
        flightList.repaint();
    }


    @Override
    public void update(Observable o, Object arg) {
        updateGUI();
    }

    public void warningMessage(String warning) {
        JOptionPane.showMessageDialog(rootPanel,
                warning,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }


    public void controls() {
        reportFaultsButton.addActionListener(this);
        completeButton.addActionListener(this);
    }

    public void clearInterface() {
        lbl_location.setText("");
        lbl_status.setText("");
        commentArea.setText("");
    }


    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("MI.actionPerformed");
        boolean isFlightSelected = !flightList.isSelectionEmpty();
        String flightCode = "no flight code available";
        int mCode = -1;
        int statusOfAircraft = -1;
        System.out.println("the flightIsSelected = "+isFlightSelected);
        if (isFlightSelected) {
            flightCode = (String) flightList.getSelectedValue();
            mCode = AMDatabase.getMCodeFromFlightCode(flightCode);
            statusOfAircraft = AMDatabase.getStatus(mCode);
        }

        if (actionEvent.getSource() == reportFaultsButton) {
            System.out.println("Clicked reportFaults");
            if (!isFlightSelected) {
                warningMessage("Select the aircraft to which the fault will be reported");
                return;
            }
            if (statusOfAircraft != ManagementRecord.READY_CLEAN_AND_MAINT) {
                warningMessage("Faults can only be reported for an aircraft waiting for cleaning and maintenance");
                return;
            }

            String desc = commentArea.getText();
            AMDatabase.faultsFound(mCode, desc);
        }
        if (actionEvent.getSource() == completeButton) {
            if (!isFlightSelected) {
                warningMessage("Selected which aircraft has been cleaned and checked for faults, now being ready to depart again");
                return;
            }

            completeRepair(mCode);
        }
        //TODO carry out repairs and return to READY_CLEAN_AND_MAINT state.  New "Repair" button?
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (flightList.isSelectionEmpty()) {
            lbl_location.setText("");
            lbl_status.setText("");
            commentArea.setText("");
        }
        else {
            String flightCode = (String) flightList.getSelectedValue();
            int mCode = AMDatabase.getMCodeFromFlightCode(flightCode);
            int status = AMDatabase.getStatus(mCode);

            String locationString = "At gate "+AMDatabase.getMR(mCode);
            String statusString = AMDatabase.getMR(mCode).getStatusAsString();
        }
    }
}
