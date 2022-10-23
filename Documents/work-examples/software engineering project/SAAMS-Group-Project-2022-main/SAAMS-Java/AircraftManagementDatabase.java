import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;


// Generated by Together
/**
 * A central database ("model" class): It is intended that there will be only
 * one instance of this class. Maintains an array of ManagementRecords (MRs),
 * one per potential visiting aircraft. Some MRs hold information about aircraft
 * currently being managed by SAAMS, and some may have the status "Free". The
 * index of each ManagementRecord in the array is its "management code"
 * ("mCode"), and the mCode of any particular visiting aircraft's
 * ManagementRecord must remain fixed once it is allocated. Many classes
 * register as observers of this class, and are notified whenever any aircraft's
 * (MR's) state changes.
 *
 * @stereotype model
 * @url element://model:project::SAAMS/design:view:::id4tg7xcko4qme4cko4swuu
 * @url
 * element://model:project::SAAMS/design:node:::id4tg7xcko4qme4cko4swuu.node149
 * @url element://model:project::SAAMS/design:view:::id1bl79cko4qme4cko4sw5j
 * @url element://model:project::SAAMS/design:view:::idwwyucko4qme4cko4sgxi
 * @url
 * element://model:project::SAAMS/design:node:::id2wdkkcko4qme4cko4svm2.node39
 * @url element://model:project::SAAMS/design:view:::id2wdkkcko4qme4cko4svm2
 * @url
 * element://model:project::SAAMS/design:node:::id3oolzcko4qme4cko4sx40.node169
 * @url element://model:project::SAAMS/design:view:::id2fh3ncko4qme4cko4swe5
 * @url element://model:project::SAAMS/design:view:::id28ykdcko4qme4cko4sx0e
 * @url
 * element://model:project::SAAMS/design:node:::id15rnfcko4qme4cko4swib.node107
 * @url element://model:project::SAAMS/design:view:::id15rnfcko4qme4cko4swib
 * @url element://model:project::SAAMS/design:view:::id3oolzcko4qme4cko4sx40
 */
public class AircraftManagementDatabase extends Observable {

    /**
     * The array of ManagementRecords. Attribute maxMRs specifies how large this
     * array should be. Initialize to a collection of MRs each in the FREE
     * state. Note: This array could be replaced by another other suitable
     * collection data structure.
     *
     * @byValue
     * @clientCardinality 1
     * @directed true
     * @label contains
     * @shapeType AggregationLink
     * @supplierCardinality 0..*
     */
    private ManagementRecord[] MRs;

    /**
     * The size of the array MRs holding ManagementRecords.<br /><br />In this
     * simple system 10 will do!
     */
    public int maxMRs = 10;

    /**
     * stores the possible state transitions for a ManagementRecord, in the form
     * of oldState as the key, possible new states as the associated values
     */
    private HashMap<Integer, List<Integer>> validStateTransitionsOfMR;

    public AircraftManagementDatabase() {
        MRs = new ManagementRecord[maxMRs];

        for (int i=0;i<maxMRs;i++)
            MRs[i] = new ManagementRecord();

        validStateTransitionsOfMR = new HashMap<>(); //initialising the Hashmap used for validating status transitions
        ManagementRecord MR = new ManagementRecord();
        validStateTransitionsOfMR.put(MR.FREE, Arrays.asList(MR.IN_TRANSIT, MR.WANTING_TO_LAND));
        validStateTransitionsOfMR.put(MR.IN_TRANSIT, Arrays.asList(MR.FREE));
        validStateTransitionsOfMR.put(MR.WANTING_TO_LAND, Arrays.asList(MR.GROUND_CLEARANCE_GRANTED));
        validStateTransitionsOfMR.put(MR.GROUND_CLEARANCE_GRANTED, Arrays.asList(MR.LANDING));
        validStateTransitionsOfMR.put(MR.LANDING, Arrays.asList(MR.LANDED));
        validStateTransitionsOfMR.put(MR.LANDED, Arrays.asList(MR.TAXIING));
        validStateTransitionsOfMR.put(MR.TAXIING, Arrays.asList(MR.UNLOADING));
        validStateTransitionsOfMR.put(MR.UNLOADING, Arrays.asList(MR.READY_CLEAN_AND_MAINT));
        validStateTransitionsOfMR.put(MR.READY_CLEAN_AND_MAINT, Arrays.asList(MR.FAULTY_AWAIT_CLEAN, MR.CLEAN_AWAIT_MAINT, MR.OK_AWAIT_CLEAN));
        validStateTransitionsOfMR.put(MR.FAULTY_AWAIT_CLEAN, Arrays.asList(MR.AWAIT_REPAIR));
        validStateTransitionsOfMR.put(MR.CLEAN_AWAIT_MAINT, Arrays.asList(MR.AWAIT_REPAIR, MR.READY_REFUEL));
        validStateTransitionsOfMR.put(MR.OK_AWAIT_CLEAN, Arrays.asList(MR.READY_REFUEL));
        validStateTransitionsOfMR.put(MR.AWAIT_REPAIR, Arrays.asList(MR.READY_CLEAN_AND_MAINT));
        validStateTransitionsOfMR.put(MR.READY_REFUEL, Arrays.asList(MR.READY_PASSENGERS));
        validStateTransitionsOfMR.put(MR.READY_PASSENGERS, Arrays.asList(MR.READY_DEPART));
        validStateTransitionsOfMR.put(MR.READY_DEPART, Arrays.asList(MR.AWAITING_TAXI));
        validStateTransitionsOfMR.put(MR.AWAITING_TAXI, Arrays.asList(MR.AWAITING_TAKEOFF));
        validStateTransitionsOfMR.put(MR.AWAITING_TAKEOFF, Arrays.asList(MR.DEPARTING_THROUGH_LOCAL_AIRSPACE));
        validStateTransitionsOfMR.put(MR.DEPARTING_THROUGH_LOCAL_AIRSPACE, Arrays.asList(MR.FREE));
        setChanged();
        notifyObservers();
    }
    
