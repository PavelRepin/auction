package training.task.model.impl;

import training.task.model.api.exception.ValidationServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static void isNull(Object... objects) throws ValidationServiceException {
        for (Object ob : objects) {
            if (ob == null) {
                throw new ValidationServiceException("Null object is not allowed.");
            }
        }
    }

    public static void isEmptyString(String... strings) throws ValidationServiceException {
        for (String string : strings) {
            if (string.isEmpty()) {
                throw new ValidationServiceException("Empty string is not allowed.");
            }
        }
    }

    public static void isAdult(int... ages) throws ValidationServiceException {
        for (int age : ages) {
            if (age < 18) {
                throw new ValidationServiceException("Too young to use auction.");
            }
        }
    }

    public static void matchDate(String... dates) throws ValidationServiceException {
        Pattern pattern = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12]\\d|3[01])$");
        try {
            for (String date : dates) {
                Matcher matcher = pattern.matcher(date);
                if (!matcher.matches()) {
                    throw new ValidationServiceException("Incorrect date type, insert it like this: YYYY-MM-DD.");
                }
            }
        } catch (NullPointerException e) {
            throw new ValidationServiceException(e);
        }
    }

    public static void matchCorrectLogin(String... logins) throws ValidationServiceException {
        Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{4,}$");
        for (String login : logins) {
            Matcher matcher = pattern.matcher(login);
            if (!matcher.matches()) {
                throw new ValidationServiceException("Incorrect login.");
            }
        }
    }

    public static void matchSecurePassword(String... passwords) throws ValidationServiceException {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        for (String pass : passwords) {
            Matcher matcher = pattern.matcher(pass);
            if (!matcher.matches()) {
                throw new ValidationServiceException("Insecure password.");
            }
        }
    }

    public static void matchEmail(String... emails) throws ValidationServiceException {
        Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w{2,}");
        for (String email : emails) {
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new ValidationServiceException("Incorrect email.");
            }
        }
    }
}
