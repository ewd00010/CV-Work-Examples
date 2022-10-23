/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author catal
 */
public class AircraftManagementDatabaseTest {

    private static AircraftManagementDatabase AMDatabase;
    private FlightDescriptor flightToStirling; //a debug flight which will land at stirling
    private FlightDescriptor passingFlight;    //a debug flight that will not land
    private ManagementRecord freeMR;            //what an non-allocated MR looks like
    private FlightDescriptor flightToStirlingWithPeople; //has a list of passengers
    private FlightDescriptor otherFlight;

    private void addDummyFlights(FlightDescriptor... fds) {
        for (FlightDescriptor fd : fds)
            AMDatabase.radarDetect(fd);
    }

    private PassengerList makePassengersList(String... names) {
        PassengerList passengers = new PassengerList();
        for (String name: names)
            passengers.addPassenger(new PassengerDetails(name));

        return passengers;
    }

    private void bringToLanding(int mCode) {
        ManagementRecord MR = new ManagementRecord();
        int[] necessaryStates = new int[] {MR.GROUND_CLEARANCE_GRANTED, MR.LANDING};

        for (int necessaryState : necessaryStates)
            AMDatabase.setStatus(mCode, necessaryState);
    }

    /**
     * brings an MR at mCode from WANTING_TO_LAND to TAXIING.
     * This is needed because there cannot be invalid status changes
     * @param mCode mCode of the MR to be modified
     */
    private void bringToTaxiing(int mCode) {
        ManagementRecord MR = new ManagementRecord();
        int[] necessaryStates = new int[] {MR.GROUND_CLEARANCE_GRANTED, MR.LANDING, MR.LANDED, MR.TAXIING};

        for (int necessaryState : necessaryStates)
            AMDatabase.setStatus(mCode, necessaryState);
    }
        
    
    public AircraftManagementDatabaseTest() {
        flightToStirling = new FlightDescriptor("AB1234", new Itinerary("Glasgow", "Stirling", "Renfrew"), new PassengerList());
        passingFlight = new FlightDescriptor("ZH3412", new Itinerary("Beijing", "Gongzhou", "Saigon"), new PassengerList());
        freeMR = new ManagementRecord();

        flightToStirlingWithPeople = new FlightDescriptor(flightToStirling.getFlightCode(),
                        flightToStirling.getItinerary(),
                        makePassengersList("Giancarlo", "Ewan", "Josh", "Chiara", "Paddy", "Cameron", "Sam"));

        otherFlight = new FlightDescriptor("CG2506",
                new Itinerary("Amsterdam", "Berlin", "Cagliari"),
                new PassengerList());
    }
    
    @BeforeAll
    public static void setUpClass() {

    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        AMDatabase = new AircraftManagementDatabase();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getStatus method, of class AircraftManagementDatabase.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        addDummyFlights(flightToStirling, passingFlight);
        assertEquals(AMDatabase.getStatus(0), ManagementRecord.WANTING_TO_LAND);
        assertEquals(AMDatabase.getStatus(1), ManagementRecord.IN_TRANSIT);
        assertEquals(AMDatabase.getStatus(2), ManagementRecord.FREE);
    }

    /**
     * Test of setStatus method, of class AircraftManagementDatabase.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        int mCode = 0;
        int newStatus = ManagementRecord.IN_TRANSIT;
        AMDatabase.setStatus(mCode, newStatus);
        assertEquals(AMDatabase.getStatus(mCode), newStatus);

        //assert that if it's not a valid transition, then the transition should not happen
        int oldStatus = AMDatabase.getStatus(mCode);
        int invalidStatus = ManagementRecord.OK_AWAIT_CLEAN;
        AMDatabase.setStatus(mCode, invalidStatus);
        assertEquals(AMDatabase.getStatus(mCode), oldStatus);
    }

    /**
     * Test of getFlightCode method, of class AircraftManagementDatabase.
     */
    @Test
    public void testGetFlightCode() {
        System.out.println("getFlightCode");
        addDummyFlights(flightToStirling);
        String expected = flightToStirling.getFlightCode();
        assertEquals(AMDatabase.getFlightCode(0), expected);
    }

    /**
     * Test of getWithStatus method, of class AircraftManagementDatabase.
     */
    @Test
    public void testGetWithStatus() {
        System.out.println("getWithStatus");
        int wantedStatus = ManagementRecord.IN_TRANSIT;
        addDummyFlights(flightToStirling, flightToStirling, passingFlight, flightToStirling, passingFlight);//there are 3 flights to stirling
        int[] relevantMRindexes = AMDatabase.getWithStatus(wantedStatus);
        assertEquals(2, relevantMRindexes.length);

        for (int mr: relevantMRindexes)
            assertEquals(wantedStatus, AMDatabase.getMR(mr).getStatus());
    }

