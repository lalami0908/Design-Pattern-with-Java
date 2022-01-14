import java.util.ArrayList;
import java.util.List;

public class Student {
    String studentID;
    // ArrayList<Assignment> assignments = new ArrayList<Assignment>();

    public Student(String studentID) {
        this.studentID = studentID;
    }

    public void review(Assignment assignment, Student owner, List<String> studentReview) {
        NormalRubric rubric = assignment.rubric;
        int criterionsCnt = rubric.criterions.size();
        List<String> actualReview = new ArrayList<String>();
        if (studentReview.size() <= criterionsCnt) {
            actualReview = studentReview;
        }
        actualReview = studentReview.subList(0, criterionsCnt);
        Rank rank = new Rank(actualReview);
        assignment.reviewedRanks.put(this, rank);
        // A1-001 was reviewed by 002. Level: Excellent Excellent Competent Competent
        System.out.printf("%s-%s was reviewed by %s. Level: ", assignment.AssignmentID, owner.studentID,
                this.studentID);
        for (int i = 0; i < rank.levels.size(); i++) {
            System.out.print(rank.levels.get(i));
            if (i != rank.levels.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    };
}
