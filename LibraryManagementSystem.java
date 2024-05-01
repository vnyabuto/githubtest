
import java.util.Scanner;
import java.util.ArrayList;

class Book {
    String title;
    String author;
    String isbn;
    int quantity;

    public Book(String title, String author, String isbn, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public String getDetails() {
        return this.title + ", " + this.author + ", " + this.isbn + ", " + this.quantity;
    }
}

class Library {
    ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBook(String isbn) {
        for (Book book : books) {
            if (book.isbn.equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public void printBooks() {
        for (Book book : books) {
            System.out.println(book.getDetails());
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Find Book");
            System.out.println("3. Print All Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Input validation for numeric choice
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.next();
                    System.out.print("Enter author: ");
                    String author = scanner.next();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.next();

                    // Input validation for numeric quantity
                    System.out.print("Enter quantity: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // consume the invalid input
                    }
                    int quantity = scanner.nextInt();

                    Book book = new Book(title, author, isbn, quantity);
                    library.addBook(book);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    System.out.print("Enter ISBN: ");
                    isbn = scanner.next();
                    Book foundBook = library.findBook(isbn);
                    if (foundBook != null) {
                        System.out.println("\nBook Found:");
                        System.out.println(foundBook.getDetails());
                    } else {
                        System.out.println("\nNo book found with this ISBN.");
                    }
                    break;

                case 3:
                    System.out.println("\nList of all books:");
                    library.printBooks();
                    break;

                case 4:
                    System.out.println("\nExiting the system.");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }

        } while (choice != 4);

        scanner.close();
    }
}
