import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibrarySystem {
    // HashMap to store books with title as key and Book object as value
    private static Map<String, Book> library = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    // Book class to store title, author, and quantity
    static class Book {
        String title;
        String author;
        int quantity;

        Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getValidChoice();
            if (choice == 4) {
                System.out.println("Exiting Library System. Goodbye!");
                break;
            }
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    returnBook();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // Display menu options
    private static void displayMenu() {
        System.out.println("\nLibrary System Menu:");
        System.out.println("1. Add Books");
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    // Validate menu choice input
    private static int getValidChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > 4) {
                return -1; // Invalid choice
            }
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Non-integer input
        }
    }

    // Add a book to the library
    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Error: Book title cannot be empty.");
            return;
        }

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            System.out.println("Error: Author name cannot be empty.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity < 0) {
                System.out.println("Error: Quantity cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid quantity. Please enter a valid number.");
            return;
        }

        // Check if book exists (case-insensitive)
        String titleLower = title.toLowerCase();
        if (library.containsKey(titleLower)) {
            Book book = library.get(titleLower);
            book.quantity += quantity;
            System.out.println("Updated quantity for '" + book.title + "' by " + quantity + ". New quantity: " + book.quantity);
        } else {
            library.put(titleLower, new Book(title, author, quantity));
            System.out.println("Added '" + title + "' by " + author + " with quantity " + quantity + " to the library.");
        }
    }

    // Borrow a book from the library
    private static void borrowBook() {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Error: Book title cannot be empty.");
            return;
        }

        System.out.print("Enter number of books to borrow: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) {
                System.out.println("Error: Quantity must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid quantity. Please enter a valid number.");
            return;
        }

        String titleLower = title.toLowerCase();
        if (library.containsKey(titleLower)) {
            Book book = library.get(titleLower);
            if (book.quantity >= quantity) {
                book.quantity -= quantity;
                System.out.println("Successfully borrowed " + quantity + " copy/copies of '" + book.title + "'. Remaining quantity: " + book.quantity);
            } else {
                System.out.println("Error: Only " + book.quantity + " copy/copies of '" + book.title + "' available.");
            }
        } else {
            System.out.println("Error: Book '" + title + "' not found in the library.");
        }
    }

    // Return a book to the library
    private static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Error: Book title cannot be empty.");
            return;
        }

        System.out.print("Enter number of books to return: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity <= 0) {
                System.out.println("Error: Quantity must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid quantity. Please enter a valid number.");
            return;
        }

        String titleLower = title.toLowerCase();
        if (library.containsKey(titleLower)) {
            Book book = library.get(titleLower);
            book.quantity += quantity;
            System.out.println("Successfully returned " + quantity + " copy/copies of '" + book.title + "'. New quantity: " + book.quantity);
        } else {
            System.out.println("Error: Book '" + title + "' not found in the library.");
        }
    }
}