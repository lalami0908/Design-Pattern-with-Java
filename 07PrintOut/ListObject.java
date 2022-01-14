import java.util.*;

public class ListObject implements DataStructure {
    
    public int length = 0;

    private List<StringObject> strings = new ArrayList<StringObject>();

    public StringObject get(int index){
        return strings.get(index);
    }

    public ListIterator createIterator(){
        return new ListIterator(this);
    }

    public void addContent(String content){
        strings.add(new StringObject(content));
        length++;
    }

    public void printOutList(){
        ListIterator iterator = createIterator();
        while (iterator.hasNext())
            System.out.println(iterator.next().content);
    }
}
