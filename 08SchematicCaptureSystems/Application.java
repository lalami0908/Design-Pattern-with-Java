import java.util.ArrayList;
import java.util.List;

public class Application {

    private List<String> inputs;
    private List<Component> components;

    Application(List<String> inputs) {
        this.inputs = inputs;
        this.components = new ArrayList<>();
    }

    public void parseInputs() {
        System.out.println("!!!!");
        System.out.println(inputs);
        int i = 0;
        int n = inputs.size();
        while (i < n) {
            String input = inputs.get(i);
            if (input.equals("<Text/>")) {
                components.add(new Text());
            } else if (input.equals("<Line/>")) {
                components.add(new Line());
            } else if (input.equals("<Rectangle/>")) {
                components.add(new Rectangle());
            } else if (input.equals("<Group/>")) {
                components.add(new Group());
            } else if (input.equals("<Group>")) {
                Group group = new Group();
                i = createGroup(group, inputs, i+1);
                components.add(group);
            }
            i++;
        }
    }

    public int createGroup(Group group, List<String> inputs, int index) {
        int i = index;
        int n = inputs.size();
        while (i < n) {
            String input = inputs.get(i);
            if (input.equals("<Text/>")) {
                group.add(new Text());
            } else if (input.equals("<Line/>")) {
                group.add(new Line());
            } else if (input.equals("<Rectangle/>")) {
                group.add(new Rectangle());
            } else if (input.equals("<Group/>")) {
                group.add(new Group());
            } else if (input.equals("</Group>")) {
                return i;
            } else if (input.equals("<Group>")) {
                Group newGroup = new Group();
                i = createGroup(newGroup, inputs, i+1);
                group.add(newGroup);
            }
            i++;
        }
        return i;
    }

    public void printAnswer() {
        for(Component c : components) {
            c.print();
            System.out.println("");
        }
    }
}
