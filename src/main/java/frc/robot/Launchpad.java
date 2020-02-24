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
    public static boolean autoColorButton = false;
    public static boolean spinButton = false;
    public static boolean adjustCWButton = false;
    public static boolean adjustCCWButton = false; 
    public static boolean spinnerUpButton = false;
    public static boolean spinnerDownButton = false;
    public static boolean climbButton1 = false;
    public static boolean climbButton2 = false;
    public static boolean climbAdjustLeftButton = false;
    public static boolean climbAdjustRightButton = false;
    public static boolean autoShootButton = false;
    public static boolean semiAutoShootButton = false;
    public static boolean semiAutoRevButton = false;
    public static boolean intakeOutButton = false;
    public static boolean intakeInButton = false;
    public static boolean indexerOutButton = false;
    public static boolean indexerInButton = false;
    public static boolean transportOutButton = false;
    public static boolean transportInButton = false;
    public static boolean controlOutButton = false;
    public static boolean controlInButton = false;
    public static boolean manualShootButton = false;
    public static boolean visionOnButton = false;
    public static boolean robotSpinLeftButton = false;
    public static boolean robotSpinRightButton = false;
    public static boolean autoIntakeButton = false;

    /* Initialize NetworkTables instance */
    static NetworkTableInstance inst = NetworkTableInstance.getDefault();

    /* Fetch table 'FangsLaunchpad' from NetworkTables */
    static NetworkTable table = inst.getTable("FangsLaunchpad");
    static NetworkTable visionTable = inst.getTable("ChickenVision");

    /* Create Listeners for Launchpad */
    public static void init() {
        /* Add listener for red button */
        table.addEntryListener("redButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Red changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                redButton = true;
            } else {
                redButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Green button*/
        table.addEntryListener("greenButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Green changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                greenButton = true;
            } else {
                greenButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Blue button*/
        table.addEntryListener("blueButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Blue changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                blueButton = true;
            } else {
                blueButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Yellow button*/
        table.addEntryListener("yellowButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Yellow changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                yellowButton = true;
            } else {
                yellowButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Auto color button*/
        table.addEntryListener("autoColorButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Auto Color changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                autoColorButton = true;
            } else {
                autoColorButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Spin button*/
        table.addEntryListener("spinButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Spin changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                spinButton = true;
            } else {
                spinButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Spinner Up button*/
        table.addEntryListener("spinnerUpButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad spinner up changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                spinnerUpButton = true;
            } else {
                spinnerUpButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Spinner Down button*/
        table.addEntryListener("spinnerDownButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad spinner down changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                spinnerDownButton = true;
            } else {
                spinnerDownButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Adjust CW button*/
        table.addEntryListener("adjustCWButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad adjustCW changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                adjustCWButton = true;
            } else {
                adjustCWButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Adjust CCW button*/
        table.addEntryListener("adjustCCWButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad adjustCCW changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                adjustCCWButton = true;
            } else {
                adjustCCWButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Climb button 1*/
        table.addEntryListener("climbButton1", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Climb 1 changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                climbButton1 = true;
            } else {
                climbButton1 = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for Climb button 2 */
        table.addEntryListener("climbButton2", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Climb 2 changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                climbButton2 = true;
            } else {
                climbButton2 = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

         /* Add listener for Climb adjust left button */
         table.addEntryListener("climbAdjustLeftButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Climb adjust left changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                climbAdjustLeftButton = true;
            } else {
                climbAdjustLeftButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

         /* Add listener for Climb adjust right button */
         table.addEntryListener("climbAdjustRightButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad Climb adjust right changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                climbAdjustRightButton = true;
            } else {
                climbAdjustRightButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for auto shoot button */
        table.addEntryListener("autoShootButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad auto shoot changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                autoShootButton = true;
            } else {
                autoShootButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for semi auto shoot button */
        table.addEntryListener("semiAutoShootButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad semi auto shoot changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                semiAutoShootButton = true;
            } else {
                semiAutoShootButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for semi auto rev button */
        table.addEntryListener("semiAutoRevButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad semi auto rev changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                semiAutoRevButton = true;
            } else {
                semiAutoRevButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for intake out button */
        table.addEntryListener("intakeOutButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad intake out changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                intakeOutButton = true;
            } else {
                intakeOutButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for intake in button */
        table.addEntryListener("intakeInButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad intake in changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                intakeInButton = true;
            } else {
                intakeInButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for indexer out button */
        table.addEntryListener("indexerOutButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad indexer out changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                indexerOutButton = true;
            } else {
                indexerOutButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for indexer in button */
        table.addEntryListener("indexerInButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad indexer in changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                indexerInButton = true;
            } else {
                indexerInButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for transport out button */
        table.addEntryListener("transportOutButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad transport out changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                transportOutButton = true;
            } else {
                transportOutButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for transport in button */
        table.addEntryListener("transportInButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad transport in changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                transportInButton = true;
            } else {
                transportInButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for control out button */
        table.addEntryListener("controlOutButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad control out changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                controlOutButton = true;
            } else {
                controlOutButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for control in button */
        table.addEntryListener("controlInButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad control in changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                controlInButton = true;
            } else {
                controlInButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for manual shoot button */
        table.addEntryListener("manualShootButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad manual shoot changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                manualShootButton = true;
            } else {
                manualShootButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for tape button */
        visionTable.addEntryListener("Tape", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad tape changed to: " + value.getValue());
            visionOnButton = value.getBoolean();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for robot spin to the left button */
        table.addEntryListener("robotSpinLeftButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad robot spin left changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                robotSpinLeftButton = true;
            } else {
                robotSpinLeftButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for robot spin to the right button */
        table.addEntryListener("robotSpinRightButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad robot spin right changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                robotSpinRightButton = true;
            } else {
                robotSpinRightButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        /* Add listener for auto intake button */
        table.addEntryListener("autoIntakeButton", (table, key, entry, value, flags) -> {
            System.out.println("Launchpad auto intake changed to: " + value.getValue());
            if (value.getDouble() == 1.0) {
                autoIntakeButton = true;
            } else {
                autoIntakeButton = false;
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    }

}