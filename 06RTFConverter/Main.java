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
                String line = bufferedReader.readLine();
                if (line == null)
                    break;
                if (!line.equals("TeX") && !line.equals("TextWidget")) {
                    System.out.println("Error");
                    continue;
                }

                String data = bufferedReader.readLine();
                if (data == null)
                    break;
                inputs.add(line);
                inputs.add(data);
            } catch (Exception e) {
                break;
            }
        }
        bufferedReader.close();

        RTFReader reader = new RTFReader(inputs);
        reader.convert();
    }
}