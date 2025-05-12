public class Login extends Main {



    public void Login() {


        //username and password for testing
        String testName = "kat_";
        String testPassword = "Pass_123";
        String testPhone = "0812345678";


        boolean checkUserName = checkUserName(testName);
        boolean checkPasswordComplexity = checkPasswordComplexity(testPassword);
        boolean checkCellPhoneNumber = checkCellPhoneNumber(testPhone);
        boolean registerUser = registerUser(testName, testPassword);


        //system responds with confirmation and error messages
        if (checkUserName) {//this if statement condition checks if the username is to according to its specifications
            System.out.println("Username Successfully Captured");
        } else {
            System.out.println("Username is not correctly formatted ,please ensure that your username contains an " +
                    "underscore and is no more than five characters in length.");

        }

        if (checkPasswordComplexity) {//checks if password is valid
            System.out.println("Password Successfully Captured");
        } else {
            System.out.println("Password is not correctly formatted ,please ensure that your password contains at least " +
                    "eight characters a capital letter,a number and a special character.");


        }

        if (checkCellPhoneNumber) {//checks if phone number is valid
            System.out.println("correct cellphone insertation");
        } else {
            System.out.println("TRY AGAIN invalid phone number.");

        }


        if (registerUser) {//checks is it is a valid registration
            System.out.println("you have been registered successfully");
        } else {
            System.out.println("incorrect TRY AGAIN.");

        }


        Login check = new Login();

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
        return checkUserName(name) && checkPasswordComplexity(password);
    }


    //cellphone number checker with regex for 10 digits implemented
    public static boolean checkCellPhoneNumber (String phoneNumber){
        return phoneNumber.matches("\\d{10,}");//this will check if it has atleast 10 digits and digits only
    }

}






