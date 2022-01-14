import java.util.*;

public class Library{
    public ArrayList<User> users = new ArrayList<>();
    public HashMap<Integer, Book> books = new HashMap<>();
    public HashMap<String, ArrayList<Book>> booksByAuthor = new LinkedHashMap<>();
    public HashMap<String, ArrayList<Book>> booksBySubject = new LinkedHashMap<>();
    
    private int bookId = 0;

    public Library(){
        //empty constructor
    }

    public void checkOut(List<Integer> bookIDs, OrdinaryBorrower user){
        for (int bookid : bookIDs){
            if(this.books.containsKey(bookId)){
                System.out.println("Error");
            } else if (! this.books.get(bookid).available){
                System.out.println("Can not check out since the book is checked out");
            }
            else{
                Book book = this.books.get(bookid);
                book.available = false;
                book.lastCheckedOut = user;
                user.checkedBook.add(book);
            }
        }
    }

    public void returnBook(int bookid, OrdinaryBorrower user){
        Book book = this.books.get(bookid);
        if(book.available){
            System.out.println("Can not return since the book isn't checked out");
            return;
        }
        book.available = true;
        book.lastCheckedOut = null;
        user.checkedBook.remove(book);
    }

    public void addBook(Book book){
        books.put(bookId, book);
        // ++ should after setting the id, since the id start from 0
//        bookId++;
        book.bookId = this.bookId;
        this.bookId++;
        if(!booksByAuthor.containsKey(book.getAuthor())){
            booksByAuthor.put(book.getAuthor(), new ArrayList<>());
        }
        booksByAuthor.get(book.getAuthor()).add(book);
        if(!booksBySubject.containsKey(book.getSubject())){
            booksBySubject.put(book.getSubject(), new ArrayList<>());
        }
        booksBySubject.get(book.getSubject()).add(book);
    }

    public void removeBook(int bookId){
        Book book = books.get(bookId);
        String author = book.getAuthor();
        String subject = book.getSubject();
        books.remove(bookId);
        booksByAuthor.get(author).remove(book);
        booksBySubject.get(subject).remove(book);
    }

    public void addUser(String type, String name, int predefinedNum){
        User user;
        if(predefinedNum == 0){
            user = new StaffUser(name);
        }
        else{
            user = new OrdinaryBorrower(name, predefinedNum);
        }
        users.add(user);
        user.library = this;
    }

    public User getUser(String username){
        for(User user : users){
            if(user.name.equals(username)){
                return user;
            }
        }
        return null;
    }

    public Book getBookByBookId(int bookId){
        if(books.containsKey(bookId)){
            return books.get(bookId);
        }
        return null;
    }

    public void listSubject(String subject){
        List<Book> books = booksBySubject.get(subject);
        if(books == null){
            System.out.println("Error");
            return;
        }
        for(Book book : books){
            System.out.println(book.getBookInfo());
        }
    }
    public void listAuthor(String author){
        List<Book> books = booksByAuthor.get(author);
        if(books == null){
            System.out.println("Error");
            return;
        }
        for(Book book : books){
            System.out.println(book.getBookInfo());
        }
    }

    public void findChecked(OrdinaryBorrower user){
        for(Book book : user.checkedBook){
            System.out.println(book.getBookInfo());
        }
    }

    // public void Borrower(int BookId){
    //     Book book = this.getBookByBookId(BookId);
    //     if(book == null){
    //            System.out.println("Error");
    //     } else{
    //         System.out.println( "User:"  + book.lastCheckedOut.name);
    //     }
    // }
}