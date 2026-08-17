package pl.tlewandster.ffwork.domain;

public abstract class User {
    private final String email;
    private String displayName;
    public User(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        // TODO Checking the email address in the Users repository
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private boolean isValidEmail(String email) {
        return email.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}");
    }
}
