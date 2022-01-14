import java.util.*;
import java.io.*;

public class RTFReader{

    private ArrayList<String> rawDocuments = new ArrayList<String>();

    RTFReader(ArrayList<String>  rawDocuments){
        this.rawDocuments = rawDocuments;
    }

    public void convert(){
        for(int i = 0; i < this.rawDocuments.size() - 1; i+=2){
            parseRTFDocument(this.rawDocuments.get(i), this.rawDocuments.get(i+1));
        }
    }
    public void parseRTFDocument(String format, String token){
        RTFDocument rtfDocument = new RTFDocument(format, token);
        if (rtfDocument.format.equals("TeX")) {
			Converter converter = new TexConverter();
			converter.convert(rtfDocument);
		}
		else if (rtfDocument.format.equals("TextWidget")) {
			Converter converter = new TextWidgetConverter();
			converter.convert(rtfDocument);
        }
    }
}