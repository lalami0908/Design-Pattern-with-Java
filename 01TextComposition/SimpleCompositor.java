import java.util.ArrayList;

public class SimpleCompositor implements Compositor{
    @Override
    public void compose(ArrayList<Component> components) {
        for(Component component: components) {
            System.out.printf("[%d]%s\n", component.getNaturalSize(), component.getContent());
        }
    }
}
