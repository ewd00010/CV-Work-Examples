import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// Generated by Together


/**
 * An interface to SAAMS:
 * A Ground Operations Controller Screen:
 * Inputs events from GOC (a person), and displays aircraft and gate information.
 * This class is a controller for the GateInfoDatabase and the AircraftManagementDatabase: sending them messages to change the gate or aircraft status information.
 * This class also registers as an observer of the GateInfoDatabase and the AircraftManagementDatabase, and is notified whenever any change occurs in those <<model>> elements.
 * See written documentation.
 *
 * @stereotype boundary/view/controller
 * @url element://model:project::SAAMS/design:node:::id2wdkkcko4qme4cko4svm2.node36
 * @url element://model:project::SAAMS/design:view:::id2wdkkcko4qme4cko4svm2
 * @url element://model:project::SAAMS/design:view:::id1un8dcko4qme4cko4sw27
 * @url element://model:project::SAAMS/design:view:::id1bl79cko4qme4cko4sw5j
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 * @url element://model:project::SAAMS/design:node:::id15rnfcko4qme4cko4swib.node107
 */
public class GOC extends JFrame implements ActionListener, Observer, ListSelectionListener {
	/**
	 * The Ground Operations Controller Screen interface has access to the GateInfoDatabase.
	 *
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 * @label accesses/observes
	 * @directed
	 */
	private GateInfoDatabase GIDatabase;
	/**
	 * The Ground Operations Controller Screen interface has access to the AircraftManagementDatabase.
	 *
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 * @label accesses/observes
	 * @directed
	 */
	private AircraftManagementDatabase AMDatabase;

	int[] flightListItem;
	Vector<String> flightCodes;

	//TODO these labels won't appear in the code until the UI is designed.
	JLabel grantGroundLbl;
	JLabel taxiToLbl;
	JLabel grantRunwayLbl;
	JLabel statusesAreaLbl;
	JLabel planeCodesLbl;
	JLabel planeDetailsLbl;
	JLabel selectMCodeLbl;

	JTextArea statusesArea; // list of all gate statuses; the highlighted selection will be used in "Taxi to gate".
	JScrollPane statusScroll;
	JTextArea planeCodes; //getFlightCode()
	JTextField selectMCode; // mCode is derived from this text field

	public GOC(GateInfoDatabase GIDatabase, AircraftManagementDatabase AMDatabase) {
		//observe AMD
		this.AMDatabase = AMDatabase;
		AMDatabase.addObserver(this);

		//observe GID
		this.GIDatabase = GIDatabase;
		GIDatabase.addObserver(this);

		//setup GUI
		add(rootPanel);
		setVisible(true);
		setTitle("Gate Controller");
		setSize(500, 350);
		setLocation(525, 40);
		//link GUI
		controls();

		//sets the GUI to be empty, because the AMD shouldn't have planes that appear here right away
		updateGUI();

		/** only needed for Ewan's implementation
		flightListItem = AMDatabase.getWithStatus(2);
		flightCodes = AMDatabase.getFlightCodes(flightListItem);
		flightList.setListData(flightCodes);

		int[] gateStatuses = GIDatabase.getStatuses();
		String[] stringGateStatuses = getGateStatusAsStringList(gateStatuses);
		gateList.setListData(stringGateStatuses);

		 **/
	}

	private JList<String> flightList;
	private JButton grantGroundClearanceButton;
	private JButton taxiToGateButton;
	private JButton grantRunway;
	private JList<String> planeDetails;
	private JList<String> gateList;
	private JPanel rootPanel;

	/**
	 * refreshes the list of gates
	 */
	public void updateGatesList() {
		int gateStatusesInts[] = GIDatabase.getStatuses();
		String gateStatusItems[] = new String[gateStatusesInts.length];
		for (int i=0;i<gateStatusesInts.length; i++)
			gateStatusItems[i] = "Gate "+(i+1)+": "+getGateStatusAsString(gateStatusesInts[i]);

		gateList.setListData(gateStatusItems);
		repaintComponent(gateList);
	}

