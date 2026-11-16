import java.util.Objects;

/**
 * Represents a health professional working at the clinic.
 * This base class holds the details shared by every professional type,
 * and leaves the type-specific details to its subclasses.
 */
public abstract class HealthProfessional {

    /** Days that are accepted as working days in this system. */
    private static final String[] VALID_DAYS = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    /** Working days used when a caller does not supply a list. */
    private static final String[] DEFAULT_DAYS = {"Monday", "Wednesday", "Friday"};

    /** Numeric-only identifier for this professional. */
    private final String id;

    /** Full name of this professional. */
    private String name;

    /** Days of the week this professional works. */
    private String[] workingDays;

    /**
     * Creates a health professional with all shared details supplied.
     *
     * @param id          numeric-only identifier
     * @param name        full name
     * @param workingDays days of the week the professional works
     */
    public HealthProfessional(String id, String name, String[] workingDays) {
        validateId(id);
        validateName(name);
        validateDays(workingDays);

        this.id = id;
        this.name = name;
        this.workingDays = workingDays.clone();
    }

    /**
     * Creates a health professional using the default working days.
     *
     * @param id   numeric-only identifier
     * @param name full name
     */
    public HealthProfessional(String id, String name) {
        this(id, name, DEFAULT_DAYS);
    }

    /**
     * Returns the label that describes this professional's type.
     *
     * @return specialty label
     */
    public abstract String getSpecialty();

    /**
     * Returns a short description of the work this professional does.
     *
     * @return role description
     */
    public abstract String describeRole();

    /**
     * Returns the professional's identifier.
     *
     * @return numeric ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the professional's name.
     *
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the working days so the internal list cannot be
     * changed from outside the class.
     *
     * @return copy of the working days
     */
    public String[] getWorkingDays() {
        return workingDays.clone();
    }

    /**
     * Updates the professional's name after checking it is not blank.
     *
     * @param name new full name
     */
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    /**
     * Checks that two professionals are the same person, using their ID.
     *
     * @param o object to compare against
     * @return true if both professionals share the same ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HealthProfessional that = (HealthProfessional) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code based on the professional's ID.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns the shared details of this professional in a readable form.
     *
     * @return readable summary
     */
    @Override
    public String toString() {
        return "ID: " + id
                + " | Name: " + name
                + " | Specialty: " + getSpecialty()
                + " | Working days: " + String.join(", ", workingDays);
    }

    /** Rejects an ID that is blank or contains anything other than digits. */
    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Professional ID must not be blank.");
        }
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                throw new IllegalArgumentException("Professional ID must contain numbers only.");
            }
        }
    }

    /** Rejects a blank name. */
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Professional name must not be blank.");
        }
    }

    /** Rejects a day list that is empty or contains an unknown day. */
    private void validateDays(String[] days) {
        if (days == null || days.length == 0) {
            throw new IllegalArgumentException("At least one working day must be supplied.");
        }
        for (String day : days) {
            boolean found = false;
            for (String valid : VALID_DAYS) {
                if (valid.equals(day)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Unknown working day: " + day);
            }
        }
    }
}
