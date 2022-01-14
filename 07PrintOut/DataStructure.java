public interface DataStructure {

	public Content get(int index);

    public Iterator createIterator();

    public void addContent(String content);

    public void printOutList();
}