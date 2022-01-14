import java.util.LinkedHashMap;
import java.util.Set;

public class Spreadsheet extends Chart{

    @Override
    public void display(LinkedHashMap<String, Integer> items) {
        Set<String> keys =  items.keySet();
        for (String key: keys){
            System.out.printf("%s %d\n", key, items.get(key));
        }
    }

}
