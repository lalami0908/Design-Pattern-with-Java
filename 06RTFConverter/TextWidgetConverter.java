public class TextWidgetConverter implements Converter {
    public void convert(RTFDocument rtfDocument) {
        for(int i=0; i<rtfDocument.token.length(); i++) {
            if(rtfDocument.token.charAt(i) == 'C') {
                System.out.printf("<Char>");
            }
            else if(rtfDocument.token.charAt(i) == 'F') {
                System.out.printf("<Font>");
            }
            else if(rtfDocument.token.charAt(i) == 'P') {
                System.out.printf("<Paragraph>");
            }
            else {
                System.out.println("Wrong input");
            }
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        String s = "CCCFFPPP";
        Converter textWidgetConverter = new TextWidgetConverter();
        textWidgetConverter.convert(new RTFDocument("test", s));
    }
}