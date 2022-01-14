import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class MedianRankingStrategy implements RankingStrategy {

    private float getMedian(ArrayList<Integer> scores){
        return (scores.get(scores.size() / 2) + scores.get(scores.size() / 2 - 1)) / 2;
    }

    public float calculateScore(Assignment assignment){
        ArrayList<Integer> scores = new ArrayList<>();
        for (Map.Entry<Student, Rank> set : assignment.reviewedRanks.entrySet()){
            for (String rank : set.getValue().levels){                          
                int score = assignment.rubric.levels.get(rank);
                scores.add(score);
            }
        }
        Collections.sort(scores);
        return getMedian(scores);
    }

    private ArrayList<ArrayList<Integer>> getCriterionScores(Assignment assignment){
        ArrayList<ArrayList<Integer>> scores = new ArrayList<>(assignment.reviewedRanks.size());
        for (Map.Entry<Student, Rank> set : assignment.reviewedRanks.entrySet()){
            int i = 0;
            for (String rank : set.getValue().levels){                          
                int score = assignment.rubric.levels.get(rank);
                scores.get(i++).add(score);
            }
        }
        return scores;
    }

    public ArrayList<String> findStrength(Assignment assignment){
        ArrayList<ArrayList<Integer>> scores = getCriterionScores(assignment);
        float maxVal = 0;
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < scores.size(); ++i){
            Collections.sort(scores.get(i));
            float val = getMedian(scores.get(i));
            if (val > maxVal){
                res.clear();
                res.add(assignment.rubric.criterions.get(i));
                maxVal = val;
            }
            else if (val == maxVal){
                res.add(assignment.rubric.criterions.get(i));
            }
        }
        return res;
    }

    public ArrayList<String> findWeakness(Assignment assignment){
        ArrayList<ArrayList<Integer>> scores = getCriterionScores(assignment);
        float minVal = Float.MAX_VALUE;
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < scores.size(); ++i){
            Collections.sort(scores.get(i));
            float val = getMedian(scores.get(i));
            if (val < minVal){
                minVal = val;
                res.clear();
                res.add(assignment.rubric.criterions.get(i));
            }
            else if (val == minVal){
                res.add(assignment.rubric.criterions.get(i));
            }
        }
        return res;
    }
}