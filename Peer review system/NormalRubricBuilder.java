import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class NormalRubricBuilder implements RubricBuilder {
    public NormalRubric normalRubric = null;
    public LinkedHashMap<String, Integer> levels = null;
    public void setLevel(LinkedHashMap<String, Integer> levels) {
        this.levels = levels;
    }
    public void setCriterion(List<String> criterions, LinkedHashMap<String, LinkedHashMap<String, String>> descriptions) {
        this.normalRubric = new NormalRubric();
        this.normalRubric.levels = this.levels;
        this.normalRubric.criterions = criterions;
        this.normalRubric.descriptions = descriptions;
    }
    public NormalRubric getResult() {
        return this.normalRubric;
    }
    public static void main(String[] args) {
        RubricBuilder normalRubricBuilder = new NormalRubricBuilder();
        LinkedHashMap<String, Integer> levels = new LinkedHashMap<>() {{
            put("Excellent",3);
            put("Competent",2);
            put("NeedsWork",1);
        }};
        normalRubricBuilder.setLevel(levels);
        List<String> criterions = new ArrayList<>() {{
            add("Knowledge/Understanding");
        }};
        LinkedHashMap<String, LinkedHashMap<String, String>> descriptions = new LinkedHashMap<>() {{
            put("Knowledge/Understanding", new LinkedHashMap<>(){{
                put("Excellent", "The presentation demonstrates a depth ...");
                put("Competent", "The presentation uses knowledge which ...");
                put("NeedsWork", "The presentation uses little relevant ...");
            }});
        }};
        normalRubricBuilder.setCriterion(criterions, descriptions);
        NormalRubric normalRubric = normalRubricBuilder.getResult();
        System.out.println("normalRubric1:");
        normalRubric.printRubric();

        System.out.println("=======================================");

        List<String> criterions2 = new ArrayList<>() {{
            add("Thinking/Inquiry");
        }};
        LinkedHashMap<String, LinkedHashMap<String, String>> descriptions2 = new LinkedHashMap<>() {{
            put("Thinking/Inquiry", new LinkedHashMap<>(){{
                put("Excellent", "The presentation is centered around ...");
                put("Competent", "The presentation shows an analytical ...");
                put("NeedsWork", "The presentation shows no analytical ...");
            }});
        }};
        normalRubricBuilder.setCriterion(criterions2, descriptions2);
        NormalRubric normalRubric2 = normalRubricBuilder.getResult();
        System.out.println("NormalRubric2:");
        normalRubric2.printRubric();
    }
}
