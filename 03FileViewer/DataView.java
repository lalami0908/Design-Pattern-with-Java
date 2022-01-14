public abstract class DataView {
    private String viewName;
    public String getViewName(){
        return viewName;
    }
    public DataView( String viewName){
        this.viewName = viewName;
    }
    public abstract void display();

    public abstract void addElement(Element element);
}
