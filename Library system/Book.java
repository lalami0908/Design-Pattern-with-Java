import java.util.*;

public class Book{
    public int bookId = 0;
    public String author = "";
    public String subject = "";
    public boolean available = true;
    public OrdinaryBorrower lastCheckedOut = null;

    public Book(String author, String subject){
        this.author = author; 
        this.subject = subject;
    }

    // ID: [book_id] Author: [book_author] Subject: [book_subject]
    public String getBookInfo(){
        String bookInfo = "ID: " + this.bookId + " Author: " + this.author + " Subject: " + this.subject;
        return bookInfo;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getSubject(){
        return this.subject;
    }
}