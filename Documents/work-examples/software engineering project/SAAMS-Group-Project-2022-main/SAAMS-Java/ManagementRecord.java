// Generated by Together

import java.util.HashMap;

/**
 * An individual aircraft management record:
 * Either FREE or models an aircraft currently known to SAAMS.
 * See MRState diagram for operational details, and written documentation.
 * This class has public static int identifiers for the individual status codes.
 * An MR may be "FREE", or may contain a record of the status of an individual aircraft under the management of SAAMS.
 * An instance of AircraftManagementDatabase holds a collection of ManagementRecords, and sends the ManagementRecords messages to control/fetch their status.
 * @stereotype entity
 * @url element://model:project::SAAMS/design:node:::id15rnfcko4qme4cko4swib.node107
 * @url element://model:project::SAAMS/design:view:::id3oolzcko4qme4cko4sx40
 * @url element://model:project::SAAMS/design:view:::id4tg7xcko4qme4cko4swuu
 * @url element://model:project::SAAMS/design:node:::id4tg7xcko4qme4cko4swuu.node152
 * @url element://model:project::SAAMS/design:node:::id3oolzcko4qme4cko4sx40.node171
 * @url element://model:project::SAAMS/design:view:::id2wdkkcko4qme4cko4svm2
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 * @url element://model:project::SAAMS/design:node:::id2wdkkcko4qme4cko4svm2.node41
 */
public class ManagementRecord {

/** Status code: This MR is currently not managing any aircraft information
 *
 * See MRState diagram.*/
  public static int FREE = 0;

/** Status code
 *
 * See MRState diagram.*/
  public static int IN_TRANSIT = 1;

/** Status code
 *
 * See MRState diagram.*/
  public static int WANTING_TO_LAND = 2;

/** Status code
 *
 * See MRState diagram.*/
  public static int GROUND_CLEARANCE_GRANTED = 3;

/** Status code
 *
 * See MRState diagram.*/
  public static int LANDING = 4;

/** Status code
 *
 * See MRState diagram.*/
  public static int LANDED = 5;

/** Status code
 *
 * See MRState diagram.*/
  public static int TAXIING = 6;

/** Status code
 *
 * See MRState diagram.*/
  public static int UNLOADING = 7;

/** Status code
 *
 * See MRState diagram.*/
  public static int READY_CLEAN_AND_MAINT = 8;

/** Status code
 *
 * See MRState diagram.*/
  public static int FAULTY_AWAIT_CLEAN = 9;

  /** Status code
   *
   * See MRState diagram.*/
  public static int OK_AWAIT_CLEAN = 11;

/** Status code
 *
 * See MRState diagram.*/
  public static int CLEAN_AWAIT_MAINT = 10;

/** Status code
 *
 * See MRState diagram.*/
  public static int AWAIT_REPAIR = 12;

/** Status code
 *
 * See MRState diagram.*/
  public static int READY_REFUEL = 13;

/** Status code
 *
 * See MRState diagram.*/
  public static int READY_PASSENGERS = 14;

/** Status code
 *
 * See MRState diagram.*/
  public static int READY_DEPART = 15;

/** Status code
 *
 * See MRState diagram.*/
  public static int AWAITING_TAXI = 16;

/** Status code
 *
 * See MRState diagram.*/
  public static int AWAITING_TAKEOFF = 17;

/** Status code
 *
 * See MRState diagram.*/
  public static int DEPARTING_THROUGH_LOCAL_AIRSPACE = 18;

/** The status code for this ManagementRecord.*/
  private int status;

  /**
   * The gate number allocated to this aircraft, when there is one.
   */
  private int gateNumber;

/** A short string identifying the flight:
 *
 * Usually airline abbreviation plus number, e.g. BA127.
 * Obtained from the flight descriptor when the aircraft is first detected.
 *
 * This is the code used in timetables, and is useful to show on public information screens.*/
  private String flightCode;

  /**
 * Holds the aircraft's itinerary.
 * Downloaded from the flight descriptor when the aircraft is first detected.
 * @clientCardinality 1
 * @directed true
 * @label contains
 * @shapeType AggregationLink
 * @supplierCardinality 1
 */
  private Itinerary itinerary;

  /**
 * The list of passengers on the aircraft.
 * Incoming flights supply their passenger list in their flight decsriptor.
 * Outbound flights have passenger lists built from passenger details supplied by the gate consoles.
 * @clientCardinality 1
 * @directed true
 * @label contains
 * @shapeType AggregationLink
 * @supplierCardinality 1
 */
  private PassengerList passengerList;

  /**
   * Contains a description of what is wrong with the aircraft if it is found to be faulty during maintenance inspection.
   */
  private String faultDescription;

  public ManagementRecord() {
      clear();
  }
/**
  * Request to set the MR into a new status.
  *
  * Only succeeds if the state change conforms to the MRState diagram.
  *
  * This is a general purpose state change request where no special details accompany the state change.
  * [Special status changers are, for example, "taxiTo", where a gate number is supplied.]
  * @preconditions Valid transition requested*/
  public void setStatus(int newStatus) {
    //TODO: should the state transition check happen here?
    this.status = newStatus;
  }

  /**
   * Return the status code of this MR.
   */
  public int getStatus(){

    return status;

  }

