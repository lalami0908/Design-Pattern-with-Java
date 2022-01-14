public class TexConverter implements Converter {
    public void convert(RTFDocument rtfDocument) {
        for(int i=0; i<rtfDocument.token.length(); i++) {
            if(rtfDocument.token.charAt(i) == 'C') {
                System.out.printf("c");
            }
            else if(rtfDocument.token.charAt(i) == 'F') {
                System.out.printf("_");
            }
            else if(rtfDocument.token.charAt(i) == 'P') {
                System.out.printf("|");
            }
            else {
                System.out.println("Wrong input");
            }
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        String s = "CCCFFPPP";
        Converter texConverter = new TexConverter();
        texConverter.convert(new RTFDocument("test", s));
    }
}