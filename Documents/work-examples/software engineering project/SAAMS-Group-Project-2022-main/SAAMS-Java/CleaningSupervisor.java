
// Generated by Together


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * An interface to SAAMS:
 * Cleaning Supervisor Screen:
 * Inputs events from the Cleaning Supervisor, and displays aircraft information.
 * This class is a controller for the AircraftManagementDatabase: sending it messages to change the aircraft status information.
 * This class also registers as an observer of the AircraftManagementDatabase, and is notified whenever any change occurs in that <<model>> element.
 * See written documentation.
 *
 * @stereotype boundary/view/controller
 * @url element://model:project::SAAMS/design:view:::id3y5z3cko4qme4cko4sw81
 * @url element://model:project::SAAMS/design:node:::id15rnfcko4qme4cko4swib.node107
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 */
public class CleaningSupervisor extends JFrame
        implements Observer, ActionListener {
  /**
   * The Cleaning Supervisor Screen interface has access to the AircraftManagementDatabase.
   *
   * @clientCardinality 1
   * @supplierCardinality 1
   * @label accesses/observes
   * @directed
   */

  private AircraftManagementDatabase AMDatabase;

  //GUI elements

  private JPanel rootPanel;
  private JTable table;
  private JButton cleanAirplaneButton;
  private JPanel leftPanel;
  private JPanel rightPanel;
  private JLabel selectionDescription;
  private String[] tableHeaders = {"Flight Code", "Gate"};


  public CleaningSupervisor(AircraftManagementDatabase amd) {
    super("Cleaning Supervisor Screen");
    setupGUI();


    //Aircraft management database set-up
    this.AMDatabase = amd;
    amd.addObserver(this);
  }

  public void setupTable(String[][] data) {
    leftPanel.removeAll();  //remove the old table
    table = new JTable(data, tableHeaders); //create the new table
    table.setBackground(Color.white);
    table.getTableHeader().setBackground(Color.lightGray);
    table.setDefaultEditor(Object.class, null); //disables editing
    leftPanel.add(new JScrollPane(table)); //insert the table in the layout
    //Note that a table needs to be in a JScrollPane to show its headers
  }

  public void setupGUI() {
    this.setSize(100, 100); //dummy arguments, will be changed later
    rootPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    leftPanel = new JPanel(new GridLayout(1, 1, 10, 10));
    rightPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    rootPanel.add(leftPanel);
    rootPanel.add(rightPanel);
    cleanAirplaneButton = new JButton("Clean Airplane");
    cleanAirplaneButton.addActionListener(this::actionPerformed);
    selectionDescription = new JLabel("Select a flight to clean");
    rightPanel.add(cleanAirplaneButton, gbc);
    gbc.gridy = 1;
    rightPanel.add(selectionDescription, gbc);
    this.setContentPane(rootPanel);
    String[][] intialData = new String[][]{}; //no initial data
    setupTable(intialData);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
    this.setVisible(true);
  }

  public String[] getMRFlightInfo(ManagementRecord mr) {
      int gateNumber = mr.getGateNumber();
      String gateNumberString = (gateNumber < 0) ? "" : ""+gateNumber;
      return new String[]{mr.getFlightCode(), gateNumberString};
  }


  private void updateGUI() {
    //get list of relevant aircrafts
    ManagementRecord MR = new ManagementRecord();
    ManagementRecord[] relevantMRs =
            AMDatabase.getWithStatuses(MR.READY_CLEAN_AND_MAINT, MR.CLEAN_AWAIT_MAINT, MR.OK_AWAIT_CLEAN,
                    MR.READY_REFUEL, MR.AWAIT_REPAIR, MR.FAULTY_AWAIT_CLEAN);

    String[][] rowsOfTable = new String[relevantMRs.length][];

    for (int i=0;i<relevantMRs.length; i++)
      rowsOfTable[i] = getMRFlightInfo(relevantMRs[i]);
    setupTable(rowsOfTable);


    validate();
    repaint();
  }

  @Override
  public void update(Observable observable, Object o) {

    updateGUI();

  }

  public boolean cleanAircraft() {
    return true; //the aircraft is always cleaned successfully
    //TODO: wait some time, what's a clean way to implement that?
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    /*
       "ready for cleaning and maintenance":
           * start cleaning -> goes to OK_AWAITING_CLEANING
           * Cleaning completed -> Awaiting_Maintenance
           * Faults have been found -> Faulty_awaiting_clean
     * "OK_AWAITING_CLEANING"
           * Maintenance has reported OK -> Ready_Refuel
     * "AWAITING_MAINTENANCE"
           * Faults were found -> Awaiting_repair
     * "AWAITING_REPAIR"
           * Repair complete -> Ready_Clean_and_maintenance
     * "Faulty_AWAITING_CLEANING"
           * Re-Cleaning completed -> Awaiting_repair
     * "Ready_for_refuelling"
           * Refuelling has finished -> Ready_for_passengers
     * "Ready_for_passengers"
           * Flight has closed -> Ready_Depart
           * add passenger -> details recorded in the passenger list
     * "Ready_to_depart"
           * air slot has been allocated -> AWAITING_TAXI
     * "AWAITING_TAXI"
           * taxii permission granted -> AWAITING_TAKE_OFF
     * "WAITING_TO_TAKE_OFF"
           * Take off permitted -> DEPARTING_THROUGH_LOCAL_AIR_SPACE
     * "DEPARTING_THROUGH_LOCAL_AIR_SPACE"
           * Lose on radar -> FREE

*/

    //the event can only come from the button
    //assertEquals(actionEvent.getSource(), cleanAirplaneButton);

    int selectedRow = table.getSelectedRow();
    if (selectedRow < 0)
      selectionDescription.setText("No flight selected"); //TODO: this doesn't work
    else {
      String flightCode = (String) table.getValueAt(selectedRow, 0);
      int mCode = AMDatabase.getMCodeFromFlightCode(flightCode);
      System.out.println("the flight "+flightCode+" is associated with mCode = "+mCode);
      cleanAircraft();
      if (!cleanAircraft())
        throw new RuntimeException("No handler for when an aircraft is cleaned unsuccessfully");


      //the cleaning happens when either
      //  passengers have just disenbarked
              //Ready for cleaning and maintenance -> Awaiting maintenance
      //  fixing has just finished
              //faulty awaiting cleaning -> Awaiting repair
      // TODO Not sure why
             //OK awaiting cleaning -> ready for refuelling

      int oldStatus = AMDatabase.getStatus(mCode);
      int nextStatus = -1;

      if (oldStatus == ManagementRecord.READY_CLEAN_AND_MAINT)
        nextStatus = ManagementRecord.CLEAN_AWAIT_MAINT;
      else if (oldStatus == ManagementRecord.FAULTY_AWAIT_CLEAN)
        nextStatus = ManagementRecord.AWAIT_REPAIR;
      else if (oldStatus == ManagementRecord.OK_AWAIT_CLEAN)
        nextStatus = ManagementRecord.READY_REFUEL;
      else
          throw new RuntimeException("Cleaning supervisor asked to clean a plane that was not meant to be cleaned");

      AMDatabase.setStatus(mCode, nextStatus);
    }

  }
}
