import java.util.ArrayList;

public class TextView extends DataView{
    String data;
    ArrayList<Element> elements = new ArrayList<Element>();

    public TextView(String textViewName, String textViewData) {
        super(textViewName);
        this.data = textViewData;
    }

    @Override
    public void display() {
        System.out.printf("%s", data);

        for(Element element: elements){
            System.out.printf(" %s", element.getElementType());
        }

        System.out.print("\n");

    }

    @Override
    public void addElement(Element element) {
        elements.add(element);
    }
}
