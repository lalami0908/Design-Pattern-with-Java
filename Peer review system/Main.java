
// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Map;
// import java.util.Set;
import java.util.*;
import java.io.*;

public class Main {
  public static Map<String, Student> students;
  public static Instructor instructor = Instructor.getInstance();

  public static void main(String args[]) throws IOException {
    students = new LinkedHashMap<>();
    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(args[0])));

    while (true) {
      try {
        String line = bufferedReader.readLine();
        if (line == null)
          break;
        if (line.equals(""))
          continue;
        DEBUG_PARSE(line);
        String[] words = line.split(" ");
        switch (words[0]) {
          case "student":
            handleStudentAdd(words);
            break;
          case "schoolStrategy":
            handleSchoolStrategy(words);
            break;
          case "designCriterion":
            handleDesignCriterion(words);
            break;
          case "assignment":
            handleAssignment(words);
            break;
          case "printRubric":
            handlePrintRubric(words);
            break;
          case "calculateScore":
            handleCalculateScore(words);
            break;
          case "averageCriterion":
            handleAverageCriterion(words);
            break;
          case "findStrength":
            handleFindStrength(words);
            break;
          case "findWeakness":
            handleFindWeakness(words);
            break;
          default:
            badInput();
        }
      } catch (Exception e) {
        badInput();
        continue;
      }
    }

    bufferedReader.close();

  }

  // student [StudentID] [StudentID] …
  private static void handleStudentAdd(String[] words) {
    if (words.length < 2) {
      badInput();
      return;
    }

    Set<String> names = new HashSet<>();
    names.addAll(students.keySet());
    for (int i = 1; i < words.length; i++) {
      if (names.contains(words[i])) {
        badInput();
        return;
      }
      names.add(words[i]);
    }

    names.forEach(s -> {
      if (!students.keySet().contains(s))
        students.put(s, new Student(s));
    });
  }

  // schoolStrategy [Level],[Score] [Level]...
  private static void handleSchoolStrategy(String[] words) {
    if (words.length < 2) {
      badInput();
      return;
    }
    DO(words);

    LinkedHashMap<String, Integer> Strategies = new LinkedHashMap<String, Integer>();
    for (int i = 1; i < words.length; i++) {
      String[] ss = words[i].split(",");
      DEBUG(ss[0] + " " + ss[1]);
      Strategies.put(ss[0], Integer.valueOf(ss[1]));
    }

    instructor.setLevel(Strategies);
  }

  // assignment [AssignmentID] [StudentID] [ReviewrID],[SampleScoreFile]
  // [ReviewrID],[Sa
  private static void handleAssignment(String[] words) {
    if (words.length < 4 || students.get(words[2]) == null) {
      badInput();
      return;
    }

    Map<Student, List<String>> parsedContent = new LinkedHashMap<>();

    for (int i = 3; i < words.length; i++) {
      String[] reviews = words[i].split(",");
      Student stu = students.get(reviews[0]);
      if (stu == null) {
        badInput();
        return;
      }
      try {
        BufferedReader fd = new BufferedReader(new FileReader(new File(reviews[1])));
        List<String> list = new ArrayList<>();
        while (true) {
          String in = fd.readLine();
          if (in != null)
            list.add(in);
          else
            break;
        }
        parsedContent.put(stu, list);
      } catch (Exception e) {
        badInput();
        return;
      }
    }

    instructor.assign(words[1], students.get(words[2]), parsedContent);
  }

  // designCriterion [AssignmentID] [RubricFile]
  private static void handleDesignCriterion(String[] words) {
    if (words.length < 3)
      badInput();
    DO(words);
    // Map<String, String> DesignCriterion = new HashMap<>();
    String AssignmentId, CriteriaFilename;
    AssignmentId = words[1];
    CriteriaFilename = words[2];

    LinkedHashMap<String, LinkedHashMap<String, String>> criterion = new LinkedHashMap<>();

    ArrayList<LinkedHashMap<String, String>> tempCriterion = new ArrayList<>();
    try {
      ArrayList<String> lines = parseFileToLines(CriteriaFilename);
      for (String line : lines) {
        String[] criterionContents = line.split("\t");
        if (criterionContents.length != 3)
          badInput();
        String criteria = criterionContents[0];
        String level = criterionContents[1];
        String descriptor = criterionContents[2];

        LinkedHashMap<String, String> map0 = new LinkedHashMap<>();

        map0.put("criteria", criteria);
        map0.put("level", level);
        map0.put("descriptor", descriptor);
        tempCriterion.add(map0);
      }
    } catch (Exception e) {
      // e.printStackTrace();
      badInput();
      return;
    }
    for (LinkedHashMap<String, String> y : tempCriterion) {
      String criteria_y = y.get("criteria");
      LinkedHashMap<String, String> levels = criterion.get(criteria_y);
      if (levels != null)
        continue;
      levels = new LinkedHashMap<>();
      for (LinkedHashMap<String, String> x : tempCriterion) {
        String criteria_x = x.get("criteria");
        String level_x = x.get("level");
        String descriptor_x = x.get("descriptor");
        if (criteria_x.equals(criteria_y)) {
          levels.put(level_x, descriptor_x);
        }
      }
      criterion.put(criteria_y, levels);
    }
    instructor.design(AssignmentId, criterion);
  }

  // printRubric [AssignmentID]
  private static void handlePrintRubric(String[] words) {
    if (words.length != 2) {
      badInput();
      return;
    }
    DO(words);
    String assignmentID = words[1];
    instructor.printRubric(assignmentID);
  }

  // calculateScore [AssignmentID] [StudentID] [RankingStrategy]
  private static void handleCalculateScore(String[] words) {
    if (words.length != 4 || students.get(words[2]) == null) {
      badInput();
      return;
    }
    DO(words);
    String assignmentID = words[1], studentID = words[2], strategyName = words[3];
    if (!setStrategy(strategyName)) {
      badInput();
      return;
    }

    instructor.calculateScore(assignmentID, students.get(studentID));
  }

  // averageCriterion [AssignmentID]
  private static void handleAverageCriterion(String[] words) {
    if (words.length != 2) {
      badInput();
      return;
    }
    DO(words);
    instructor.averageCriterion(words[1]);
  }

  // findStrength [AssignmentID] [StudentID] [RankingStrategy]
  private static void handleFindStrength(String[] words) {
    if (words.length != 4 || students.get(words[2]) == null) {
      badInput();
      return;
    }
    DO(words);
    String assignmentID = words[1], studentID = words[2], strategyName = words[3];

    if (!setStrategy(strategyName)) {
      badInput();
      return;
    }
    instructor.findStrength(assignmentID, students.get(studentID));
  }

  // findWeakness [AssignmentID] [StudentID] [RankingStrategy]
  private static void handleFindWeakness(String[] words) {
    if (words.length != 4 || students.get(words[2]) == null) {
      badInput();
      return;
    }
    DO(words);
    String assignmentID = words[1], studentID = words[2], strategyName = words[3];
    if (!setStrategy(strategyName)) {
      badInput();
      return;
    }
    instructor.findWeakness(assignmentID, students.get(studentID));
  }

  private static boolean setStrategy(String strategyName) {
    // DO(words);
    String rankingStrategy = strategyName;
    RankingStrategy strategy;
    if (rankingStrategy.equals("MeanRankingStrategy")) {
      strategy = new MeanRankingStrategy();
    } else if (rankingStrategy.equals("MedianRankingStrategy")) {
      strategy = new MedianRankingStrategy();
    } else {
      return false;
    }
    instructor.setStrategy(strategy);
    return true;
  }

  private static void DEBUG(String s) {
    boolean isPrintLog = false;
    if (isPrintLog) {
      System.out.println("DEBUG: " + s);
    }
  }

  private static void DO(String[] words) {
    boolean isPrintLog = false;
    if (isPrintLog) {
      System.out.print("DO: ");
      for (String word : words) {
        System.out.print(word + " ");
      }
      System.out.println();
    }
  }

  private static void TODO(String[] words) {
    boolean isPrintLog = false;
    if (isPrintLog) {
      System.out.print("TODO: ");
      for (String word : words) {
        System.out.print(word + " ");
      }
      System.out.println();
    }
  }

  private static void DEBUG_PARSE(String s) {
    boolean isPrintLog = false;
    if (isPrintLog) {
      System.out.println("DEBUG_PARSE: " + s);
    }
  }

  private static void badInput() {
    System.out.println("Error");
  }

  private static ArrayList<String> parseFileToLines(String inFileName) throws Exception {
    ArrayList<String> lines = new ArrayList<>();
    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inFileName)));
    while (true) {
      String line = bufferedReader.readLine();
      // DO(line);
      if (line == null)
        break;
      if (line.equals(""))
        continue;

      lines.add(line);
    }
    bufferedReader.close();
    return lines;
  }
}