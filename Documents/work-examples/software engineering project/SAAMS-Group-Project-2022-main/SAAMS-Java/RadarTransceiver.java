
// Generated by Together


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * An interface to SAAMS:
 * Radar tracking of arriving and departing aircraft, and transceiver for downloading of flight descriptors
 * (by aircraft entering the local airspace) and uploading of passenger lists (to aircraft about to depart).
 * A screen simulation of the radar/transceiver system.
 * This class is a controller for the AircraftManagementDatabase: it sends messages to notify when a new aircraft is detected
 * (message contains a FlightDescriptor), and when radar contact with an aircraft is lost.
 * It also registers as an observer of the AircraftManagementDatabase, and is notified whenever any change occurs in that <<model>> element.
 * See written documentation.
 *
 * @stereotype boundary/view/controller
 * @url element://model:project::SAAMS/design:view:::idwwyucko4qme4cko4sgxi
 * @url element://model:project::SAAMS/design:node:::id15rnfcko4qme4cko4swib.node107
 * @url element://model:project::SAAMS/design:node:::id3oolzcko4qme4cko4sx40.node167
 * @url element://model:project::SAAMS/design:view:::id3oolzcko4qme4cko4sx40
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 */
public class RadarTransceiver extends JFrame implements Observer, ActionListener, ListSelectionListener {
    /**
     * The Radar Transceiver interface has access to the AircraftManagementDatabase.
     *
     * @clientCardinality 1
     * @supplierCardinality 1
     * @label accesses/observes
     * @directed
     */
    private AircraftManagementDatabase AMDatabase;
    private JPanel MainFormPanel;
    private JTextField txt_flightCode;
    private JTextField txt_flightTo;
    private JTextField txt_nextStop;
    private JTextField txt_passengerName;
    private JTextField txt_flightFrom;
    private JLabel lblFlightTo;
    private JLabel lblFlightCode;
    private JLabel lblFlightFrom;
    private JLabel lblNextStop;
    private JLabel lblPassengerName;

    private JList addedPassengersList;

    private JButton btn_AddPassenger;

    private JButton btn_DetectFlight;


    private JList currentPlanesList;
    private JButton btn_LeaveAirspace;
    private JLabel lblCurrentPlanes;
    private JLabel lblPassengers;
    private JList otherPassengersList;
    private JPanel rootPanel;
    private JLabel lbl_addedPassengers;
    PassengerList passengerList = new PassengerList();
    boolean atLeastOnePassenger = false;

    public RadarTransceiver(AircraftManagementDatabase amd) {
        super("Radar Trasceiver");
        this.AMDatabase = amd;
        amd.addObserver(this); //RadarTransceiver is an observer of AMD

        btn_DetectFlight.addActionListener(this);
        btn_AddPassenger.addActionListener(this);
        btn_LeaveAirspace.addActionListener(this);

        currentPlanesList.addListSelectionListener(this); //will call valueChanged

        add(rootPanel);
        setVisible(true);

    }

    @Override
    /**
     * Called when the user selects something in the flightList
     * This method is the implementation of ListSelectionListener.
     */
    public void valueChanged(ListSelectionEvent e) {
        updateOtherPassengerListGUI();
    }

    /**
     * Utility function for handling GUI events based on fields being populated.
     * It takes functions as arguments, which should be written as () -> {body;}
     * It also manages showing that the fields are empty (red frame)
     * @param thenDo function to be executed if the fields are ALL filled
     * @param elseDo function executed when even 1 field is empty
     * @param fields variable amount of arguments, the text fields to be checked
     */
    public void labelEmptyHandler(Runnable thenDo, Runnable elseDo, JTextField... fields) {
        boolean areAllFieldsFilled = true;
        for (JTextField field: fields) {
            areAllFieldsFilled &= !(field.getText().isEmpty());
            field.setBorder(new LineBorder(Color.black, 1));
        }

        if (areAllFieldsFilled)
            thenDo.run();
        else {
            elseDo.run();
            //turn red all the fields that were required
            for (JTextField field : fields)
                field.setBorder(new LineBorder(Color.red , 3));
        }
    }

