/**
 * A nurse who works on a ward and carries out tasks such as taking blood samples.
 */
public class Nurse extends HealthProfessional {

    /** Ward this nurse is assigned to. */
    private final String ward;

    /**
     * Creates a nurse with all details supplied.
     *
     * @param id          numeric-only identifier
     * @param name        full name
     * @param workingDays days of the week the nurse works
     * @param ward        ward the nurse is assigned to
     */
    public Nurse(String id, String name, String[] workingDays, String ward) {
        super(id, name, workingDays);
        validateWard(ward);
        this.ward = ward;
    }

    /**
     * Creates a nurse using the default working days.
     *
     * @param id   numeric-only identifier
     * @param name full name
     * @param ward ward the nurse is assigned to
     */
    public Nurse(String id, String name, String ward) {
        super(id, name);
        validateWard(ward);
        this.ward = ward;
    }

    /**
     * Returns the nurse's ward.
     *
     * @return ward name
     */
    public String getWard() {
        return ward;
    }

    @Override
    public String getSpecialty() {
        return "Nurse";
    }

    @Override
    public String describeRole() {
        return "Takes blood samples, monitors patients and supports the medical team on the "
                + ward + " ward.";
    }

    @Override
    public String toString() {
        return super.toString() + " | Ward: " + ward;
    }

    /** Rejects a blank ward name. */
    private void validateWard(String ward) {
        if (ward == null || ward.trim().isEmpty()) {
            throw new IllegalArgumentException("Ward must not be blank.");
        }
    }
}
