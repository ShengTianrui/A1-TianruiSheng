/**
 * A patient who attends the clinic, recorded by name and mobile number.
 */
public class Patient {

    /** Patient's full name. */
    private final String name;

    /** Patient's mobile phone number, digits only. */
    private final String mobile;

    /**
     * Creates a patient after checking the contact details are valid.
     *
     * @param name   full name
     * @param mobile mobile phone number (10 digits)
     */
    public Patient(String name, String mobile) {
        validateName(name);
        validateMobile(mobile);
        this.name = name;
        this.mobile = mobile;
    }

    /**
     * Returns the patient's name.
     *
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patient's mobile number.
     *
     * @return mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Returns the patient's details in a readable form.
     *
     * @return readable summary
     */
    @Override
    public String toString() {
        return "Name: " + name + " | Mobile: " + mobile;
    }

    /** Rejects a blank name. */
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name must not be blank.");
        }
    }

    /** Rejects a mobile number that is not 10 digits. */
    private void validateMobile(String mobile) {
        if (mobile == null || mobile.length() != 10) {
            throw new IllegalArgumentException("Mobile number must be 10 digits.");
        }
        for (int i = 0; i < mobile.length(); i++) {
            if (!Character.isDigit(mobile.charAt(i))) {
                throw new IllegalArgumentException("Mobile number must contain digits only.");
            }
        }
    }
}