    /**
     * checks if the given Itinerary is valid
     * @param itinerary the itinerary to be validated
     * @return true if the itinerary is valid
     */
    public boolean validateItinerary(Itinerary itinerary) {
        return true; //TODO what should be checked in an itinerary?
        //from != to, from != next
    }

    public void clearInputs() {
        txt_flightCode.setText("");
        txt_flightTo.setText("");
        txt_flightFrom.setText("");
        txt_nextStop.setText("");
        txt_passengerName.setText("");
    }

    @Override
    /**
     * Activates when an action is performed on any element of the form
     */
    public void actionPerformed(ActionEvent e) {
        //DETECT FLIGHT VALIDATION
        if (e.getSource() == btn_DetectFlight) {
            labelEmptyHandler(
                () -> { //then
                    Itinerary newItinerary = new Itinerary(txt_flightFrom.getText(), txt_flightTo.getText(), txt_nextStop.getText());
                    PassengerList passengersOfNewFlight = new PassengerList(passengerList);
                    if (!passengerList.isEmpty()) {

                        if (validateItinerary(newItinerary)) {
                            System.out.println("Itinerary is valid");
                            radarDetect(txt_flightCode.getText(), newItinerary, passengersOfNewFlight);
                            //when the button is clicked, all the fields should be emptied
                            passengerList.clear(); //empty the temporary passengerList
                            clearInputs();
                            updateGUI();
                        } else {
                            JOptionPane.showMessageDialog(rootPanel,
                                    "The provided itinerary is invalid",
                                    "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                },
                () -> { //else
                    JOptionPane.showMessageDialog(rootPanel,
                            "The fields \"Flight code\", \"Next stop\" and \"Destination\" are required",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                },
                txt_flightTo, txt_flightCode, txt_nextStop
            );
        }
        //TODO: ADD PASSENGER VALIDATION
        if (e.getSource() == btn_AddPassenger) {
            labelEmptyHandler(
                    () -> {
                        PassengerDetails passenger = new PassengerDetails(txt_passengerName.getText());
                        passengerList.addPassenger(passenger);
                        updateAddedPassengersListGUI();
                        txt_passengerName.setText("");
                    },
                    () -> {
                        JOptionPane.showMessageDialog(rootPanel,
                                "Passenger list cannot be empty",
                                "Warning", JOptionPane.WARNING_MESSAGE);
                    },
                    txt_passengerName
            );
        }

        if(e.getSource() == btn_LeaveAirspace)
        {
            if (currentPlanesList.isSelectionEmpty()) {
                JOptionPane.showMessageDialog(rootPanel,
                        "Select an aircraft that will leave the airspace",
                        "Cannot lose contact", JOptionPane.WARNING_MESSAGE);
            }
            else {
                String flightCode = (String)currentPlanesList.getSelectedValue();
                int currentMCode = AMDatabase.getMCodeFromFlightCode(flightCode);
                AMDatabase.radarLostContact(currentMCode);
            }
        }

    }


    /**
     * when an aircraft is detected in the local airspace
     * (ie when the user inputs flight info in the interface and presses a button)
     * this method is called to alert the AMD, so that a new MR is allocated
     * whether the new aircraft is just passing or wanting to land.
     *
     * @param flightCode flight code of the aircraft
     * @param itinerary  itinerary of the aircraft
     * @param list       list of passengers in the aircraft
     */
    public void radarDetect(String flightCode, Itinerary itinerary, PassengerList list) {
        FlightDescriptor newFD = new FlightDescriptor(flightCode, itinerary, list);
        AMDatabase.radarDetect(newFD);
    }

    public void updateGUI() {
        updateAddedPassengersListGUI();
        updateFlightsList();
        updateOtherPassengerListGUI();

        validate();
        repaint();

    }

    public void updateAddedPassengersListGUI() {
        this.addedPassengersList.removeAll();
        String[] passengerListAsString = passengerList.asStringArray();
        addedPassengersList.setListData(passengerListAsString);

        addedPassengersList.validate();
        addedPassengersList.repaint();
    }

    public void updateFlightsList() {
        currentPlanesList.removeAll();
        ManagementRecord[] relevantMRs = AMDatabase.getNonFreeMRs();
        String flightCodes[] = new String[relevantMRs.length];
        for (int i=0; i<relevantMRs.length; i++)
            flightCodes[i] = relevantMRs[i].getFlightCode();

        currentPlanesList.setListData(flightCodes);

        //doesn't need to be repainted, if it's called then the entire window would have to be repainted
    }

    public void updateOtherPassengerListGUI() {
        System.out.println("called otherPassengerListGUI");
        String[] passengerNames;
        if (currentPlanesList.isSelectionEmpty())
            passengerNames = new String[]{};
        else {
            System.out.println("There is a selection");
            String flightCode = (String) currentPlanesList.getSelectedValue();
            int mCode = AMDatabase.getMCodeFromFlightCode(flightCode);
            PassengerList passengers = AMDatabase.getMR(mCode).getPassengerList();
            String[] passengerStrings = passengers.asStringArray();
            otherPassengersList.setListData(passengerStrings);
        }
        otherPassengersList.validate();
        otherPassengersList.repaint();
    }


    /**
     * called when AMD receives an update, not sure wheter anything should happen
     *
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        updateGUI();
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
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        MainFormPanel = new JPanel();
        MainFormPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        rootPanel.add(MainFormPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), -1, -1));
        MainFormPanel.add(panel1);
        lblFlightTo = new JLabel();
        lblFlightTo.setText("Flight to:");
        panel1.add(lblFlightTo, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblFlightFrom = new JLabel();
        lblFlightFrom.setText("Flight from:");
        panel1.add(lblFlightFrom, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblNextStop = new JLabel();
        lblNextStop.setText("Next stop:");
        panel1.add(lblNextStop, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblPassengerName = new JLabel();
        lblPassengerName.setText("Passenger name:");
        panel1.add(lblPassengerName, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblFlightCode = new JLabel();
        lblFlightCode.setEnabled(true);
        lblFlightCode.setText("Flight Code:");
        panel1.add(lblFlightCode, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txt_flightCode = new JTextField();
        panel1.add(txt_flightCode, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txt_flightFrom = new JTextField();
        panel1.add(txt_flightFrom, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txt_flightTo = new JTextField();
        panel1.add(txt_flightTo, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txt_nextStop = new JTextField();
        panel1.add(txt_nextStop, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txt_passengerName = new JTextField();
        panel1.add(txt_passengerName, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addedPassengersList = new JList();

        panel1.add(addedPassengersList, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 5, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        btn_AddPassenger = new JButton();
        btn_AddPassenger.setText("Add Passenger");
        panel1.add(btn_AddPassenger, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btn_DetectFlight = new JButton();
        btn_DetectFlight.setText("Detect Flight");
        panel1.add(btn_DetectFlight, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblCurrentPlanes = new JLabel();
        lblCurrentPlanes.setText("Current Planes");
        panel1.add(lblCurrentPlanes, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblPassengers = new JLabel();
        lblPassengers.setText("Passengers");
        panel1.add(lblPassengers, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentPlanesList = new JList();
        panel1.add(currentPlanesList, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        btn_LeaveAirspace = new JButton();
        btn_LeaveAirspace.setText("Leave Airspace");
        panel1.add(btn_LeaveAirspace, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        otherPassengersList = new JList();
        panel1.add(otherPassengersList, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        MainFormPanel.add(spacer3);
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(30, -1), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(30, -1), null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 15), null, null, 0, false));

        rootPanel.setFocusCycleRoot(true);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
