import java.util.LinkedHashMap;
import java.util.Set;

public class ApplicationData {
    private LinkedHashMap<String, Chart> charts = new LinkedHashMap<String, Chart>();
    private LinkedHashMap<String, Integer> items= new LinkedHashMap<String, Integer>();

    public void addData(String itemKey, int value){
        items.put(itemKey, value);
    }
    public Chart getChart(String chartType){
        return  charts.get(chartType);

    }
    public LinkedHashMap<String, Integer> getData() {
        return items;
    }
    public void change(String itemKey, int value){
        items.put(itemKey, value);
        Set<String> keys = charts.keySet();
        for (String key : keys) {
            charts.get(key).update();
        }
    }
    public void attach(String chartType, Chart chart) {
        charts.put(chartType, chart);
    }
    public void detach(Chart chart) {
        charts.remove(chart);
    }
}
