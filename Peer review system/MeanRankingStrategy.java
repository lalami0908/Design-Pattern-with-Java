import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class MeanRankingStrategy implements RankingStrategy {

    public float calculateScore(Assignment assignment) {
        float score = 0, n = 0;
        for (Map.Entry<Student, Rank> set : assignment.reviewedRanks.entrySet()) {
            for (String rank : set.getValue().levels) {
                score += assignment.rubric.levels.get(rank);
                ++n;
            }
        }
        return score / n;
    }

    private ArrayList<Float> getCriterionScores(Assignment assignment) {
        int n = 0;
        ArrayList<Float> scores = new ArrayList<>();
        for (int i = 0; i < assignment.rubric.criterions.size(); ++i) {
            scores.add((float) (0.0));
        }
        for (Map.Entry<Student, Rank> set : assignment.reviewedRanks.entrySet()) {
            int i = 0;
            ++n;
            for (String rank : set.getValue().levels) {
                int score = assignment.rubric.levels.get(rank);
                scores.set(i, scores.get(i) + score);
                ++i;
            }
        }
        for (int j = 0; j < scores.size(); ++j) {
            scores.set(j, scores.get(j) / n);
        }
        return scores;
    }

    public ArrayList<String> findStrength(Assignment assignment) {
        ArrayList<Float> scores = getCriterionScores(assignment);
        float maxVal = 0;
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < scores.size(); ++i) {
            float val = scores.get(i);
            if (val > maxVal) {
                res.clear();
                res.add(assignment.rubric.criterions.get(i));
                maxVal = val;
            } else if (val == maxVal) {
                res.add(assignment.rubric.criterions.get(i));
            }
        }
        return res;
    }

    public ArrayList<String> findWeakness(Assignment assignment) {
        ArrayList<Float> scores = getCriterionScores(assignment);
        float minVal = Float.MAX_VALUE;
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < scores.size(); ++i) {
            float val = scores.get(i);
            if (val < minVal) {
                minVal = val;
                res.clear();
                res.add(assignment.rubric.criterions.get(i));
            } else if (val == minVal) {
                res.add(assignment.rubric.criterions.get(i));
            }
        }
        return res;
    }
}