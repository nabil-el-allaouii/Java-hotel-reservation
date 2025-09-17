import java.util.Scanner;

import utils.Validator;

import repositories.MemoryClientRepository;
import services.AuthService;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        MemoryClientRepository repo = new MemoryClientRepository();
        AuthService authService = new AuthService(repo);

        boolean running = true;

        while (running) {
            System.out.println("Welcome to Hotel Reservation System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Your Name:");
                    String name = scanner.nextLine();
                    String email;
                    do {
                        System.out.println("Your Email:");
                        email = scanner.nextLine();
                        if (!Validator.isValidEmail(email)) {
                            System.out.println("Invalid email format. Please try again.");
                        }
                    } while (!Validator.isValidEmail(email));
                    String password;
                    do {
                        System.out.println("Your Password:");
                        password = scanner.nextLine();
                        if (!Validator.isValidPassword(password)) {
                            System.out.println("Password must be at least 6 characters long. Please try again.");
                        }

                    } while (!Validator.isValidPassword(password));

                    authService.register(email, password, name);
                    System.out.println("Registration successful Mr/Ms " + name);
                    break;
                case 2:
                    System.out.println("Your Email:");
                    String loginEmail = scanner.nextLine();
                    System.out.println("Your Password:");
                    String loginPassword = scanner.nextLine();
                    if (authService.login(loginEmail, loginPassword) != null) {
                        System.out.println("Login successful. Welcome back!"
                                + authService.login(loginEmail, loginPassword).getName());
                        running = false;
                    } else {
                        System.out.println("Login failed. Please check your credentials.");
                    }
                default:
                    break;
            }
        }
    }
}
