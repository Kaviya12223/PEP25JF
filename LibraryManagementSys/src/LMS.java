import java.util.*;
class Books{
    String name;
    String author;
    int id;
    boolean isAvailable;
    public Books(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isAvailable = true;
    }
    @Override
    public String toString() {
        return "Book ID: " + id + ", Name: " + name + ", Author: " + author + ", Available: " + isAvailable;
    }
    //update in registry
    public void updateAvailability(boolean status) {
        this.isAvailable = status;
    }
}
class Users{
    String name;
    int id;
    int limit;
    List<Books> borrowedBooks;
    public Users(int id, String name, int limit) {
        this.id = id;
        this.name = name;
        this.limit = limit;
        this.borrowedBooks = new ArrayList<>();
    }
    static class Student extends Users {
        public Student(int id, String name) {
            super(id, name, 3);
        }
    }
    static class Teacher extends Users {
        public Teacher(int id, String name) {
            super(id, name, 5);
        }
    }
    public void borrowBook(Books book) throws Exception {
        if (borrowedBooks.size() >= limit) {
            throw new Exception("Borrow limit exceeded.");
        }
        if (!book.isAvailable) {
            throw new Exception("Book is already borrowed.");
        }
        book.isAvailable = false;
        borrowedBooks.add(book);
        System.out.println(name + " borrowed: " + book.name);
    }
    public void returnBook(Books book) {
        if (borrowedBooks.remove(book)) {
            book.isAvailable = true;
            System.out.println(name + " returned: " + book.name);
        } else {
            System.out.println(name + " did not borrow: " + book.name);
        }
    }
    @Override
    public String toString() {
        return "User ID: " + id + ", Name: " + name + ", Borrowed Books: " + borrowedBooks.size();
    }
    //to view list books
    public void viewBooks(List<Books> books) {
        for (Books book : books) {
            System.out.println(book);
        }
    }
    //to view remaining lisit of books
    public void viewAvailableBooks(List<Books> books) {
        for (Books book : books) {
            if (book.isAvailable) {
                System.out.println(book);
            }
        }
    }

}


public class LMS {
    public static void main(String[] args) {
        List<Books> books = new ArrayList<>();
        books.add(new Books(1, "1984", "George Orwell"));
        books.add(new Books(2, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Books(3, "The Great Gatsby", "F. Scott Fitzgerald"));

        Users student = new Users.Student(1, "Alice");
        Users teacher = new Users.Teacher(2, "Bob");

        try {
            teacher.viewBooks(books);
            student.borrowBook(books.get(0));
            student.viewAvailableBooks(books);
            teacher.borrowBook(books.get(1));
            student.returnBook(books.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
