import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

public class MidtermExam {
    /* public */
//    public ArrayList <User> users = new ArrayList<>();

    public static Library library = new Library();

    public static eParseMission parseMission = eParseMission.eParseNumberOfBook;
    public static User currentUser = null;
    public static User currentUserB = null;

    private static Book parseBook(String line) {
        boolean rtn = false;
        String[] words = line.split(" ");
        int wordCount = words.length;
        if (wordCount != 2)
            return null;

        String author = words[0];
        String subject = words[1];

        Book book = new Book(author, subject);
        return book;
    }

    private static Integer parseInt(String line) {
        String[] words = line.split(" ");
        int wordCount = words.length;
        if (wordCount != 1)
            return null;

        int nValue = -1;
        try {
            nValue = Integer.parseInt(words[0]);
        }
        catch (Exception e) {
            return null;
        }

        return Integer.valueOf(nValue);
    }

    private static List<Integer> parseIntArray(String line) {
        List<Integer> intAry = new ArrayList<>();

        String[] words = line.split(" ");
        int wordCount = words.length;
        if (wordCount == 0)
            return intAry;

        for (String word : words) {
            int nValue = -1;
            try {
                nValue = Integer.parseInt(word);
            }
            catch (Exception e) {
                continue;
            }

            intAry.add(Integer.valueOf(nValue));
        }
        return intAry;
    }

    private static User parseUser(String line) {
        boolean rtn = false;
        String[] words = line.split(" ");
        int wordCount = words.length;
        if (wordCount < 2)
            return null;

        String user_type = words[0];
        String user_name = words[1];

        if (user_type.equals("Staff")) {
            StaffUser staff = new StaffUser(user_name);
            return staff;
        }
        else if (user_type.equals("Borrower")) {
            if (wordCount == 3) {
                int nValue = -1;
                try {
                    nValue = Integer.parseInt(words[2]);
                }
                catch (Exception e) {
                    return null;
                }
                int predefined_borrow_book_number = nValue;
                OrdinaryBorrower borrower = new OrdinaryBorrower(user_name, predefined_borrow_book_number);
                return borrower;
            }
            else {
                return null;
            }
        }
        return null;
    }

    private static void parseCommand(String line) {
        MidtermExam.currentUser = null;

        String[] words = line.split(" ");
        int wordCount = words.length;
        if (wordCount < 1) {
            System.out.printf("Error\n");
            return;
        }
 
        String userName = words[0];
      
        User user = MidtermExam.library.getUser(userName);
        if (user == null) {
            System.out.printf("Error\n");
            return;
        }

        if (wordCount < 2) {
            System.out.printf("Error\n");
            return;
        }

        String commandType = words[1];
        if (commandType.equals("addBook")) {
            MidtermExam.currentUser = user;
            MidtermExam.parseMission = eParseMission.eParseCommandAddBook;
        }
        else if (commandType.equals("removeBook")) {
            // UserA removeBook 3
            int nValue = -1;
            try {
                nValue = Integer.parseInt(words[2]);
            }
            catch (Exception e) {
                System.out.printf("Error\n");
                return;
            }
            int book_id = nValue;
            user.removeBook(book_id);

        }
        else if (commandType.equals("checkout")) {
           
            // UserA checkout UserB
            String userB_Name = words[2];
            User userB = MidtermExam.library.getUser(userB_Name);
            if (userB == null) {
                System.out.printf("Error\n");
                return;
            }
            MidtermExam.currentUser = user;
            MidtermExam.currentUserB = userB;
            MidtermExam.parseMission = eParseMission.eParseCommandCheckout;
        }
        else if (commandType.equals("return")) {
            // UserA return 0
            User userA = MidtermExam.library.getUser(words[0]); 
            if (wordCount < 3) {
                System.out.printf("Error\n");
                return;
            }
            int book_id = Integer.parseInt(words[2]);
            userA.returnBook(book_id);
        }
        else if (commandType.equals("listAuthor")) {
            // UserA listAuthor AuthorA
            // index error
//            String author = words[1];
            String author = words[2];
            user.listAuthor(author);
        }
        else if (commandType.equals("listSubject")) {
            // UserA listSubject SubjectA
            String subject = words[2];
            user.listSubject(subject);
        }
        else if (commandType.equals("findChecked")) {
            // UserA findChecked UserB
            String userB_Name = words[2];
            user.findCheckedList(userB_Name);
        }
        // the command in Sample0.in is findBorrower
//        else if (commandType.equals("Borrower")) {
        else if (commandType.equals("findBorrower")) {
            // UserA Borrower 1
            String book_id = words[2];
            user.findLastCheckBrowser(Integer.parseInt(book_id));
        }

        return;
    }

