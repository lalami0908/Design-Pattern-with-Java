import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class OrdinaryBorrower extends User {
    
    public int predefined_borrow_book_number;
    public ArrayList<Book> checkedBook = new ArrayList<Book>();

    public OrdinaryBorrower(String name, int predefined_borrow_book_number) {
        super(name);
        this.predefined_borrow_book_number = predefined_borrow_book_number;
    }

    public void findCheckedList(String username) {
        User user = library.getUser(username);
        if(user == null) {
            System.out.println("Error");
            return;
        }
        if(user.name.equals(this.name)){
            for(Book book: checkedBook){
                System.out.println(book.getBookInfo());
            }
        }
        else{
            System.out.println("Borrower can not find books checked out by other users");
        }
    }
}