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


        if (Login.registerUser(loginName, loginPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Check your username or password");
        }

    }
}

