public class Login extends Main {

    void Login() {

        //username and password for testing
        String testName="katleho_";
        String testPassword="Pass_123";
        String testPhone="0812345678";


        boolean isValidUsername=checkUserName(testName);
        boolean isValidPassword=checkPasswordComplexity(testPassword);
        boolean isValidPhone=checkCellPhoneNumber(testPhone);
        boolean isValidRegistered=registerUser(testName,testPassword);



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
            Login check=new Login();
            check.Login();

        }

    }
