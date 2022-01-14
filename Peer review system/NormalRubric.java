import java.util.LinkedHashMap;
import java.util.List;

public class NormalRubric {
    public List<String> criterions;
    public LinkedHashMap<String, Integer> levels;
    public LinkedHashMap<String, LinkedHashMap<String, String>> descriptions;

    void printRubric() {
        for (String criterion : this.criterions) {
            for (String level : this.levels.keySet()) {
                System.out.printf("(%s,%s) %s\n", criterion, level, descriptions.get(criterion).get(level));
            }
        }
    }
}