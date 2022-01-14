public class ListIterator implements Iterator  {
    
    private ListObject list;

    private int index = 0;

    public ListIterator(ListObject list){
        this.list = list;
    }

    public boolean hasNext(){
        if (index < list.length)
            return true;
        else
            return false;
    }

    public StringObject next(){
        if (hasNext())
            return list.get(index++);
        else{
            System.out.println("Error");
            return null;
        }
    }
}
