/**
 * A dietitian who gives nutrition and diet advice to patients.
 */
public class Dietitian extends HealthProfessional {

    /** Main nutrition area this dietitian focuses on. */
    private final String focusArea;

    /**
     * Creates a dietitian with all details supplied.
     *
     * @param id          numeric-only identifier
     * @param name        full name
     * @param workingDays days of the week the dietitian works
     * @param focusArea   main nutrition focus, for example "Sports nutrition"
     */
    public Dietitian(String id, String name, String[] workingDays, String focusArea) {
        super(id, name, workingDays);
        validateFocusArea(focusArea);
        this.focusArea = focusArea;
    }

    /**
     * Creates a dietitian using the default working days.
     *
     * @param id        numeric-only identifier
     * @param name      full name
     * @param focusArea main nutrition focus, for example "Sports nutrition"
     */
    public Dietitian(String id, String name, String focusArea) {
        super(id, name);
        validateFocusArea(focusArea);
        this.focusArea = focusArea;
    }

    /**
     * Returns the dietitian's nutrition focus area.
     *
     * @return focus area
     */
    public String getFocusArea() {
        return focusArea;
    }

    @Override
    public String getSpecialty() {
        return "Dietitian";
    }

    @Override
    public String describeRole() {
        return "Plans nutrition and diet advice for patients focusing on " + focusArea + ".";
    }

    @Override
    public String toString() {
        return super.toString() + " | Focus: " + focusArea;
    }

    /** Rejects a blank focus area. */
    private void validateFocusArea(String focusArea) {
        if (focusArea == null || focusArea.trim().isEmpty()) {
            throw new IllegalArgumentException("Focus area must not be blank.");
        }
    }
}
