import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class PieChart extends Chart{
    public int sumValue(LinkedHashMap<String, Integer> items){
        int sumValue = 0;
        for(int value: items.values()){
            sumValue += value;
        }
        return  sumValue;
    }

    @Override
    public void display(LinkedHashMap<String, Integer> items) {
        int sumValue = sumValue(items);
        for(Entry<String, Integer> item: items.entrySet()){
            float percentage = 100 * (float)item.getValue() / sumValue;
            System.out.printf("%s %.1f%%\n", item.getKey(), percentage);
        }
    }
}
