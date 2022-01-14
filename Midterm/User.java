import java.io.*;
import java.util.*;

public abstract class User {
    public String name = "";

    public Library library;

    public User(String name) {
        this.name = name;
    }

    public void checkOut(List<Integer> books, String username){
        System.out.println("Borrower can not check out the books");
        for (int id : books){
            Book book = library.getBookByBookId(id);
            if(book == null || book.available == false){
                System.out.println("Error");
            }
        }
        if (library.getUser(username) == null)
            System.out.println("Error");
    }

    public abstract void findCheckedList(String username);

    public void listSubject(String subject) {
        library.listSubject(subject); 
    }

    public void listAuthor(String author) {
        library.listAuthor(author);
    }
    
    public void findLastCheckBrowser(int book_id){
        System.out.println("Borrower can not find borrower");
        if (library.getBookByBookId(book_id) == null) 
            System.out.println("Error");
    }

    public void removeBook(int book_id){
        System.out.println("Borrower can not remove book");
        if (library.getBookByBookId(book_id) == null) 
            System.out.println("Error");
    }

    public void returnBook(int book_id) {
        System.out.println("Borrower can not return book");
        if (library.getBookByBookId(book_id) == null) 
            System.out.println("Error");
    }

    public void addBook(String authorName, String subjectName) {
        System.out.println("Borrower can not add book");
    }
}