    /**
     * accesses the MR given the code, but also checks that mCode is valid
     * @param mCode
     * @return null if mCode is invalid
     */
    public ManagementRecord getMR(int mCode) {
        if (mCode >= 0 && mCode < maxMRs)
            return MRs[mCode];
        
        throw new RuntimeException("Attempted to access mCode"+mCode);
    }

    /**
     * returns the mCode of the given flightCode
     * (this is used for GUI elements which show the flight code)
     * @param flightCode
     * @return
     */
    public int getMCodeFromFlightCode(String flightCode) {
        for (int mCode = 0;mCode < maxMRs; mCode++) {
            if (getMR(mCode).getFlightCode().equals(flightCode))
                return mCode;
        }

        //when the flight was not found
        throw new RuntimeException("Flight #"+flightCode+" could not be associated with an mCode");
    }

    /**
     * Return the status of the MR with the given mCode supplied as a parameter.
     */
    public int getStatus(int mCode) {
        ManagementRecord mr = getMR(mCode);
        return mr.getStatus();
    }

    /**
     * Checks that a given state transition of MR is valid
     *
     * @param oldState the "from" state
     * @param newState the "to" state
     * @return is the transition oldState->NewState valid?
     */
    private boolean isValidStateTransition(int oldState, int newState) {
        return validStateTransitionsOfMR.get(oldState).contains(newState);
    }

    /**
     * Forward a status change request to the MR given by the mCode supplied as
     * a parameter. Parameter newStatus is the requested new status. No effect
     * is expected if the current status is not a valid preceding status. This
     * operation is appropriate when the status change does not need any
     * additional information to be noted. It is present instead of a large
     * collection of public operations for requesting specific status changes.
     */
    public void setStatus(int mCode, int newStatus) {
        System.out.print("setStatus("+mCode+", "+newStatus);
        ManagementRecord mr = getMR(mCode);
        int oldStatus = getStatus(mCode);
        System.out.print(", "+mr.getStatusAsString()+"->"+newStatus);
        if (isValidStateTransition(oldStatus, newStatus)) {
            mr.setStatus(newStatus);
            System.out.print(", Accepted");
        }

        System.out.print(", Rejected");
        System.out.println();

        
        
        setChanged();
        notifyObservers(mCode);
    }

    /**
     * Return the flight code from the given MR supplied as a parameter.The
 request is forwarded to the MR.
     * @param mCode mCode of the aircraft we're interested in
     * @return flight code of the aircraft
     */
    public String getFlightCode(int mCode) {
        return getMR(mCode).getFlightCode();
    }

    public Vector<String> getFlightCodes(int[] mCodes){
        Vector<String> FlightCodes = new Vector<>(maxMRs);
        for(int i = 0; i < mCodes.length; i++) {
            FlightCodes.add((MRs[i]).getFlightCode());
        }
        return FlightCodes;
    }

    /**
     * Returns an array of mCodes: Just the mCodes of those MRs with the given
     * status supplied as a parameter.Principally for call by the various
 interface screens.
     * @param statusCode the status that we're looking for
     * @return the elements of MR which have the status given in the argument
     * 
     */
    public int[] getWithStatus(int statusCode) {
        ArrayList<Integer> mCodes = new ArrayList<>();
        for (int i=0; i< maxMRs;i++) {
            if (getStatus(i)==statusCode) mCodes.add(i);
        }
        
        return mCodes.stream().mapToInt(n->n).toArray(); // https://www.youtube.com/watch?v=IJ_icDmulqU
    }

