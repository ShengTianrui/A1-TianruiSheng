/**
 * A general practitioner who treats common illnesses and prescribes medication.
 */
public class GeneralPractitioner extends HealthProfessional {

    /** Years spent practising as a GP. */
    private final int yearsExperience;

    /**
     * Creates a general practitioner with all details supplied.
     *
     * @param id              numeric-only identifier
     * @param name            full name
     * @param workingDays     days of the week the GP works
     * @param yearsExperience years of general practice experience
     */
    public GeneralPractitioner(String id, String name, String[] workingDays, int yearsExperience) {
        super(id, name, workingDays);
        validateExperience(yearsExperience);
        this.yearsExperience = yearsExperience;
    }

    /**
     * Creates a general practitioner using the default working days.
     *
     * @param id              numeric-only identifier
     * @param name            full name
     * @param yearsExperience years of general practice experience
     */
    public GeneralPractitioner(String id, String name, int yearsExperience) {
        super(id, name);
        validateExperience(yearsExperience);
        this.yearsExperience = yearsExperience;
    }

    /**
     * Returns the GP's years of experience.
     *
     * @return years of experience
     */
    public int getYearsExperience() {
        return yearsExperience;
    }

    @Override
    public String getSpecialty() {
        return "General Practitioner";
    }

    @Override
    public String describeRole() {
        return "Prescribes medication and treats common, non-specialist conditions.";
    }

    @Override
    public String toString() {
        return super.toString() + " | Experience: " + yearsExperience + " years";
    }

    /** Rejects a negative experience value. */
    private void validateExperience(int yearsExperience) {
        if (yearsExperience < 0) {
            throw new IllegalArgumentException("Years of experience cannot be negative.");
        }
    }
}
