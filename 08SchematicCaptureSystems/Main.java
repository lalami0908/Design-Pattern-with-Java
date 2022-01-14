import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(args[0])));

        ArrayList<String> inputs = new ArrayList<String>();
        while (true) {
            try {
                String line = bufferedReader.readLine().trim();
                if (line == null)
                    break;

                inputs.add(line);
            } catch (Exception e) {
                break;
            }
        }
        bufferedReader.close();
        
        Application application = new Application(inputs);
        application.parseInputs();
        application.printAnswer();
    }
}