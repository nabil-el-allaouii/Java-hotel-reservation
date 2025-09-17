package utils;

public class Validator {
    public static boolean isValidEmail(String email){
        email = email.contains("@") && email.contains(".") ? email : "";
        return !email.isEmpty();
    }
    public static boolean isValidPassword(String password){
        return password.length() >= 6;
    }
}
