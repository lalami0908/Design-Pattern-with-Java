import java.util.ArrayList;

public interface RankingStrategy {

    public float calculateScore(Assignment assignment);

    public ArrayList<String> findStrength(Assignment assignment);

    public ArrayList<String> findWeakness(Assignment assignment);
}