public class Rectangle implements Component{
    public void print() {
        System.out.print("Rectangle");
    }

    public static void main(String[] args) {
        Component rectangle = new Rectangle();
        rectangle.print();
    }
}
