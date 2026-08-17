package pl.tlewandster.ffwork.domain;

public class IndividualUser extends User {
    private final String pesel;

    public IndividualUser(String email, String fullName, String pesel) {
        super(email, fullName);
        if (!isValidFullName(fullName)) {
            throw new IllegalArgumentException("Full name cannot be blank");
        }
        if (!isValidPesel(pesel)) {
            throw new IllegalArgumentException("Invalid PESEL number");
        }
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) PESEL: %S", super.getDisplayName(), super.getEmail(), this.pesel);
    }

    private boolean isValidPesel(String pesel) {
        return pesel.matches("^\\d{11}$");
    }

    private boolean isValidFullName(String fullName) {
        return fullName != null && !fullName.isBlank();
    }
}
