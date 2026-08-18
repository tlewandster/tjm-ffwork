package pl.tlewandster.ffwork.domain;

public abstract class User {
    private final String email;
    private final String displayName;
    public User(String email, String displayName) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    private boolean isValidEmail(String email) {
        return email.matches("[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}");
    }
}
