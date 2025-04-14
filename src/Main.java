import java.util.Scanner;

public class Main {

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
    public static boolean registerUser (String name, String password){
        return name.contains(name) && checkPasswordComplexity(password);
    }


    //cellphone number with regex for 10 digits
    public static boolean checkCellPhoneNumber (String phoneNumber){
        return phoneNumber.matches("\\d{10,}");//this will check if it has atleast 10 digits and digits only
    }

}