import jdk.nashorn.internal.objects.annotations.Getter;

public abstract class Component {

    private int componentId;
    private int naturalSize;
    private int stretchability;
    private  int shrinkability;
    private String content;


    public int getComponentId() { return componentId; }
    public int getNaturalSize(){
        return naturalSize;
    }
    public String getContent() { return content; }

    public boolean changeSize(int size){
        if (size < shrinkability || stretchability < size){
            System.out.printf("component %d failed to change size\n", componentId);
            return false;
        }
        else{
            naturalSize = size;
            System.out.printf("component %d size changed to %d\n", componentId, size);
            return true;
        }
    }


    public void setComponentId(int componentId) { this.componentId = componentId; }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNaturalSize(int naturalSize) {
        this.naturalSize = naturalSize;
    }

    public void setShrinkability(int shrinkability) {
        this.shrinkability = shrinkability;
    }

    public void setStretchability(int stretchability) {
        this.stretchability = stretchability;
    }
}
