import java.util.ArrayList;

public class Application {
    private ArrayList<Document> documents = new ArrayList<Document>();

    public void addDocument(Document document){
        documents.add(document);
    }

    public void display(){
        for(Document document: documents){
            document.display();
        }
    }
}
