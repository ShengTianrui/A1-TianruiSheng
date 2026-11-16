import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Stores and manages all appointments for the current day.
 * Supports adding, filtering, sorting and cancelling appointments,
 * and reports a warning message when an action cannot be performed.
 */
public class AppointmentManager {

    /** All appointments booked for the current day. */
    private final ArrayList<Appointment> appointments;

    /** Creates an empty appointment manager. */
    public AppointmentManager() {
        appointments = new ArrayList<>();
    }

    /**
     * Books a new appointment. The booking is rejected if the slot is
     * invalid or if the professional already has an appointment at that slot.
     *
     * @param patient      patient to book
     * @param professional professional to see
     * @param slot         scheduled slot index
     * @return success or warning message
     */
    public String addAppointment(Patient patient, HealthProfessional professional, int slot) {
        if (patient == null) {
            return "Warning: cannot book, patient details are required.";
        }
        if (professional == null) {
            return "Warning: cannot book, a health professional is required.";
        }
        if (!ClinicSchedule.isValidSlot(slot)) {
            return "Warning: cannot book, the time slot is outside the clinic schedule.";
        }

        for (Appointment existing : appointments) {
            if (existing.getProfessional().equals(professional) && existing.getSlot() == slot) {
                return "Warning: " + professional.getId()
                        + " is already booked at " + ClinicSchedule.getSlotLabel(slot) + ".";
            }
        }

        appointments.add(new Appointment(patient, professional, slot));
        return "Booked " + patient.getName() + " with " + professional.getId()
                + " at " + ClinicSchedule.getSlotLabel(slot) + ".";
    }

    /**
     * Returns all appointments in the order they were added.
     *
     * @return copy of the appointment list
     */
    public ArrayList<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    /**
     * Returns appointments for one professional, matched by ID.
     *
     * @param professionalId ID to match
     * @return matching appointments
     */
    public ArrayList<Appointment> filterByProfessionalId(String professionalId) {
        ArrayList<Appointment> matches = new ArrayList<>();
        if (professionalId == null) {
            return matches;
        }
        String id = professionalId.trim();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(id)) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    /**
     * Returns appointments for one patient, matched by mobile number.
     *
     * @param phone mobile number to match
     * @return matching appointments
     */
    public ArrayList<Appointment> filterByPatientPhone(String phone) {
        ArrayList<Appointment> matches = new ArrayList<>();
        if (phone == null) {
            return matches;
        }
        String mobile = phone.trim();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobile().equals(mobile)) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    /**
     * Returns all appointments sorted from earliest to latest slot.
     *
     * @return sorted copy of the appointment list
     */
    public ArrayList<Appointment> getSortedAppointments() {
        ArrayList<Appointment> sorted = new ArrayList<>(appointments);
        sortByTime(sorted);
        return sorted;
    }

    /**
     * Sorts a list of appointments from earliest to latest slot.
     * This can be applied to the full list or to a filtered subset.
     *
     * @param appointments list to sort
     */
    public static void sortByTime(ArrayList<Appointment> appointments) {
        Collections.sort(appointments);
    }

    /**
     * Cancels the appointment for a professional at a given slot.
     *
     * @param professionalId ID of the professional
     * @param slot            slot index to cancel
     * @return success or warning message
     */
    public String cancelByProfessionalAndSlot(String professionalId, int slot) {
        if (professionalId == null || professionalId.trim().isEmpty()) {
            return "Warning: a professional ID is required.";
        }
        if (!ClinicSchedule.isValidSlot(slot)) {
            return "Warning: cannot cancel, the time slot is outside the clinic schedule.";
        }
        String id = professionalId.trim();
        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getProfessional().getId().equals(id) && appointment.getSlot() == slot) {
                iterator.remove();
                return "Cancelled appointment for " + id
                        + " at " + ClinicSchedule.getSlotLabel(slot) + ".";
            }
        }
        return "Warning: no appointment found for " + id
                + " at " + ClinicSchedule.getSlotLabel(slot) + ".";
    }

    /**
     * Cancels every appointment booked under a patient's mobile number.
     *
     * @param phone mobile number to match
     * @return success or warning message
     */
    public String cancelByPatientPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return "Warning: a patient phone number is required.";
        }
        String mobile = phone.trim();
        int removed = 0;
        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getPatient().getMobile().equals(mobile)) {
                iterator.remove();
                removed++;
            }
        }
        if (removed == 0) {
            return "Warning: no appointment found for " + mobile + ".";
        }
        return "Cancelled " + removed + " appointment(s) for " + mobile + ".";
    }

    /**
     * Returns the number of appointments currently stored.
     *
     * @return appointment count
     */
    public int appointmentCount() {
        return appointments.size();
    }
}
