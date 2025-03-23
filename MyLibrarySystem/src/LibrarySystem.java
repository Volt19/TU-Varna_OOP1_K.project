import models.*;

import java.io.*;
import java.util.*;


/**
 * Main method of the program
 */

public class LibrarySystem {
    private static final String BOOKS_FILE = "books.dat";
    private static final String USERS_FILE = "users.dat";
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Runnable> commands = new HashMap<>();

    public LibrarySystem() {
        loadBooks();
        loadUsers();
        initializeCommands();
    }

    private void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOKS_FILE))) {
            books = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            books = new ArrayList<>();
        }
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new ArrayList<>();
        }
    }

    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }

    private void initializeCommands() {
        commands.put("help", this::help);
        commands.put("register", this::registerUser);
        commands.put("login", this::loginUser);
        commands.put("addbook", this::addBook);
        commands.put("listbooks", this::listBooks);
        commands.put("exit", () -> System.exit(0));
    }

    private void help() {
        System.out.print("register ");
        System.out.print("login ");
        System.out.print("addbook ");
        System.out.println("listbooks ");

    }

    private void registerUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Admin? (true/false): ");
        boolean isAdmin = Boolean.parseBoolean(scanner.nextLine());
        users.add(new User(username, password, isAdmin));
        saveUsers();
        System.out.println("User registered.");
    }

    private void loginUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    private void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Rating: ");
        double rating = Double.parseDouble(scanner.nextLine());
        System.out.print("Keywords (comma-separated): ");
        List<String> keywords = Arrays.asList(scanner.nextLine().split(","));
        books.add(new Book(books.size() + 1, author, title, genre, description, year, keywords, rating));
        saveBooks();
        System.out.println("Book added.");
    }

    private void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void run() {
        System.out.println("Welcome to Library System. Type 'help' for a list of commands.");
        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine().trim().toLowerCase();
            commands.getOrDefault(command, () -> System.out.println("Unknown command. Type 'help' for a list of commands.")).run();
        }
    }

    public static void main(String[] args) {
        new LibrarySystem().run();
    }
}