    private static Boolean parseInputArgs(String[] args) {
    
        int bookCount = 0;
        int userCount = 0;

        File dataFile = new File(args[0]);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            try {
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
//                    System.out.printf("Parsing -- %s\n",line);

                    switch (MidtermExam.parseMission) {
                        case eParseNumberOfBook:
                        {
                            Integer rtn = MidtermExam.parseInt(line);
                            if (rtn== null) {
                                System.out.printf("Error\n");
                                return false;
                            }

                            bookCount = rtn.intValue();
                            MidtermExam.parseMission = eParseMission.eParseBook;
                            if (bookCount == 0) {
                                MidtermExam.parseMission = eParseMission.eParseNumberOfUser;
                                continue;
                            }
//                            System.out.printf("bookCount = %d\n",bookCount);
                            break;
                        }
                        case eParseBook:
                        {
                            Book book = MidtermExam.parseBook(line);
                            if (book != null) {
                                MidtermExam.library.addBook(book);
                            }
                            bookCount--;

//                            System.out.printf("eParseBook  %s %s\n",book.subject, book.author);
//                            System.out.printf("MidtermExam.library.books  %d\n", MidtermExam.library.books.size());
                            if (bookCount == 0) {
                                MidtermExam.parseMission = eParseMission.eParseNumberOfUser;
                            }
                            break;                      
                        }
                        case eParseNumberOfUser:
                        {
                            Integer rtn = MidtermExam.parseInt(line);
                            if (rtn== null) {
                                System.out.printf("Error\n");
                                return false;
                            }
                            userCount = rtn.intValue();

                            MidtermExam.parseMission = eParseMission.eParseUser;
                            if (userCount == 0) {
                                MidtermExam.parseMission = eParseMission.eParseCommand;
                                continue;
                            }
//                            System.out.printf("userCount = %d\n",userCount);
                            break;   

                        }
                        case eParseUser:
                        {
                            User user = MidtermExam.parseUser(line);
                            if (user != null) {
                                String type = "";
                                String name = user.name;
                                int predefinedNum = 0;
                                if (user instanceof OrdinaryBorrower) {
                                    type = "Borrower";
                                    OrdinaryBorrower borrower = (OrdinaryBorrower) user;
                                    predefinedNum = borrower.predefined_borrow_book_number;
                                }
                                else if (user instanceof StaffUser) {
                                    type = "Staff";
                                }
                                MidtermExam.library.addUser(type, name, predefinedNum);
//                                System.out.printf("eParseUser  %s %s\n", user.name, user.getClass());
//                                System.out.printf("MidtermExam.library.users  %d\n", MidtermExam.library.users.size());
                                userCount--;
                                if (userCount == 0) {
                                    MidtermExam.parseMission = eParseMission.eParseCommand;
                                }
                            }
                            break;
                        }
                        case eParseCommand:
                        {
                            MidtermExam.parseCommand(line);
                            break;
                        }
                        case eParseCommandAddBook:
                        {
                            Book book = MidtermExam.parseBook(line);
                            if (MidtermExam.currentUser != null && book != null) {
                                //TODO: book size in library not increase after addbook
//                                System.out.printf("eParseCommandAddBook  %s %s by %s, books to %d\n",book.subject, book.author, MidtermExam.currentUser.name, MidtermExam.library.users.size());
                                MidtermExam.currentUser.addBook(book.author, book.subject);
//                                System.out.printf("eParseCommandAddBook  %s %s by %s, books to %d\n",book.subject, book.author, MidtermExam.currentUser.name, MidtermExam.library.users.size());
                            }
                            MidtermExam.parseMission = eParseMission.eParseCommand;
                            break;
                        }
                        case eParseCommandCheckout:
                        {
//                            System.out.printf("eParseCommandCheckout %s checkout %s\n", MidtermExam.currentUser.name, MidtermExam.currentUserB.name);
                            List<Integer>ary = MidtermExam.parseIntArray(line);
                            MidtermExam.currentUser.checkOut(ary, MidtermExam.currentUserB.name);
                            MidtermExam.parseMission = eParseMission.eParseCommand;
                            break;
                        }
                        default:
                            break;
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (FileNotFoundException ex) {

        }
        return true;
    }
    public static void main(String[] args){
        Boolean isParsingOk = MidtermExam.parseInputArgs(args);

        return;
    }
}
    
    