    /**
     * Test of radarDetect method, of class AircraftManagementDatabase.
     */
    @Test
    public void testRadarDetect() {
        System.out.println("radarDetect");
        AMDatabase.radarDetect(flightToStirling); //should be allocated as the first one, index 0
        AMDatabase.radarDetect(passingFlight); //should be allocated at index 1

        //check allocation
        assertEquals(AMDatabase.getMR(0).getFlightDescriptor(), flightToStirling);
        assertEquals(AMDatabase.getMR(1).getFlightDescriptor(), passingFlight);

        //check that the status is correct
        assertEquals(AMDatabase.getMR(0).getStatus(), ManagementRecord.WANTING_TO_LAND);
        assertEquals(AMDatabase.getMR(1).getStatus(), ManagementRecord.IN_TRANSIT);
    }

    /**
     * Test of radarLostContact method, of class AircraftManagementDatabase.
     */
    @Test
    public void testRadarLostContact() {
        System.out.println("radarLostContact");
        addDummyFlights(flightToStirling, flightToStirling, passingFlight);
        //passingflight is allocated to position 2 and should be RadarLostContact-able
        AMDatabase.radarLostContact(2);
        assertEquals(AMDatabase.getStatus(2), freeMR.getStatus());
    }

    /**
     * Test of taxiTo method, of class AircraftManagementDatabase.
     */
    @Test
    public void testTaxiTo() {
        System.out.println("taxiTo");
        int mCode = 0;
        addDummyFlights(flightToStirling);
        ManagementRecord MR = new ManagementRecord();
        //the aircraft has to be in the TAXIING status
        AMDatabase.setStatus(mCode, MR.GROUND_CLEARANCE_GRANTED);
        AMDatabase.setStatus(mCode, MR.LANDING);
        AMDatabase.setStatus(mCode, MR.LANDED);
        AMDatabase.setStatus(mCode, MR.TAXIING);

        int gateNumber = 0;
        AMDatabase.taxiTo(mCode, gateNumber); //only changes the internal state of the MR
        assertEquals(AMDatabase.getMR(mCode).getGateNumber(), gateNumber);
    }

    /**
     * Test of faultsFound method, of class AircraftManagementDatabase.
     */
    @Test
    public void testFaultsFound() {
        System.out.println("faultsFound");
        int mCode = 0;
        addDummyFlights(flightToStirling);
        String faultDescription = "the engine was stolen, damn engineers";
        AMDatabase.faultsFound(mCode, faultDescription);
        assertEquals(AMDatabase.getMR(mCode).getFaultDescription(), faultDescription);
    }

    /**
     * Test of addPassenger method, of class AircraftManagementDatabase.
     */
    @Test
    public void testAddPassenger() {
        System.out.println("addPassenger");
        int mCode = 0;
        PassengerDetails dad = new PassengerDetails("Dad");
        PassengerDetails mom = new PassengerDetails("Mom");
        PassengerList justMom = new PassengerList();
        justMom.addPassenger(mom);
        FlightDescriptor boardingFlight = new FlightDescriptor("BR0000",
                new Itinerary("Atrium", "UnderGround", "Starbucks"), justMom);

        addDummyFlights(boardingFlight);
        AMDatabase.addPassenger(mCode, dad);
        PassengerList expected = new PassengerList();
        expected.addPassenger(mom);
        expected.addPassenger(dad);


        assertEquals(AMDatabase.getPassengerList(mCode), expected);
    }

    /**
     * Test of getPassengerList method, of class AircraftManagementDatabase.
     */
    @Test
    public void testGetPassengerList() {
        System.out.println("getPassengerList");
        int mCode = 0;
        PassengerList expected = flightToStirlingWithPeople.getPassengerList();
        addDummyFlights(flightToStirlingWithPeople);
        assertEquals(AMDatabase.getPassengerList(mCode), expected);


        PassengerList noPassengers = makePassengersList();
        addDummyFlights(flightToStirling);
        mCode++;
        assertEquals(AMDatabase.getPassengerList(mCode), noPassengers);
    }

    /**
     * Test of getItinerary method, of class AircraftManagementDatabase.
     */
    @Test
    public void testGetItinerary() {
        System.out.println("getItinerary");
        int mCode = 0;
        addDummyFlights(flightToStirling);
        Itinerary expectedItinerary = flightToStirling.getItinerary();
        Itinerary gottenItinerary = AMDatabase.getItinerary(mCode);
        assertEquals(gottenItinerary, expectedItinerary);
    }

    @Test
    void getMR() {
        System.out.println("getMR");
        addDummyFlights(flightToStirling, passingFlight, flightToStirlingWithPeople);

        //check flight descriptors
        assertEquals(AMDatabase.getMR(0).getFlightDescriptor(), flightToStirling);
        assertEquals(AMDatabase.getMR(1).getFlightDescriptor(), passingFlight);
        assertEquals(AMDatabase.getMR(2).getFlightDescriptor(), flightToStirlingWithPeople);
    }