	/**
	 * gets the status of a given gate, as a String
	 * @param gateNumber gate number, 0 or 1
	 * @return a capitalized string
	 */
	public String getGateStatusAsString(int gateNumber) {
		int status = GIDatabase.getStatus(gateNumber);
		String result;

		switch(status) {
			case 0: {result = "FREE";break;}
			case 1: {result = "RESERVED";break;}
			case 2: {result = "OCCUPIED";break;}
			default: {result = "ERROR";break;}
		}
		return result;
	}

	//ED, this has to be kept
	// GOC's role as a controller
	// Loads buttons, but does not draw them onto the UI (as of 16/3).
	public void controls() {
		grantGroundClearanceButton.setActionCommand("grant ground");
		grantGroundClearanceButton.addActionListener(this);

		taxiToGateButton.setActionCommand("taxi");
		taxiToGateButton.addActionListener(this);

		grantRunway.setActionCommand("grant runaway");
		grantRunway.addActionListener(this);

		statusesArea = new JTextArea();
		statusesArea.setEditable(false);
		statusScroll = new JScrollPane(statusesArea);

		flightList.addListSelectionListener(this);
	}

	/**
	 * repaints a component that was just changed
	 * @param component a JComponent, usually a JList
	 */
	public void repaintComponent(JComponent component) {
		component.validate();
		component.repaint();
	}

	/**
	 * returns a report of a given flight
	 * @param mCode mCode of the flight in question
	 * @return an array of strings, {flightCode, Status, Itinerary, from, to, next}, they are labeled
	 */
	public String[] getPlaneDetails(int mCode) {
		ManagementRecord mr = AMDatabase.getMR(mCode);
		FlightDescriptor flightDescriptor = mr.getFlightDescriptor();
		String flightCode = mr.getFlightCode();
		String statusAsString = mr.getStatusAsString();
		String from = mr.getItinerary().getFrom();
		String to = mr.getItinerary().getTo();
		String next = mr.getItinerary().getNext();
		return new String[]{"Code:"+flightCode,
							"Status:"+statusAsString,
							"Itinerary:",
								"  From: "+from,
								"  To: "+ to,
								" Next stop: "+next};
	}

	/**
	 * fetches the flights and updates the JList
	 */
	public void updateFlightList() {
		flightListItem = AMDatabase.getWithStatus(ManagementRecord.WANTING_TO_LAND);
		flightCodes = AMDatabase.getFlightCodes(flightListItem);
		flightList.setListData(flightCodes);

		repaintComponent(flightList);
	}

	/**
	 * fetches the flight info and updates the JList
	 * If no flight is selected, it sets it to be empty
	 */
	public void updatePlaneInfo() {
		String[] listData;
		if (flightList.isSelectionEmpty())
			listData = new String[]{};
		else {
			System.out.println("updateFlightInfo, there is a selection");
			String flightCode = flightList.getSelectedValue();
			int mCode = AMDatabase.getMCodeFromFlightCode(flightCode);
			String[] flightInfo = getPlaneDetails(mCode);
			planeDetails.setListData(flightInfo);
		}

		repaintComponent(planeDetails);
	}

	/**
	 * updates the entire window
	 */
	public void updateGUI() {
		updateFlightList();
		updatePlaneInfo();
		updateGatesList();

		validate();
		repaint();
	}

	/**
	 * called when there is an update in GID or AMD
	 * @param o the object that had a change, AMD or GID
	 * @param arg not used
	 */
	public void update(Observable o, Object arg) {
		updateGUI();
	}

