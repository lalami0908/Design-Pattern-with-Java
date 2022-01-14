import java.io.*;

public class Main {
    private static void excuteCommand(Application application, String[] command){

        String commandType = command[0];

        switch (commandType) {
            case "Draw": {
                application.addDocument(new DrawingDocument());
                break;
            }
            case "Text":{
                application.addDocument(new TextDocument());
                break;
            }
            case "Present":{
                application.display();
                break;
            }
            default: {

            }
        }
    }

    public static void main(String[] args) throws IOException{
        // read file
        try{
            for (String filename : args){
                File file = new File(filename);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String strLine;
                Application application = new Application();

                while ((strLine = br.readLine()) != null){
//                    System.out.println(strLine);
                    String[] command = strLine.split(" ");
                    excuteCommand( application, command);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File reading error.");
            e.printStackTrace();
        }
    }

}