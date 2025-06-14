import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        // Registration phase.
        System.out.println("=== REGISTRATION ===");

        System.out.print("Please Enter your Username: ");
        String username = scanner.nextLine();

        System.out.print("Please Enter your Password: ");
        String password = scanner.nextLine();

        System.out.print("Please Enter your Cell Phone Number: ");
        String phoneNumber = scanner.nextLine();

        // Register the user
        String registrationResult = loginSystem.registerUser(username, password, phoneNumber);
        System.out.println(registrationResult);

        // Only proceed to login if registration was successful
        if(registrationResult.contains("successfully")){

            System.out.println("\n=== COMPLETE REGISTRATION ===");
            System.out.print("Please Enter your First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Please Enter your Surname: ");
            String surname = scanner.nextLine();

            // Store the name and surname in login system
            loginSystem.setUserDetails(firstName, surname);

            System.out.println("\n=== LOGIN ===");
            System.out.print("Name: ");
            String Name = scanner.nextLine();

            System.out.print("Surname: ");
            String surname2 = scanner.nextLine();

            String loginResult = loginSystem.returnLoginStatus(Name, surname2);
            System.out.println(loginResult);

            // If login successful, start messaging system with Part 3 functionality
            if (loginResult.contains("Welcome")) {
                Message messageSystem = new Message();

                messageSystem.showMainMenu(scanner);
            }
        }

        scanner.close();
    }
}