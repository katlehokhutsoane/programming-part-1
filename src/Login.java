// Login.java
public class Login {
    private String registeredUsername;
    private String registeredPassword;
    private String registeredPhoneNumber;
    private String registeredname;
    private String registeredsurname;

    public static boolean isValidNameOrSurname(String input){
        return input.matches("[A-Za-z]{2,}");//only letters minimum 2 charactrs
    }



    // Method to check if username contains underscore and is no more than 5 characters
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Method to check password complexity
    public static boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // Method to check cell phone number format (international code + 10 digits)
    public static boolean checkCellPhoneNumber(String phoneNumber) {
        // Check for international format: +XX followed by 10 digits
        if (phoneNumber.length() == 13 && phoneNumber.startsWith("+") &&
                phoneNumber.substring(1).matches("\\d{12}")) {
            return true;
        }
        // Also accept format like +27718693002 (12 characters total)
        if (phoneNumber.length() == 12 && phoneNumber.startsWith("+") &&
                phoneNumber.substring(1).matches("\\d{11}")) {
            return true;
        }
        return false;
    }

    // Method to register user and return appropriate message
    public String registerUser(String username, String password, String phoneNumber) {

        boolean validUsername = checkUserName(username);
        boolean validPassword = checkPasswordComplexity(password);
        boolean validPhone = checkCellPhoneNumber(phoneNumber);



        if (!validUsername) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!validPassword) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!validPhone) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // Store registration details

        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredPhoneNumber = phoneNumber;

        return "The two above conditions have been met, and the user has been registered successfully.";
    }

    // Method to verify login credentials
    public boolean loginUser(String name,String surname) {
        return name.equals(registeredname) && surname.equals(registeredsurname);
    }

    // Method to return login status message
    public String returnLoginStatus(String name,String surname) {
        if(!isValidNameOrSurname(name)||!isValidNameOrSurname(surname)) {
            return "invalid name or surname entered. Please enter alphabetic characters only";

        }
        return "Welcome\t" +name +",its is great to see you again.";
    }
}