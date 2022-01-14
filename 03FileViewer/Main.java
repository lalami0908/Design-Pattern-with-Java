import java.io.*;

public class Main {
    private static void excuteCommand(FileViewer fileViewer, String[] command){
        String textViewName = command[0];
        String commandType = command[1];

        switch (commandType) {
            case "add": {
                for(int i = 2; i < command.length; i++){
                    String elementType = command[i];
                    switch(elementType){
                        case "scrollBar":{
                            fileViewer.getDataView(textViewName).addElement(new ScrollBar(elementType));
                            break;
                        }
                        case "thickBlackBorder":{
                            fileViewer.getDataView(textViewName).addElement(new ThickBlackBorder(elementType));
                            break;
                        }
                    }
                }
                break;
            }
            case "display":{
                fileViewer.getDataView(textViewName).display();
                break;
            }
            default: {
                String textViewData = command[1];
                fileViewer.addDataView(new TextView(textViewName, textViewData));
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
                FileViewer fileViewer = new FileViewer();

                while ((strLine = br.readLine()) != null){
//                    System.out.println(strLine);
                    String[] command = strLine.split(" ");
                    excuteCommand( fileViewer, command);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File reading error.");
            e.printStackTrace();
        }
    }

}