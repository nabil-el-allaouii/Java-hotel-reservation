import java.util.Scanner;

import modal.Client;
import utils.Validator;

import repositories.MemoryClientRepository;
import repositories.MemoryHotelRepository;
import services.AuthService;
import services.HotelService;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        MemoryClientRepository repo = new MemoryClientRepository();
        AuthService authService = new AuthService(repo);

        MemoryHotelRepository hotelrepo = new MemoryHotelRepository();
        HotelService hotelservice = new HotelService(hotelrepo);

        Client loggedInUser = null;
        boolean running = true;

        while (running) {
            if (loggedInUser == null) {
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
                        Client user = authService.login(loginEmail, loginPassword);
                        if (user != null) {
                            System.out.println("Login successful. Welcome back Mr/Ms " + user.getName());
                            loggedInUser = user;

                        } else {
                            System.out.println("Login failed. Please check your credentials.");
                        }
                    default:
                        break;
                }
            } else {
                System.out.println("1. Logout");
                System.out.println("2. Create Hotel");
                System.out.println("3. View All Hotels");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        loggedInUser = null;
                        System.out.println("Logged out successfully.");
                        break;
                    case 2:
                        System.out.println("Enter hotel details:");
                        System.out.println("Enter name:");
                        String hotelname = scanner.nextLine();
                        System.out.println("Enter address:");
                        String address = scanner.nextLine();
                        System.out.println("Enter available rooms:");
                        int availableRooms = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.println("Enter rating:");
                        String ratingInput = scanner.nextLine();
                        double hotelRating = Double.parseDouble(ratingInput);
                        hotelservice.addHotel(hotelname, address, availableRooms, hotelRating);
                        System.out.println("Hotel added successfully.");
                        break;
                    case 3:
                        System.out.println("List of all hotels:");
                        if(hotelservice.getAllHotels().isEmpty()){
                            System.out.println("No hotels available.");
                            break;
                        }
                        for (var hotel : hotelservice.getAllHotels()) {
                            System.out.println("Hotel ID: " + hotel.getId());
                            System.out.println("Name: " + hotel.getName());
                            System.out.println("Address: " + hotel.getAddress());
                            System.out.println("Available Rooms: " + hotel.getAvailableRooms());
                            System.out.println("Rating: " + hotel.getRating());
                            System.out.println("---------------------------");
                        }
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }
}
