public class MotifStandard implements Standard {
    
    
    public Window createWindow(String name){
        return new MotifWindow(name);
    }

    public ScrollBar createScrollbar(String name){
        return new MotifScrollBar(name);
    }

    public Button createButton(String name){
        return new MotifButton(name);
    }

}