    /**
     * returns the flights (as ManagementRecords) that are in one of the given states
     * @param statuses : the statuses that we want the result aircraft can have
     * @return the aircrafts, as an ArrayList of ManagementRecords
     */
    public ManagementRecord[] getWithStatuses(int... statuses) {
        List<Integer> statusesList = new ArrayList<>();
        for (int status : statuses)
            statusesList.add(status);

        ArrayList<ManagementRecord> wantedMRs = new ArrayList<>(0);
        for (ManagementRecord mr : MRs) {
            if (statusesList.contains((Integer) mr.getStatus()))
                wantedMRs.add(mr);
        }

        ManagementRecord[] resultMRs = new ManagementRecord[wantedMRs.size()];
        for (int i = 0; i < wantedMRs.size(); i++)
            resultMRs[i] = wantedMRs.get(i);
        return resultMRs;
    }
    /**
     * returns all of the MRs in the database.
     *  This is used for debug and GUI stuff
     *  TODO: is this necessary?
     * @return
     */
    public ManagementRecord[] getNonFreeMRs() {
        return getWithStatuses(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        //TODO: have a cleaner solution, it's too late for this shite
    }
    /**
     * The radar has detected a new aircraft, and has obtained flight descriptor
     * fd from it.
     *
     * This operation finds a currently FREE MR and forwards the radarDetect
     * request to it for recording.
     */
    public void radarDetect(FlightDescriptor fd) {
        
        //finding the first MR with the status FREE
        int freeMRindex = -1;
        for (int i=0; i<maxMRs; i++) {
            if (getMR(i).getStatus() == ManagementRecord.FREE) {
                freeMRindex = i;
                break;
            }
        }

        if (freeMRindex == -1) //there is no free MR index,
            throw new RuntimeException("No free MRs available for the aircraft" + fd);

        System.out.println("The allocated MR has mCode="+freeMRindex);
        
        getMR(freeMRindex).radarDetect(fd); //sends message to MR to add the aircraft


        String destination = fd.getItinerary().getTo();
        String next = fd.getItinerary().getNext();

        if (destination.equals("Stirling")) //only checks the "to" field
            getMR(freeMRindex).setStatus(ManagementRecord.WANTING_TO_LAND);
        else
            getMR(freeMRindex).setStatus(ManagementRecord.IN_TRANSIT);


        setChanged();
        notifyObservers(freeMRindex);
    }

    /**
     * The aircraft in the MR given by mCode supplied as a parameter has
     * departed from the local airspace.The message is forwarded to the MR,
 which can then delete/archive its contents and become FREE.
     * @param mCode index of the MR to be cleared
     */
    public void radarLostContact(int mCode) {
        //there is no archiving needed for this, the MR is simply cleared
        ManagementRecord mr = getMR(mCode);

        mr.getItinerary().departingForNextStop();
        
        mr.setStatus(ManagementRecord.FREE); 
        mr.clear();   
        setChanged();
        notifyObservers();
    }

    /**
     * A GOC has allocated the given gate to the aircraft with the given mCode
     * supplied as a parameter for unloading passengers.The message is
 forwarded to the given MR for status update.
     * @param mCode mCode of the MR of the aircraft to be taxied
     * @param gateNumber gate the Aircraft is going to be taxied to 
     */
    public void taxiTo(int mCode, int gateNumber) {
        ManagementRecord mr = getMR(mCode);
        setStatus(mCode, ManagementRecord.TAXIING);
        mr.taxiTo(gateNumber);
        setChanged();
        notifyObservers();
    }



    /**
     * The Maintenance Supervisor has reported faults with the given description
     * in the aircraft with the given mCode.The message is forwarded to the
 given MR for status update.
     * @param mCode mCode of the aircraft where the fault was found
     * @param description description of the fault
     */
    public void faultsFound(int mCode, String description) {
        ManagementRecord mr = getMR(mCode);        
        mr.faultsFound(description);
        
        setChanged();
        notifyObservers();
    }

    /**
     * The given passenger is boarding the aircraft with the given mCode.Forward the message to the given MR for recording in the passenger list.
     * @param mCode mCode of the aircraft
     * @param details details of the passenger that's boarding
     */
    public void addPassenger(int mCode, PassengerDetails details) {
        getMR(mCode).addPassenger(details);
        
        //is this necessary?
        setChanged();
        notifyObservers();
    }

    /**
     * Return the PassengerList of the aircraft with the given mCode.
     */
    public PassengerList getPassengerList(int mCode) {
        return getMR(mCode).getPassengerList();
    }

    /**
     * Return the Itinerary of the aircraft with the given mCode.
     */
    public Itinerary getItinerary(int mCode) {
        return getMR(mCode).getItinerary();
    }

        /**
         * this function is used for debug purposes, namely testing GUI components. Forcibly sets the MRs
         * @param mrs the MRs, has to be an array of lenght 10
         */
        public void dbg_setMRs(ManagementRecord[] mrs) {
            this.MRs = mrs;
        }

    public String getFaultDescriptor(int mCode) {
            return getMR(mCode).getFaultDescription();
    }


}