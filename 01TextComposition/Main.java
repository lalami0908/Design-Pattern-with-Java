import java.io.*;
import java.util.*;



public class Main {
    private static void excuteCommand(Composition composition, String[] command){

        String commandType = command[0];
        switch (commandType){
            case "Text":{
                int componentId = Integer.parseInt( command[1]);
                int naturalSize = Integer.parseInt( command[2]);
                int stretchability = Integer.parseInt( command[3]);
                int shrinkability = Integer.parseInt( command[4]);
                String conetent = command[5];
                composition.addComponent(new Text(componentId,naturalSize,  shrinkability,stretchability,conetent));

                break;
            }
            case "GraphicalElement": {
                int componentId = Integer.parseInt( command[1]);
                int naturalSize = Integer.parseInt( command[2]);
                int stretchability = Integer.parseInt( command[3]);
                int shrinkability = Integer.parseInt( command[4]);
                String conetent = command[5];
                composition.addComponent(new Graphical(componentId,naturalSize, shrinkability,stretchability ,conetent));
                break;
            }
            case "ChangeSize": {
                int componentId = Integer.parseInt(command[1]);
                int newSize = Integer.parseInt(command[2]);
                Component component = composition.getComponent(componentId);
                component.changeSize(newSize);
                break;
            }
            case "Require":{
                String breakStrategy = command[1];
                switch (breakStrategy){

                    case "SimpleComposition":
                        composition.setCompositor(new SimpleCompositor());
                        break;
                    case "TexComposition":
                        composition.setCompositor(new TexCompositor());
                        break;
                    case "ArrayComposition":
                        composition.setCompositor(new ArrayCompositor());
                        break;
                    default:
                        System.out.println("None breakStrategy: " + breakStrategy);
                }
                composition.arrange();
                break;
            }
            default:

        }
    }

    public static void main(String[] args) throws IOException{
        try{
            for (String filename : args){
                File file = new File(filename);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String strLine;
                Composition composition = new Composition();
                while ((strLine = br.readLine()) != null){
//                    System.out.println(strLine);
                    String[] command = strLine.split(" ");
                    excuteCommand(composition, command);
                }
            }
        } catch (Exception e) {
            System.out.println("File reading error.");
            e.printStackTrace();
        }
    }
}
