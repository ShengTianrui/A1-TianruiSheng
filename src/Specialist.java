/**
 * A specialist who focuses on a particular area of medicine,
 * such as cardiology or radiology.
 */
public class Specialist extends HealthProfessional {

    /** Area of medicine this specialist works in. */
    private final String subSpecialty;

    /**
     * Creates a specialist with all details supplied.
     *
     * @param id           numeric-only identifier
     * @param name         full name
     * @param workingDays  days of the week the specialist works
     * @param subSpecialty area of medicine, for example "Cardiologist"
     */
    public Specialist(String id, String name, String[] workingDays, String subSpecialty) {
        super(id, name, workingDays);
        validateSubSpecialty(subSpecialty);
        this.subSpecialty = subSpecialty;
    }

    /**
     * Creates a specialist using the default working days.
     *
     * @param id           numeric-only identifier
     * @param name         full name
     * @param subSpecialty area of medicine, for example "Cardiologist"
     */
    public Specialist(String id, String name, String subSpecialty) {
        super(id, name);
        validateSubSpecialty(subSpecialty);
        this.subSpecialty = subSpecialty;
    }

    /**
     * Returns the specialist's area of medicine.
     *
     * @return sub-specialty
     */
    public String getSubSpecialty() {
        return subSpecialty;
    }

    @Override
    public String getSpecialty() {
        return "Specialist (" + subSpecialty + ")";
    }

    @Override
    public String describeRole() {
        return "Provides specialist diagnosis and treatment as a " + subSpecialty + ".";
    }

    @Override
    public String toString() {
        return super.toString() + " | Sub-specialty: " + subSpecialty;
    }

    /** Rejects a blank sub-specialty. */
    private void validateSubSpecialty(String subSpecialty) {
        if (subSpecialty == null || subSpecialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Sub-specialty must not be blank.");
        }
    }
}
