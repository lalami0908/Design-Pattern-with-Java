import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Assignment {
    public String AssignmentID;
    public Student owner = null;
    public ArrayList<Student> reviewers = new ArrayList<Student>();
    public Map<Student, Rank> reviewedRanks = new HashMap<>();
    public NormalRubric rubric = null;

    public Assignment(String AssignmentID, Student owner, NormalRubric rubric){
        this.AssignmentID = AssignmentID;
        this.owner = owner;
        this.rubric = rubric;
    }

}
