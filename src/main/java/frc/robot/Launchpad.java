package frc.robot;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/*
 * This class handles all NetworkTable actions that communicate with the Launchpad
 */
public class Launchpad {

    /* Initialize and define launchpad button variables */
    public static boolean redButton = false;
    public static boolean greenButton = false;
    public static boolean blueButton = false;
    public static boolean yellowButton = false;
    public static boolean spinButton = false;
    public static boolean climbButton1 = false;
    public static boolean climbButton2 = false;

    /* Initialize NetworkTables instance */
    static NetworkTableInstance inst = NetworkTableInstance.getDefault();

    /* Fetch table 'FangsLaunchpad' from NetworkTables */
    static NetworkTable table = inst.getTable("FangsLaunchpad");

    /* Create Listeners for Launchpad */
    public static void init() {
        /* Add listener for red button */
        table.addEntryListener("redButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Red changed to: " + value.getValue());
            redButton = value.getBoolean();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Green button*/
        table.addEntryListener("greenButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Green changed to: " + value.getValue());
            greenButton = value.getBoolean();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Blue button*/
        table.addEntryListener("blueButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Blue changed to: " + value.getValue());
            blueButton = value.getBoolean();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Yellow button*/
        table.addEntryListener("yellowButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Yellow changed to: " + value.getValue());
            yellowButton = value.getBoolean();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Spin button*/
        table.addEntryListener("spinButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Spin changed to: " + value.getValue());
            spinButton = value.getBoolean();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Climb button 1*/
        table.addEntryListener("climbButton1", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Climb 1 changed to: " + value.getValue());
            climbButton1 = value.getBoolean();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Climb button 2 */
        table.addEntryListener("climbButton2", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Climb 2 changed to: " + value.getValue());
            climbButton2 = value.getBoolean();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    }

}