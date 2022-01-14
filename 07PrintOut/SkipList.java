import java.util.ArrayList;
import java.util.List;

public class SkipList implements DataStructure {

    private int length = 0;

    private List<SkipNode> skipNodes = new ArrayList<SkipNode>();

    public SkipNode get(int index) {
        return skipNodes.get(index);
    }

    public SkipListIterator createIterator() {
        return new SkipListIterator(this);
    }

    public void addContent(String content) {
        skipNodes.add(new SkipNode(content));
        length++;        
    }

    public int size(){
        return length;
    }

    public void printOutList() {
        SkipListIterator iterator = createIterator();
        while (iterator.hasNext())
            System.out.println("SkipNode:" +  iterator.next().content);
    }
}