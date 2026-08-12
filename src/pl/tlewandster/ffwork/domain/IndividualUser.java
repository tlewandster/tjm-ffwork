package pl.tlewandster.ffwork.domain;

public class IndividualUser extends User {
    private String pesel;
    private String fullName;

    public IndividualUser(String email, String fullName, String pesel) {
        super(email);
        if(!isValidFullName(fullName)){
            throw new IllegalArgumentException("Full name cannot be blank");
        }
        this.fullName = fullName;
        if(!isValidPesel(pesel)){
            throw new IllegalArgumentException("Invalid PESEL number");
        }
        this.pesel = pesel;
    }

    private boolean isValidPesel(String pesel) {
        return pesel.matches("^\\d{11}$");
    }

    private boolean isValidFullName(String fullName) {
        return fullName != null && !fullName.isBlank();
    }

    //TODO Add the necessary accessors
}
