package pl.tlewandster.ffwork.domain;

public class CompanyUser extends User {
    private final String taxId;

    public CompanyUser(String email, String companyName, String taxId) {
        super(email, companyName);
        if (!isValidCompanyName(companyName)) {
            throw new IllegalArgumentException("Company name cannot be blank");
        }
        if (!isValidTaxId(taxId)) {
            throw new IllegalArgumentException("Invalid taxID number");
        }
        this.taxId = taxId;
    }

    private boolean isValidCompanyName(String companyName) {
        return companyName != null && !companyName.isBlank();
    }

    private boolean isValidTaxId(String taxID) {
        return taxID.matches("^\\d{3}-?\\d{3}-?\\d{2}-?\\d{2}$");
    }

    @Override
    public String toString() {
        return String.format("%s (%s) NIP:%S", super.getDisplayName(), super.getEmail(), this.taxId);
    }
}