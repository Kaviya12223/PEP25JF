import java.util.*;

// Custom Exception
class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

// Book class
class Book {
    String title;
    String author;
    int id;
    boolean isBorrowed;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String toString() {
        return id + " - " + title + " by " + author + (isBorrowed ? " [Borrowed]" : "");
    }
}

// Abstract User class
abstract class User {
    String name;
    int id;
    List<Book> borrowedBooks = new ArrayList<>();
    int borrowLimit;

    public User(int id, String name, int limit) {
        this.id = id;
        this.name = name;
        this.borrowLimit = limit;
    }

    public void borrowBook(Book b) throws Exception {
        if (borrowedBooks.size() >= borrowLimit) {
            throw new Exception("Borrow limit exceeded.");
        }
        if (b.isBorrowed) {
            throw new Exception("Book already borrowed.");
        }
        b.isBorrowed = true;
        borrowedBooks.add(b);
        System.out.println(name + " borrowed: " + b.title);
    }

    public void returnBook(Book b) {
        if (borrowedBooks.remove(b)) {
            b.isBorrowed = false;
            System.out.println(name + " returned: " + b.title);
        }
    }
}

// Subclasses: Student and Teacher
class Student extends User {
    public Student(int id, String name) {
        super(id, name, 3);
    }
}

class Teacher extends User {
    public Teacher(int id, String name) {
        super(id, name, 5);
    }
}

// Admin class
class Admin {
    public void addBook(Library lib, Book b) {
        lib.books.put(b.id, b);
        System.out.println("Book added: " + b.title);
    }

    public void removeBook(Library lib, int bookId) {
        lib.books.remove(bookId);
        System.out.println("Book removed.");
    }

    public void viewBooks(Library lib) {
        for (Book b : lib.books.values()) {
            System.out.println(b);
        }
    }
}

// Library class
class Library {
    Map<Integer, Book> books = new HashMap<>();
    List<User> users = new ArrayList<>();

    public Book findBook(int id) throws BookNotFoundException {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book ID not found: " + id);
        }
        return books.get(id);
    }

    public void registerUser(User u) {
        users.add(u);
        System.out.println("User registered: " + u.name);
    }
}

// Optional: Multithreaded access simulation
class UserAction implements Runnable {
    User user;
    Library lib;
    int bookId;

    public UserAction(User user, Library lib, int bookId) {
        this.user = user;
        this.lib = lib;
        this.bookId = bookId;
    }

    public void run() {
        try {
            Book b = lib.findBook(bookId);
            user.borrowBook(b);
            Thread.sleep(1000);
            user.returnBook(b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

// Main driver
public class LibraryManagementSystem {
    public static void main(String[] args) throws Exception {
        Library lib = new Library();
        Admin admin = new Admin();

        // Add books
        admin.addBook(lib, new Book(101, "Java Basics", "John"));
        admin.addBook(lib, new Book(102, "Python", "Sara"));
        admin.addBook(lib, new Book(103, "C++", "Mike"));

        // Register users
        User s1 = new Student(1, "Alice");
        User t1 = new Teacher(2, "Bob");
        lib.registerUser(s1);
        lib.registerUser(t1);

        // View books
        admin.viewBooks(lib);

        // Borrow & return
        Book bookToBorrow = lib.findBook(101);
        s1.borrowBook(bookToBorrow);
        s1.returnBook(bookToBorrow);

        // Multithreading simulation
        Thread tA = new Thread(new UserAction(t1, lib, 102));
        Thread tB = new Thread(new UserAction(s1, lib, 102));
        tA.start();
        tB.start();
        tA.join();
        tB.join();
    }
}
