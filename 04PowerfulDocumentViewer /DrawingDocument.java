public class DrawingDocument extends Document{

    private  String documentType = "DrawingDocument";
    @Override
    public void display() {
        System.out.println(documentType);
    }
}
