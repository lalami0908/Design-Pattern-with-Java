import java.util.ArrayList;

public class TexCompositor implements Compositor {
    @Override
    public void compose(ArrayList<Component> components) {
        for(int i = 0; i < components.size(); i++){
            System.out.printf("[%d]%s", components.get(i).getNaturalSize(), components.get(i).getContent());
            if (i == components.size()- 1 || components.get(i).getContent().equals("<ParagraphEnd>")){
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }
}
