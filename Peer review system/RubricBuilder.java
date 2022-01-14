import java.util.LinkedHashMap;
import java.util.List;

public interface RubricBuilder {
    void setLevel(LinkedHashMap<String, Integer> levels);
    void setCriterion(List<String> criterions, LinkedHashMap<String, LinkedHashMap<String, String>> descriptions);
    NormalRubric getResult();
}
