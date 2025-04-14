import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter your Name");
        String name = scanner.nextLine();

        System.out.println("Please Enter your Password");
        String password = scanner.nextLine();

        System.out.println("Please Enter your Number");
        String phoneNumber = scanner.nextLine();

        //ask for login details
        System.out.println("Please enter your login details:");
        System.out.println("Username: ");
        String loginName = scanner.nextLine();
        System.out.println("Password: ");
        String loginPassword = scanner.nextLine();

//decision structure is used to verify user authentication
        if (registerUser(loginName, loginPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Check your username or password");
        }
    }

    //this checks username underscore and max length
    public static boolean checkUserName(String name) {
        return name.contains("_") && name.length() <= 5;
    }


    //password meets complexity requirements
    public static boolean checkPasswordComplexity(String password) {
        return password.matches(".*[A-Z].*") && //atleast one upper case letter
                password.matches(".*[0-9].*") &&//at least one number
                password.matches(".*[_].*") &&//atleast ones underscore
                password.length() >= 8;//MINIMUM length of 8 characters
    }

    //register user
    public static boolean registerUser(String name, String password) {
        return name.contains(name) && checkPasswordComplexity(password);
    }


    //cellphone number with regex for 10 digits
    public static boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10,}");//this will check if it has atleast 10 digits and digits only
    }


    void Login() {

        //username and password for testing
        String testName = "katleho_";
        String testPassword = "Pass_123";
        String testPhone = "0812345678";


        boolean isValidUsername = checkUserName(testName);
        boolean isValidPassword = checkPasswordComplexity(testPassword);
        boolean isValidPhone = checkCellPhoneNumber(testPhone);
        boolean isValidRegistered = registerUser(testName, testPassword);


        //system responds with confirmation and error messages
        if (isValidUsername) {//this if statement condition checks if the username is to according to its specifications
            System.out.println("Username Successfully Captured");
        } else {
            System.out.println("Username is not correctly formatted ,please ensure that your username contains an " +
                    "underscore and is no more than five characters in length.");

        }

        if (isValidPassword) {//checks if password is valid
            System.out.println("Password Successfully Captured");
        } else {
            System.out.println("Password is not correctly formatted ,please ensure that your password contains at least " +
                    "eight characters a capital letter,a number and a special character.");


        }

        if (isValidPhone) {//checks if phone number is valid
            System.out.println("correct cellphone insertation");
        } else {
            System.out.println("TRY AGAIN invalid phone number.");

        }


        if (isValidRegistered) {//checks is it is a valid registration
            System.out.println("you have been registered successfully");
        } else {
            System.out.println("incorrect TRY AGAIN.");

        }
        Login check = new Login();
        check.Login();

    }
}
