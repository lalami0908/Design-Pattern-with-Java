public class SkipListIterator implements Iterator {
    
    private SkipList list;

    private int index = 0;

    public SkipListIterator(SkipList list){
        this.list = list;
    }

    public boolean hasNext(){
        if (index < list.size())
            return true;
        else
            return false;
    }

    public SkipNode next(){
        if (hasNext())
            return list.get(index++);
        else{
            System.out.println("Error");
            return null;
        }
    }
}
