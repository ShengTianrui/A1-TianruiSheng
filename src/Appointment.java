/**
 * A single appointment between a patient and a chosen health professional
 * at one slot in the daily schedule. Appointments can be ordered by time.
 */
public class Appointment implements Comparable<Appointment> {

    /** Patient booked for this appointment. */
    private final Patient patient;

    /** Professional the patient is seeing. */
    private final HealthProfessional professional;

    /** Index of the scheduled slot in the clinic schedule. */
    private final int slot;

    /**
     * Creates an appointment after checking that all required details are present.
     *
     * @param patient      patient to book
     * @param professional professional to see
     * @param slot         scheduled slot index
     */
    public Appointment(Patient patient, HealthProfessional professional, int slot) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient details are required.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("A health professional is required.");
        }
        if (!ClinicSchedule.isValidSlot(slot)) {
            throw new IllegalArgumentException("Invalid appointment time slot.");
        }
        this.patient = patient;
        this.professional = professional;
        this.slot = slot;
    }

    /**
     * Returns the booked patient.
     *
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the booked professional.
     *
     * @return professional
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the scheduled slot index.
     *
     * @return slot index
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Orders appointments from earliest to latest slot.
     *
     * @param other appointment to compare against
     * @return negative, zero, or positive depending on the slot order
     */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.slot, other.slot);
    }

    /**
     * Returns the appointment details in a readable form.
     *
     * @return readable summary
     */
    @Override
    public String toString() {
        return "At " + ClinicSchedule.getSlotLabel(slot)
                + " with " + professional.getSpecialty()
                + " (" + professional.getId() + ") " + professional.getName()
                + " | Patient: " + patient;
    }
}
