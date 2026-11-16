import java.util.ArrayList;

/**
 * Demonstration class for the clinic appointment system.
 * The main method creates the professionals and exercises the
 * appointment manager, covering both successful and rejected actions.
 */
public class AssignmentOne {

    /**
     * Runs the demonstration.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ArrayList<HealthProfessional> professionals = buildProfessionals();

        printHeader("Part 3: Health Professionals");
        for (HealthProfessional professional : professionals) {
            System.out.println(professional);
            System.out.println("    Role: " + professional.describeRole());
        }

        AppointmentManager manager = new AppointmentManager();

        Patient jack = new Patient("Jack Martin", "0412345678");
        Patient sarah = new Patient("Sarah Wilson", "0423456789");
        Patient mike = new Patient("Mike Taylor", "0434567890");
        Patient lisa = new Patient("Lisa Moore", "0445678901");

        printHeader("Part 6: Booking Appointments");
        System.out.println(manager.addAppointment(jack, findById(professionals, "101"), 2));
        System.out.println(manager.addAppointment(sarah, findById(professionals, "201"), 4));
        System.out.println(manager.addAppointment(mike, findById(professionals, "301"), 1));
        System.out.println(manager.addAppointment(lisa, findById(professionals, "401"), 6));

        System.out.println("-- Rejected bookings --");
        System.out.println(manager.addAppointment(mike, findById(professionals, "101"), 2));
        System.out.println(manager.addAppointment(sarah, findById(professionals, "102"), 20));

        printHeader("All Appointments");
        printAppointments(manager.getAllAppointments());

        printHeader("Filter: Professional 101");
        ArrayList<Appointment> byProfessional = manager.filterByProfessionalId("101");
        AppointmentManager.sortByTime(byProfessional);
        printAppointments(byProfessional);

        printHeader("Filter: Patient phone 0423456789");
        ArrayList<Appointment> byPhone = manager.filterByPatientPhone("0423456789");
        AppointmentManager.sortByTime(byPhone);
        printAppointments(byPhone);

        printHeader("Appointments Sorted by Time");
        printAppointments(manager.getSortedAppointments());

        printHeader("Cancelling Appointments");
        System.out.println(manager.cancelByProfessionalAndSlot("101", 2));
        System.out.println(manager.cancelByPatientPhone("0434567890"));
        System.out.println(manager.cancelByProfessionalAndSlot("999", 5));
        System.out.println(manager.cancelByPatientPhone("0499999999"));

        printHeader("Appointments After Cancellation");
        printAppointments(manager.getSortedAppointments());
        System.out.println("Appointments remaining: " + manager.appointmentCount());
    }

    /**
     * Builds the mix of professionals used in the demonstration.
     *
     * @return list of professionals
     */
    private static ArrayList<HealthProfessional> buildProfessionals() {
        ArrayList<HealthProfessional> professionals = new ArrayList<>();

        professionals.add(new GeneralPractitioner("101", "Alice Lee",
                new String[]{"Monday", "Tuesday", "Thursday"}, 12));
        professionals.add(new GeneralPractitioner("102", "Brian Chen", 8));
        professionals.add(new GeneralPractitioner("103", "Carmen Diaz",
                new String[]{"Monday", "Wednesday", "Friday"}, 15));

        professionals.add(new Specialist("201", "David Wu",
                new String[]{"Tuesday", "Thursday"}, "Cardiologist"));
        professionals.add(new Specialist("202", "Ella Smith", "Radiologist"));

        professionals.add(new Nurse("301", "Fiona Brown",
                new String[]{"Monday", "Tuesday", "Wednesday"}, "Emergency"));
        professionals.add(new Nurse("302", "George King", "Paediatrics"));

        professionals.add(new Dietitian("401", "Hannah Zhao",
                new String[]{"Wednesday", "Friday"}, "Sports nutrition"));

        return professionals;
    }

    /**
     * Finds a professional in the list by ID.
     *
     * @param professionals list to search
     * @param id            ID to match
     * @return matching professional
     */
    private static HealthProfessional findById(ArrayList<HealthProfessional> professionals, String id) {
        for (HealthProfessional professional : professionals) {
            if (professional.getId().equals(id)) {
                return professional;
            }
        }
        throw new IllegalArgumentException("No professional found with ID " + id);
    }

    /**
     * Prints a section header.
     *
     * @param title header text
     */
    private static void printHeader(String title) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(title);
        System.out.println("==================================================");
    }

    /**
     * Prints each appointment in a list, or a message when the list is empty.
     *
     * @param appointments list to print
     */
    private static void printAppointments(ArrayList<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("(no appointments)");
            return;
        }
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}
