public abstract  class Element {
    private String elementType;
    public Element(String elementType){
        this.elementType = elementType;
    }

    public String getElementType() {
        return elementType;
    }
}
