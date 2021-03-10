package me.parkprin.assignment.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.regex.Pattern.matches;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ValidationUtils {

    public static String emailValidation(String email){
        checkArgument(isNotEmpty(email), "address must be provided");
        checkArgument(
                email.length() >= 4 && email.length() <= 50,
                "address length must be between 4 and 50 characters"
        );
        checkArgument(matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", email),
                "Invalid email address: " + email);
        return email;
    }
}
