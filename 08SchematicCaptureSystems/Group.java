import java.util.ArrayList;


public class Group implements Component{
    public ArrayList<Component> components = new ArrayList<Component>();

    public void add(Component component){
        components.add(component);
    }
    public void print(){
        System.out.print("Group:{");
        for(Component component: components){
            component.print();
            System.out.print(" ");
        }
        System.out.print("}");
    }
    public static void main(String[] args) {
        Group group = new Group();
        // group.print();
        group.add(new Line());
        group.add(new Text());
        group.add(new Line());
        group.add(new Text());
        group.add(new Rectangle());
        Group group2 = new Group();
        group.add(group2);
        group.add(new Rectangle());
        group.print();
        // Group:{Line Text Line Text Rectangle Group:{} Rectangle } 
    }
}
