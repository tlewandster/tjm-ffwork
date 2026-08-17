package pl.tlewandster.ffwork.domain;

public class CompanyUser extends User {
    private String companyName;
    private String taxID;

    public CompanyUser(String email, String companyName, String taxID) {
        super(email);
        if(!isValidCompanyName(companyName)){
            throw new IllegalArgumentException("Company name cannot be blank");
        }
        this.companyName = companyName;
        if(!isValidTaxId(taxID)){
            throw new IllegalArgumentException("Invalid taxID number");
        }
        this.taxID = taxID;
    }

    private boolean isValidTaxId(String taxID) {
        return taxID.matches("^\\d{3}-?\\d{3}-?\\d{2}-?\\d{2}$");
    }

    private boolean isValidCompanyName(String companyName) {
        return companyName != null && !companyName.isBlank();
    }

    //TODO Add the necessary accessors
}
