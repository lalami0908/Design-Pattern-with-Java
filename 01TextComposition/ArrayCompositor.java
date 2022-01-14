import java.util.ArrayList;

public class ArrayCompositor implements Compositor{
    @Override
    public void compose(ArrayList<Component> components) {
        int counter = 0;
        for (int i = 0; i < components.size(); i++){
            System.out.printf("[%d]%s", components.get(i).getNaturalSize(), components.get(i).getContent());
            counter += 1;
            if (counter % 3  != 0 && i != components.size()-1 ){
                System.out.print(" ");
            } else {
                System.out.println();
            }
        }
    }
}
