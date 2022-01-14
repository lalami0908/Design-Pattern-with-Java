
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Instructor {

    public Map<String, List<Assignment>> assignments = new HashMap<>();
    public Map<String, NormalRubric> rubrics = new HashMap<>();
    public RankingStrategy rankingStrategy = new MeanRankingStrategy();
    private static Instructor instance = new Instructor();
    public NormalRubricBuilder rubricBuilder;

    private Instructor() {
        this.rubricBuilder = new NormalRubricBuilder();
    }

    public static Instructor getInstance() {
        if (instance == null)
            instance = new Instructor();
        return instance;
    }

    public void assign(String assignmentID, Student owner, Map<Student, List<String>> studentReview) {
        // assignment no rubric exists.
        if (studentReview.size() > 5 || studentReview.size() < 3) {
            System.out.println("Assignment should be reviewed by 3-5 students.");
            return;
        }

        if (!rubrics.containsKey(assignmentID)) {
            System.out.println("Error");
            return;
        }
        // check self assign.
        // System.out.println("haha");
        for (Student student : studentReview.keySet()) {
            if (student.studentID.equals(owner.studentID)) {
                System.out.println("Error");
                return;
            }
        }
        // System.out.println("ha");
        if (assignments.containsKey(assignmentID)) {
            for (Assignment assignment : assignments.get(assignmentID)) {
                if (assignment.owner.studentID.equals(owner.studentID)) {
                    System.out.println("Error");
                    return;
                }
            }
        }
        // System.out.println("hahahaha");
        Assignment new_assignment = new Assignment(assignmentID, owner, rubrics.get(assignmentID));
        // add assignment of a student to assignments.
        if (!assignments.containsKey(assignmentID)) {
            // System.out.println("hahahaha2");
            assignments.put(assignmentID, new ArrayList<>());
            assignments.get(assignmentID).add(new_assignment);
        } else {
            // System.out.println("hahahaha3");
            assignments.get(assignmentID).add(new_assignment);
        }

        // make rank
        // System.out.println("hahahaha4");
        for (Student student : studentReview.keySet()) {
            // System.out.println("hahahaha5");
            new_assignment.reviewers.add(student);
            student.review(new_assignment, owner, studentReview.get(student));
        }

    }

    // setting the level for a testcase in a rubric.
    public void setLevel(LinkedHashMap<String, Integer> levels) {
        rubricBuilder.setLevel(levels);
    }

    // set the rubric for an assignment and add it to the rubrics.
    public void design(String assignmentID, LinkedHashMap<String, LinkedHashMap<String, String>> descriptions) {
        rubricBuilder.setCriterion(new ArrayList<>(descriptions.keySet()), descriptions);
        rubrics.put(assignmentID, rubricBuilder.getResult());
    }

    // setting strategy for ranking.
    public void setStrategy(RankingStrategy strategy) {
        rankingStrategy = strategy;
    }

    public void calculateScore(String assignmentID, Student student) {
        float score = 0;
        for (Assignment assignment : assignments.get(assignmentID)) {
            if (assignment.owner.studentID.equals(student.studentID)) {
                score = rankingStrategy.calculateScore(assignment);
            }
        }
        System.out.printf("Assignment: %s, Student: %s, Score: %.1f\n", assignmentID, student.studentID, score);
    }

    public void printRubric(String assignmentID) {
        rubrics.get(assignmentID).printRubric();
    }

    public void averageCriterion(String assignmentID) {
        List<Assignment> a = assignments.get(assignmentID);
        List<String> criterion = rubrics.get(assignmentID).criterions;
        int j = 0;
        for (String criterionName : criterion) {
            float score = 0, n = 0;
            for (Assignment i : a) {
                for (Student reviewer : i.reviewers) {
                    String key = i.reviewedRanks.get(reviewer).levels.get(j);
                    score += i.rubric.levels.get(key);
                    ++n;
                }
            }
            System.out.printf("Assignment: %s, Criterion: %s, AvgScore: %.1f\n", assignmentID, criterionName,
                    score / n);
            j++;
        }

    }

    public void findStrength(String assignmentID, Student student) {
        ArrayList<String> criterions = new ArrayList<String>();
        for (Assignment assignment : assignments.get(assignmentID)) {
            if (assignment.owner.studentID.equals(student.studentID)) {
                criterions = rankingStrategy.findStrength(assignment);
            }
        }
        String criterionString = "";
        for (int i = 0; i < criterions.size(); ++i) {
            criterionString += criterions.get(i);
            if (i != criterions.size() - 1) {
                criterionString += " ";
            }
        }
        System.out.printf("Assignment: %s, Student: %s, Strength: %s\n",
                assignmentID, student.studentID, criterionString);
    }

    public void findWeakness(String assignmentID, Student student) {
        ArrayList<String> criterions = new ArrayList<String>();
        for (Assignment assignment : assignments.get(assignmentID)) {
            if (assignment.owner.studentID.equals(student.studentID)) {
                criterions = rankingStrategy.findWeakness(assignment);
            }
        }
        String criterionString = "";
        for (int i = 0; i < criterions.size(); ++i) {
            criterionString += criterions.get(i);
            if (i != criterions.size() - 1) {
                criterionString += " ";
            }
        }
        System.out.printf("Assignment: %s, Student: %s, Weakness: %s\n",
                assignmentID, student.studentID, criterionString);
    }
}