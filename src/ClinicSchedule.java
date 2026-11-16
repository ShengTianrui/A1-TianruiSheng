/**
 * Holds the fixed daily schedule of appointment slots.
 * The clinic runs from 08:00 to 16:00 in 30-minute intervals.
 * Changing this list updates every part of the system that relies on it.
 */
public final class ClinicSchedule {

    /** Appointment labels in time order, one entry per slot. */
    private static final String[] SLOTS = {
            "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
            "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
            "14:00", "14:30", "15:00", "15:30", "16:00"
    };

    /** Private constructor: this class only provides schedule helpers. */
    private ClinicSchedule() {
    }

    /**
     * Checks whether a slot index is part of the daily schedule.
     *
     * @param slot slot index to check
     * @return true if the index is valid
     */
    public static boolean isValidSlot(int slot) {
        return slot >= 0 && slot < SLOTS.length;
    }

    /**
     * Returns the time label for a slot index.
     *
     * @param slot slot index
     * @return time label such as "09:00"
     */
    public static String getSlotLabel(int slot) {
        return SLOTS[slot];
    }

    /**
     * Returns the number of slots in the daily schedule.
     *
     * @return slot count
     */
    public static int slotCount() {
        return SLOTS.length;
    }
}
