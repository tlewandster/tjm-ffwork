package pl.tlewandster.ffwork.domain;

public class CompanyUser extends User {
    private final String taxID;

    public CompanyUser(String email, String companyName, String taxID) {
        super(email, companyName);
        if (!isValidCompanyName(companyName)) {
            throw new IllegalArgumentException("Company name cannot be blank");
        }
        if (!isValidTaxId(taxID)) {
            throw new IllegalArgumentException("Invalid taxID number");
        }
        this.taxID = taxID;
    }

    private boolean isValidCompanyName(String companyName) {
        return companyName != null && !companyName.isBlank();
    }

    private boolean isValidTaxId(String taxID) {
        return taxID.matches("^\\d{3}-?\\d{3}-?\\d{2}-?\\d{2}$");
    }

    @Override
    public String toString() {
        return String.format("%s (%s) NIP:%S", super.getDisplayName(), super.getEmail(), this.taxID);
    }
}