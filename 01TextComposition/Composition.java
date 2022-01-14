import java.util.ArrayList;

public class Composition {
    private ArrayList<Component> components = new ArrayList<>();
    private Compositor compositor;

    public void arrange(){
        compositor.compose(this.components);
    }

    public void setCompositor(Compositor compositor) {
        this.compositor = compositor;
    }

    public Component getComponent(int componentId){
        for(Component component: components){
            if(component.getComponentId() == componentId){
                return component;
            }
        }
        return null;
    }
    public void addComponent(Component component){
        components.add(component);
    }
}