  /**
   * utility method used for showing plane statuses in a more customer-friendly way
   * @return  the result string, which tends to be concise and simplified
   */
  public String getStatusAsString() {
    String resultString = "error";

    HashMap<Integer, String> statusToNames = new HashMap<>();
    statusToNames.put(FREE, "Available");
    statusToNames.put(IN_TRANSIT, "In transit");
    statusToNames.put(WANTING_TO_LAND, "Requesting to land");
    statusToNames.put(GROUND_CLEARANCE_GRANTED, "Landing Initiated");
    statusToNames.put(LANDING, "Landing");
    statusToNames.put(LANDED, "Landed");
    statusToNames.put(TAXIING, "Driving to the gate");
    statusToNames.put(UNLOADING, "Unloading passengers");
    statusToNames.put(READY_CLEAN_AND_MAINT, "Cleaning");
    statusToNames.put(FAULTY_AWAIT_CLEAN, "Cleaning after maintenance");
    statusToNames.put(OK_AWAIT_CLEAN, "Cleaning initiated");
    statusToNames.put(CLEAN_AWAIT_MAINT, "Maintenance");
    statusToNames.put(AWAIT_REPAIR, "Fault repair");
    statusToNames.put(READY_REFUEL, "About to refuel");
    //TODO once there's a way to get an MR's gate, the next line can be uncommented
    //statusToNames.put(READY_DEPART, "Boarding at Gate" + GIDatabase.getGateOf(this));
    statusToNames.put(AWAITING_TAXI, "Departing");
    statusToNames.put(AWAITING_TAKEOFF, "Preparing for takeoff");
    statusToNames.put(DEPARTING_THROUGH_LOCAL_AIRSPACE, "Taking off");

    return statusToNames.get(status);
    //TODO: is this too convoluted? I initially made a big switch block, but it was very long...
  }

  /**
   * Return the flight code of this MR.
   */
  public String getFlightCode(){

      return flightCode;

  }

/** Sets up the MR with details of newly detected flight
  *
  * Status must be FREE now, and becomes either IN_TRANSIT or WANTING_TO_LAND depending on the details in the flight descriptor.
  * @preconditions Status is FREE*/
  public void radarDetect(FlightDescriptor fd){
      if (status != FREE)
        throw new RuntimeException("tried to allocate an MR which isn't free, status=" + status);
      this.flightCode = fd.getFlightCode();
      this.itinerary = fd.getItinerary();
      this.passengerList = fd.getPassengerList();
  }

/** This aircraft has departed from local airspace.
  *
  * Status must have been either IN_TRANSIT or DEPARTING_THROUGH_LOCAL_AIRSPACE, and becomes FREE (and the flight details are cleared).
  * @preconditions Status is IN_TRANSIT or DEPARTING_THROUGH_LOCAL_AIRSPACE*/
  public void radarLostContact() {
      System.out.println("Flight : " + flightCode + " has departed local airspace");
      clear();
  }

/** GOC has allocated the given gate for unloading passengers.
  *
  * The gate number is recorded.The status must have been LANDED and becomes TAXIING.
  * @preconditions Status is LANDED*/
  public void taxiTo(int gateNumber){
    this.gateNumber = gateNumber;
  }

/** The Maintenance Supervisor has reported faults.
  *
  * The problem description is recorded.
  *
  * The status must have been READY_FOR_CLEAN_MAINT or CLEAN_AWAIT_MAINT and becomes FAULTY_AWAIT_CLEAN or AWAIT_REPAIR respectively.
  * @preconditions Status is READY_FOR_CLEAN_MAINT or CLEAN_AWAIT_MAINT*/
  public void faultsFound(String description){
      int nextState = -1;
      if (status == READY_CLEAN_AND_MAINT) {
        faultDescription = description;
        nextState = FAULTY_AWAIT_CLEAN;
      }
      else if (status == AWAIT_REPAIR) {
        faultDescription = description;
        nextState = AWAIT_REPAIR;
      }
      else
        throw new RuntimeException("Faults found on an aircraft in invalid status = "+status);



  }

  /**
   * method that tells the MR the fault that was found has now been fixed.
   * This method will be called by MaintenanceInspector via the GUI
   * The use case can be found here: http://www.cs.stir.ac.uk/~sma/SAAMS/index.html#R3
   */
  public void completeRepair() {
      this.setStatus(READY_CLEAN_AND_MAINT);
  }

/** The given passenger is boarding this aircraft.
  *
  * Their details are recorded in the passengerList.
  *
  * For this operation to be applicable, the status must be READY_PASSENGERS, and it doesn't change.
  * @preconditions Status is READY_PASSENGERS*/
  public void addPassenger(PassengerDetails details){

        passengerList.addPassenger(details);

  }

/** Return the entire current PassengerList.*/
  public PassengerList getPassengerList(){

    return passengerList;

  }

/** Return the aircraft's Itinerary.*/
  public Itinerary getItinerary(){

    return itinerary;

  }


  public int getGateNumber() {
    return gateNumber;
  }

  /**
   * returns the flight descriptor stored in this MR
   * @return the FlightDescriptor object
   */
  public FlightDescriptor getFlightDescriptor() {
    return new FlightDescriptor(flightCode, itinerary, passengerList);
  }

  public void clear() {
    gateNumber = -1;
    faultDescription = "Error: no fault description";
    flightCode = "Error";
    itinerary = null;
    passengerList = null;
    status = ManagementRecord.FREE;
  }

    public String getFaultDescription() {
      return faultDescription;
    }
}