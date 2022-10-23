import javax.swing.*;
import java.awt.*;

/**
 * The Main class.
 *
 * The principal component is the usual main method required by Java application to launch the application.
 *
 * Instantiates the databases.
 * Instantiates and shows all the system interfaces as Frames.
 * @stereotype control
 */
public class Main {


/**
 * Launch SAAMS.
 */


    /**
     * helper method to set the windows in the right places and with the right dimentions
     * It divides the screen in a grid, and we supply coordinates of the window
     * with a grid square as the unit
     * @param window the window to be positions.
     * @param x the x position (from the left) of the window, as a grid square unit
     * @param y the y position (from the top) of the window, as a grid square unit
     * @param width the amount of grid cells for the width
     * @param height the amount of grid cells for the height
     */
    public static void setInGrid(JFrame window, int x, int y, int width, int height) {
    int gridWidth = 6;
    int gridHeight = 6;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int cellWidth = (int) (screenSize.getWidth() / gridWidth);
    int cellHeight = (int) (screenSize.getHeight() / gridHeight);

    window.setBounds(
            cellWidth * x,
            cellHeight * y,
            cellWidth * width,
            cellHeight * height
    );
}

public static void packAll(JFrame... frames) {
    for (JFrame frame : frames)
        frame.pack();
}

public static void main(String[] args) {
  // Instantiate databases
    AircraftManagementDatabase AMDatabase = new AircraftManagementDatabase();
    GateInfoDatabase GIDatabase = new GateInfoDatabase();

    // Instantiate and show all interfaces as Frames
    CleaningSupervisor cleaningGUI = new CleaningSupervisor(AMDatabase);
    MaintenanceInspector MaintenanceGUI = new MaintenanceInspector(AMDatabase); //doesn't appear
    PublicInfo publicInfoGUI = new PublicInfo(AMDatabase);

    GOC GateControlGUI = new GOC(GIDatabase, AMDatabase);
  
    RadarTransceiver RadarTransceiverGUI = new RadarTransceiver(AMDatabase);
    LATC AirTrafficControlGUI = new LATC(AMDatabase);


    //TODO: set the values below appropriately
    //my screen is too small for so many windows to be shown at the same time
    //please, somebody with a good gaming screen, fix! gian

    setInGrid(RadarTransceiverGUI, 0, 0, 3, 2);
    // setInGrid(GateInfoDatabase, 1, 3, 1, 1); // this doesn't seem to be linking correctly in GIDatabase
    //instantiated two gates, change that to try make this work if you like
    setInGrid(MaintenanceGUI, 4, 2, 2, 2);
    setInGrid(AirTrafficControlGUI, 0, 2, 2, 2);
    setInGrid(GateControlGUI, 3, 0, 2, 2);
    //setInGrid(GateDisplayGUI, 4, 1, 2, 1); // apprently this one gets loaded even if commented out, it just doesnt appear on screen
    setInGrid(publicInfoGUI, 2, 2, 2, 2);
    setInGrid(cleaningGUI, 0, 4, 2, 2);
    //setInGrid(RefuellingSupervisorGUI, 4, 2, 1, 1);

    //this resizes all the windows, might be useful in the future
    //packAll(RadarTransceiverGUI,MaintenanceGUI, AirTrafficControlGUI, GateControlGUI);

  }

}