    @Test
    void getMCodeFromFlightCode() {
        int mCode = 0;
        addDummyFlights(flightToStirling);
        String flightCode = flightToStirling.getFlightCode();
        assertEquals(AMDatabase.getMCodeFromFlightCode(flightCode), 0);
    }

    void testGetStatus_aux(int mCode, int status) {
        AMDatabase.setStatus(mCode, status);
        assertEquals(AMDatabase.getStatus(mCode), status);
    }

    @Test
    void getStatus() {
        int mCode = 0;
        addDummyFlights(flightToStirling); //will be in MR.wanting_to_land
        ManagementRecord MR = new ManagementRecord();
        testGetStatus_aux(0, MR.GROUND_CLEARANCE_GRANTED);
        testGetStatus_aux(0, MR.LANDING);
        testGetStatus_aux(0, MR.LANDED);
        testGetStatus_aux(0, MR.TAXIING);
        //more tests could be added, with different mCodes
    }

    @Test
    void setStatus() {
        int mCode = 0;
        addDummyFlights(flightToStirling);
        int expectedStatus = ManagementRecord.GROUND_CLEARANCE_GRANTED;
        AMDatabase.setStatus(mCode, expectedStatus);
        assertEquals(AMDatabase.getStatus(mCode), expectedStatus);
    }

    @Test
    void getFlightCode() {
        int mCode = 0;
        addDummyFlights(flightToStirling);
        String flightCode = flightToStirling.getFlightCode();
        assertEquals(AMDatabase.getFlightCode(mCode), flightCode);
    }

    @Test
    void getFlightCodes() {
        int[] mCodes = new int[]{0, 2, 3, 5};
        for (int i=0;i<7;i+=3)
                addDummyFlights(flightToStirling, passingFlight, otherFlight);

        HashMap<Integer, FlightDescriptor> expectedFlights = new HashMap<>();
        expectedFlights.put(0, flightToStirling);
        expectedFlights.put(2, otherFlight);
        expectedFlights.put(3, flightToStirling);
        expectedFlights.put(5, otherFlight);

        for (int mCode : mCodes)
            assertEquals(AMDatabase.getFlightCode(mCode), expectedFlights.get(new Integer(mCode)).getFlightCode());
    }

    @Test
    void getWithStatus() {
        for (int i=0;i<AMDatabase.maxMRs;i++)
            addDummyFlights(flightToStirling);

        int[] expectedMCodes = new int[]{2, 4, 5, 7};
        for (int expectedMCode: expectedMCodes)
            bringToTaxiing(expectedMCode);

        int[] gottenMRs = AMDatabase.getWithStatus(ManagementRecord.TAXIING);
        assertEquals(expectedMCodes.length, gottenMRs.length);
        for (int i=0;i<expectedMCodes.length; i++)
            assertEquals(gottenMRs[i], expectedMCodes[i]);
    }

    @Test
    void getWithStatuses() {
        for (int i=0;i<AMDatabase.maxMRs;i++)
            addDummyFlights(flightToStirling);

        int[] expectedMCodesLanding = new int[]{2, 4, 5, 7};
        int[] expectedMCodesTaxiing = new int[]{1, 3, 6};

        for (int expectedMCode: expectedMCodesLanding)
            bringToLanding(expectedMCode);

        for (int expectedMCode: expectedMCodesTaxiing)
            bringToTaxiing(expectedMCode);

        ManagementRecord[] gottenMRs = AMDatabase.getWithStatuses(ManagementRecord.TAXIING, ManagementRecord.LANDING);
        int expectedLength = expectedMCodesLanding.length + expectedMCodesTaxiing.length;
        assertEquals(expectedLength, gottenMRs.length);
        ArrayList<Integer> validStates = new ArrayList<Integer>();
        validStates.add(ManagementRecord.LANDING);
        validStates.add(ManagementRecord.TAXIING);
        for (int i=0;i<expectedLength;i++) {
            int statusOfFlight = gottenMRs[i].getStatus();
            assertTrue(validStates.contains(statusOfFlight));
        }
    }

    @Test
    void getNonFreeMRs() {
    }

    @Test
    void radarDetect() {
    }

    @Test
    void radarLostContact() {
    }

    @Test
    void taxiTo() {
    }

    @Test
    void faultsFound() {
    }

    @Test
    void addPassenger() {
    }

    @Test
    void getPassengerList() {
    }

    @Test
    void getItinerary() {
    }

    @Test
    void dbg_setMRs() {
    }

    @Test
    void getFaultDescriptor() {
    }
}
