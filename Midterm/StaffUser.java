import java.util.*;

public class StaffUser extends User {

    public StaffUser(String name) {
        super(name);
    }

    @Override
    public void checkOut(List<Integer> bookId, String userName) {
        if (library.getUser(userName) == null) {
            System.out.println("Error");
            return;
        }
        // if(library.getUser(userName) instanceof OrdinaryBorrower) {
        
        if(library.getUser(userName) instanceof OrdinaryBorrower) {
            OrdinaryBorrower userB = (OrdinaryBorrower) library.getUser(userName);
    // }
            if(bookId.size() > userB.predefined_borrow_book_number)
                System.out.println("Can not check out since the number of books exceed the limitation of user can check-out");
            else {
                library.checkOut(bookId, userB);
            }
        }
    }

    public void findCheckedList(String username) {
        if(library.getUser(username) == null){
            System.out.println("Error");
            return;
        }
        OrdinaryBorrower u = (OrdinaryBorrower) library.getUser(username);
        for(Book book: u.checkedBook) {
            System.out.println(book.getBookInfo());
        }
    }

    public void findLastCheckBrowser(int bookId){
        if (library.getBookByBookId(bookId) == null) {
            System.out.println("Error");
            return;
        }
        Book book = library.books.get(bookId);
        System.out.printf("User: %s", book.lastCheckedOut.name);
    }

    public void removeBook(int bookId) {
        if (library.getBookByBookId(bookId) == null) {
            System.out.println("Error");
            return;
        }
        library.removeBook(bookId);
    }

    public void addBook(String authorName, String subjectName) {
        Book book = new Book(authorName, subjectName);
        library.addBook(book);
    }

    public void returnBook(int bookId) {
        if (library.getBookByBookId(bookId) == null) {
            System.out.println("Error");
            return;
        }
        Book book = library.books.get(bookId);
        OrdinaryBorrower user = book.lastCheckedOut;
        if (book.available)
            System.out.println("Can not return since the book isn't checked out");
        library.returnBook(bookId, user);
    }

  

}