	/**
	 * creates a warning message window
	 * @param warning the text of the warning message
	 */
	public void warningMessage(String warning) {
		JOptionPane.showMessageDialog(rootPanel,
				warning,
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}


	/**
	 * returns the gateNumbers of the free Gates
	 * @return an ArrayList<Integer>, empty if there are no free gates
	 */
	public ArrayList<Integer> freeGates() {
		int gateAmount = GIDatabase.maxGateNumber;
		ArrayList<Integer> availableGates = new ArrayList<>();
		for (int i=0;i<gateAmount;i++) {
			if (GIDatabase.getStatus(i) == Gate.FREE)
				availableGates.add((Integer) i);
		}

		return availableGates;
	}

	/**
	 * grants ground clearance to an aircraft, putting it to the GROUND_CLEARANCE_GRANTED state
	 * @param mCode mCode of the aircraft that will get ground clearance
	 */
	public void grantGroundClearance(int mCode) {
		if (AMDatabase.getStatus(mCode) == ManagementRecord.WANTING_TO_LAND) {
			System.out.println("GOC grants ground clearance...");
			AMDatabase.setStatus(mCode, ManagementRecord.GROUND_CLEARANCE_GRANTED);
		}
		else {
			warningMessage("Could not grant ground clearance to flight "+AMDatabase.getFlightCode(mCode)+", has to be in state \"Wanting to land\"");
			System.out.println("GOC could not grant ground clearance");
		}

	}

	/**
	 * sends the command to an aircraft to taxi to a gate
	 * @param mCode mCode of the aircraft that will taxi
	 * @param gateNumber gateNumber (0, 1..) where the aircraft taxoes to
	 */
	public void taxiTo(int mCode, int gateNumber) {
		if (AMDatabase.getStatus(mCode) == ManagementRecord.LANDED) {
			System.out.println("GOC orders taxi to gate: " + gateNumber);
			AMDatabase.taxiTo(mCode, gateNumber);
			GIDatabase.allocate(gateNumber, mCode);
		}
		else {
			warningMessage("Could not taxi the aircraft "+AMDatabase.getFlightCode(mCode)+", needs to be in the state \"Landed\"");
			System.out.println("GOC could not request taxiing");
		}
	}

	/**
	 * grants Runway permission to the aircraft
	 * @param mCode mCode of the aircraft
	 */
	public void grantRunway(int mCode) {
		if (AMDatabase.getStatus(mCode) == ManagementRecord.AWAITING_TAXI) {
			System.out.println("GOC grants taxi runway clearance...");
			AMDatabase.setStatus(mCode, ManagementRecord.AWAITING_TAKEOFF);
		}
		else {
			warningMessage("Could not grant runway taxi permission to aircraft "+AMDatabase.getFlightCode(mCode)+", needs to be in the state \"Awaiting taxi\"");
			System.out.println("GOC could not given runway taxi permission");
		}
	}


	//By ED, being modified by GC
	// Performs actions resulting from button presses.
	public void actionPerformed(ActionEvent e) {
		boolean isFlightSelected = !flightList.isSelectionEmpty();
		String flightCode = isFlightSelected ? flightList.getSelectedValue() : "Error";
		int mCode = isFlightSelected ? AMDatabase.getMCodeFromFlightCode(flightCode) : -1;

		int noGateFree = GIDatabase.getMaxGateNumber() + 1;
		int gateNumber = noGateFree;

		if (e.getActionCommand().equals("grant ground")) {
			if (isFlightSelected)
				grantGroundClearance(mCode);
			else
				warningMessage("Select the flight which will get ground clearance");
		}

		if (e.getActionCommand().equals("taxi")) {
			if (isFlightSelected) {
				if (gateList.isSelectionEmpty()) {
					ArrayList<Integer> freeGates = freeGates();
					if (freeGates.isEmpty())
						warningMessage("There are no gates available to taxi to");
					else {
						int selectedGate = gateList.getSelectedIndex();
						if (selectedGate < 0)
							warningMessage("there is no gate selected");
						else
							taxiTo(mCode, selectedGate);  //TODO: should it automatically find the first available gate?, it would do taxiTo(mCode, availableGates.get(0))
					}
				} else
					warningMessage("Select a gate to taxi to");
			}
			else
				warningMessage("Select the flight that will taxi to a gate");
		}

		if (e.getActionCommand().equals("grant runaway")) {
			if (isFlightSelected)
				grantRunway(mCode);
			else
				warningMessage("No flight selected to grant runway to");
		}

		updateGUI();
	}


	//ED
	// GOC's role as an observer
	// Updates non-editable text areas.
	public void notifications() {
		for (int i = 0; i < GIDatabase.getStatuses().length; i++) {
			statusesArea.setText(GIDatabase.getStatus(i) + "\n"); // should have 2 inputs
		}
	}


	//ED
	public String[] getGateStatusAsStringList(int[] statuses) {
		String[] statusAsString = new String[statuses.length];
		for (int i = 0; i < statuses.length; i++) {
			if (statuses[i] == 0) {
				statusAsString[i] = "FREE";
			} else if (statuses[i] == 1) {
				statusAsString[i] = "RESERVED";
			} else {
				statusAsString[i] = "OCCUPIED";
			}
		}
		return statusAsString;
	}

	//this method was implemented by ED, was broken down by GC and is now update();
	public void ED_update(Observable o, Object arg) {
		flightListItem = AMDatabase.getWithStatus(2);
		flightCodes = AMDatabase.getFlightCodes(flightListItem);
		flightList.setListData(flightCodes);

		Vector<String> planeDetails = new Vector<>();
		for (int i = 0; i < flightCodes.size(); i++) {
			if (flightList.isSelectedIndex(i)) {
				planeDetails.add(AMDatabase.getMR(AMDatabase.getStatus(flightListItem[i])).getFlightCode());
				planeDetails.add(AMDatabase.getMR(AMDatabase.getStatus(flightListItem[i])).getStatusAsString());
				planeDetails.add(AMDatabase.getMR(AMDatabase.getStatus(flightListItem[i])).getItinerary().getTo());
				planeDetails.add(AMDatabase.getMR(AMDatabase.getStatus(flightListItem[i])).getItinerary().getFrom());
				planeDetails.add(AMDatabase.getMR(AMDatabase.getStatus(flightListItem[i])).getItinerary().getNext());
				this.planeDetails.setListData(planeDetails);
				// from mr call flightcode itenerary and passengerList
			} else {
				planeDetails.clear();
				this.planeDetails.setListData(planeDetails);
			}
		}

		int[] gateStatuses = GIDatabase.getStatuses();
		String[] stringGateStatuses = getGateStatusAsStringList(gateStatuses);
		gateList.setListData(stringGateStatuses);
		validate();
		repaint();
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
		final JLabel label1 = new JLabel();
		label1.setText("Ground Operations Controls");
		rootPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
		rootPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 25), null, null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
		rootPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 25), null, null, 0, false));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(12, 2, new Insets(0, 0, 0, 0), -1, -1));
		rootPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Planes");
		panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		flightList = new JList();
		panel1.add(flightList, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 5, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		gateList = new JList();
		panel1.add(gateList, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Inbound");
		panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		grantGroundClearanceButton = new JButton();
		grantGroundClearanceButton.setText("Grant ground clearance");
		panel1.add(grantGroundClearanceButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		taxiToGateButton = new JButton();
		taxiToGateButton.setText("Taxi to gate");
		panel1.add(taxiToGateButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setText("Outbound");
		panel1.add(label4, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		grantRunway = new JButton();
		grantRunway.setText("Grant taxi runway clearance");
		panel1.add(grantRunway, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setText("Gate status");
		panel1.add(label5, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JSeparator separator1 = new JSeparator();
		panel1.add(separator1, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final JLabel label6 = new JLabel();
		label6.setText("Plane details");
		panel1.add(label6, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		planeDetails = new JList();
		panel1.add(planeDetails, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
		rootPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(50, -1), null, null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
		rootPanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(50, -1), null, null, 0, false));
		final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
		rootPanel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 25), null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return rootPanel;
	}

	public void enableButtons(JButton... buttons) {
		//Clear all buttons
		grantGroundClearanceButton.setEnabled(false);
		taxiToGateButton.setEnabled(false);
		grantRunway.setEnabled(false);
		for (JButton button: buttons) {
			if (Arrays.stream(buttons).toList().contains(button))
				button.setEnabled(true);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		updatePlaneInfo();
		boolean isFlightSelected = !flightList.isSelectionEmpty();
		String selectedFlightCode = isFlightSelected ? flightList.getSelectedValue() : "Error";
		int mCode = isFlightSelected ? AMDatabase.getMCodeFromFlightCode(selectedFlightCode) : -1;
		int status = isFlightSelected ? AMDatabase.getStatus(mCode) : -1;

		if (status == ManagementRecord.WANTING_TO_LAND)
			enableButtons(grantGroundClearanceButton);
		else if (status == ManagementRecord.LANDED)
			enableButtons(taxiToGateButton);
		else if (status == ManagementRecord.AWAITING_TAXI)
			enableButtons(grantRunway);
		else
			enableButtons();
	}
}
