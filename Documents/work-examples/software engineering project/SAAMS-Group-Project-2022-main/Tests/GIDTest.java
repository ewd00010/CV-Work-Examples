import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GIDTest {
    private static GateInfoDatabase GID;
    private static AircraftManagementDatabase AMDatabase;
    private static FlightDescriptor aircraft;
    private static FlightDescriptor aircraft2;
    private static FlightDescriptor aircraft3;

    /*
     * Before each test, a new GateInfoDatabase, AMDatabase and aircraft are instantiated.
     * The flight is then passed to the radarDetect method in AMDatabase
     */
    @BeforeEach
    public void setUp(){
        GID = new GateInfoDatabase();
        AMDatabase = new AircraftManagementDatabase();

        aircraft = new FlightDescriptor("BR00000", new Itinerary("Edinburgh", "Stirling", "Linlithgow"), new PassengerList());
        AMDatabase.radarDetect(aircraft);
    }

    /*
     * The status of each gate specifies if it is:
     * free, reserved (for an aircraft) or occupied (by an aircraft)
     *
     * We will test that the status of the gate changes correctly when we allocate an aircraft, dock an aircraft, and depart an aircraft.
     *
     */
    @Test
    void getStatus() {

        //before we have allocated an aircraft to the gate, the status should be 0 which means its free.
        assertEquals(GID.getStatus(0), Gate.FREE);
        GID.allocate(0, 0);

        //now the gate has been allocated an aircraft and is reserved.
        assertEquals(GID.getStatus(0), Gate.RESERVED);
        GID.docked(0);

        //now the aircraft is docked and the gate is occupied.
        assertEquals(GID.getStatus(0), Gate.OCCUPIED);
        GID.departed(0);

        //now the aircraft has left the gate and the gate is free.
        assertEquals(GID.getStatus(0), Gate.FREE);

    }

    /*
     * When we call getStatus, we specify the gate number, which is the index of the array.
     * What happens when the gate we specify is out of bounds?
     */
    @Test
    void getStatusOutOfBounds() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                GID.getStatus(GID.maxGateNumber));
        assertEquals("2", exception.getMessage());
    }

    /*
     *   The getMaxGateNumber method should return the maximum number of gates, which in this case is 0.
     */
    @Test
    void getMaxGateNumber() {
        assertEquals(GID.getMaxGateNumber(), 2);
    }

    /*
     *  The getStatuses method returns the status of both the gates.
     *  In order to fully test this method, we will introduce a second aircraft and make sure the correct
     *  statuses are returned when aircrafts are allocated, docked and departed.
     */
    @Test
    void getStatuses() {

        //to fully test this, we will use a second aircraft.
        aircraft2 = new FlightDescriptor("SS9000", new Itinerary("Bridge of Allan", "Stirling", "Glasgow"), new PassengerList());
        AMDatabase.radarDetect(aircraft2);


        //no aircrafts, both gates free
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});

        //1 gate reserved
        GID.allocate(1, 0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.RESERVED});

        //1 gate reserved, 1 gate occupied
        GID.allocate(0, 1);
        GID.docked(1);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.RESERVED, Gate.OCCUPIED});

        //both gates occupied
        GID.docked(0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.OCCUPIED, Gate.OCCUPIED});

        //1 gate free after aircraft departs
        GID.departed(0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.OCCUPIED});

        //both gates free
        GID.departed(1);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});

    }

    /*
     *  We should be able to allocate aircrafts to a given gate.
     */
    @Test
    void allocate() {
        GID.allocate(0, 0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.RESERVED, Gate.FREE});
    }

    /*
     *  What happens if we attempt to allocate an aircraft to an invalid gate?
     */
    @Test
    void allocateInvalidGate1() {
        GID.allocate(GID.maxGateNumber, 0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  What happens if we attempt to allocate an aircraft to an invalid gate?
     */
    @Test
    void allocateInvalidGate2() {
        GID.allocate(-1, 0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  What happens if we attempt to allocate an invalid aircraft to a gate?
     */
    @Test
    void allocateInvalidAircraft1() {
        GID.allocate(0, 3);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  What happens if we attempt to allocate an invalid aircraft to a gate?
     */
    @Test
    void allocateInvalidAircraft2() {
        GID.allocate(0, -1);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  The docked method should allow us to dock an aircraft to a gate it reserved
     */
    @Test
    void docked() {
        GID.allocate(0, 0);
        GID.docked(0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.OCCUPIED, Gate.FREE});
    }

    /*
     *  What happens if we attempt to dock an invalid gate
     */
    @Test
    void dockInvalidGate1(){
        // testing invalid gates
        GID.docked(-1);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  What happens if we attempt to dock an invalid gate
     */
    @Test
    void dockInvalidGate2(){
        // testing invalid gates
        GID.docked(GID.maxGateNumber);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  What happens if we attempt to dock a valid gate but one which has not been allocated to an aircraft
     */
    @Test
    void dockNonExistingAircraft(){
        //while the aircraft exists, it has not been allocated to the gate.
        GID.docked(0);
        //since theres no plane to dock (as there is none reserved), the gate should still be free.
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  The departed method should set a gates status to 0 (FREE)
     */
    @Test
    void departed() {
        GID.allocate(0, 0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.RESERVED, Gate.FREE});
        GID.docked(0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.OCCUPIED, Gate.FREE});
        GID.departed(0);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  What happens if we attempt to depart a plane from a non-existant, invalid gate
     */
    @Test
    void departInvalidGate1(){
        //departing from invalid gates
        GID.departed(-1);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  What happens if we attempt to depart a plane from a non-existant, invalid gate
     */
    @Test
    void departInvalidGate2(){
        //departing from invalid gates
        GID.departed(GID.maxGateNumber);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  What happens if we attempt to depart a non-existent plane from a gate
     */
    @Test
    void departNonExistingAircraft(){
        GID.departed(0);
        //since theres no plane to depart, the gate should still be free.
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.FREE, Gate.FREE});
    }

    /*
     *  Planes should be allocated, docked and then departed.
     *  What happens if we try to make a plane depart when it hasn't been docked?
     */
    @Test
    void departUndockedAircraft(){
        //the plane is allocated but has not been docked yet. what happens when we try to depart the plane?
        GID.allocate(0, 0);
        GID.departed(0);

        assertArrayEquals(GID.getStatuses(), new int[]{Gate.RESERVED, Gate.FREE});
    }

    /*
     *  A gate can only be allocated to a single aircraft.
     *  What happens if we try to allocate a second aircraft?
     */
    @Test
    void overAllocateGate(){
        //we need another aircraft for this test
        aircraft2 = new FlightDescriptor("BR00000", new Itinerary("Falkirk", "Stirling", "Boness"), new PassengerList());
        AMDatabase.radarDetect(aircraft2);

        GID.allocate(0, 0);
        GID.docked(0);
        GID.allocate(0, 1);
        assertArrayEquals(GID.getStatuses(), new int[]{Gate.OCCUPIED, Gate.FREE});
    }
}