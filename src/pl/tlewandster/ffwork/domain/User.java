package pl.tlewandster.ffwork.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {
    private final String email;
    private String displayName;

    public User(String email) {
        if (!isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email address");
        }
        // TODO Checking the email address in the Users repository
        this.email = email;
    }

    private boolean isValidEmail(String email){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    //TODO Add the necessary accessors
}
