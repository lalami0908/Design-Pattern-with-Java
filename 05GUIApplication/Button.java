public abstract class Button {

    String style;

    String name;

    Button(String style, String name){
        this.style = style;
        this.name = name;
    }

    public void display(){
        String s = style + " " + name;
        System.out.println(s);
    }

    public String getStyle() {
        return style;
    }

    public String getName() {
        return name;
    }
}