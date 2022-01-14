public class PMStandard implements Standard {

    public Window createWindow(String name){
        return new PMWindow(name);
    }

    public ScrollBar createScrollbar(String name){
        return new PMScrollBar(name);
    }

    public Button createButton(String name){
        return new PMButton(name);
    }
}
