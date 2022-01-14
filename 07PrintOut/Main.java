import java.util.*;
import java.io.*;

public class Main {

    static HashMap<String, DataStructure> dataStructure = new HashMap<String, DataStructure>();
    public static void main(String[] args) {
        parseInputArgs(args);
    }

    private static void printMsg(String inString) {
        System.out.printf(inString);
    }

    private static void printError() {
        System.out.printf("Error\n");
    }

    /* 7 methods */
    private static void Create(String inStructureName, String inStructureType) {
        if(inStructureType.equals("List")) {
            dataStructure.put(inStructureName, new ListObject());
        } else if(inStructureType.equals("SkipList")) {
            dataStructure.put(inStructureName, new SkipList());
        } else
            printError();
    }

    private static void Add(String inStructureName, String inContent) {
        DataStructure curStructure = dataStructure.get(inStructureName);

        if (curStructure == null) {
            printError();
            return;
        }

        curStructure.addContent(inContent);
    }

    private static void Length(String inStructureName) {
        DataStructure curStructure = dataStructure.get(inStructureName);

        if (curStructure == null) {
            printError();
            return;
        }

        if(curStructure.getClass() == ListObject.class) {
            System.out.println(((ListObject)curStructure).length);
        } else if (curStructure.getClass() == SkipList.class) {
            System.out.println("SkipList can not access length");
        } else {
            printError();
        }
    }

    private static void Size(String inStructureName) {
        DataStructure curStructure = dataStructure.get(inStructureName);

        if (curStructure == null) {
            printError();
            return;
        }

        if(curStructure.getClass() == SkipList.class) {
            System.out.println(((SkipList) curStructure).size());
        } else if (curStructure.getClass() == ListObject.class) {
            System.out.println("List do not have method size");
        } else {
            printError();
        }
    }

    private static void Get(String inStructureName, int inIndex) {
        DataStructure curStructure = dataStructure.get(inStructureName);
        
        if (curStructure == null) {
            printError();
            return;
        }

        if(curStructure.getClass() == ListObject.class) {
            System.out.println(((ListObject) curStructure).get(inIndex).content);
        } else if (curStructure.getClass() == SkipList.class) {
            System.out.println("SkipList do not have method get");
        } else {
            printError();
        }
    }

    private static void GetNode(String inStructureName, int inIndex) {
        DataStructure curStructure = dataStructure.get(inStructureName);
        if (curStructure == null) {
            printError();
            return;
        }
        if(curStructure.getClass() == SkipList.class) {
            System.out.println("SkipNode:" + ((SkipList)curStructure).get(inIndex).content);
        } else if (curStructure.getClass() == ListObject.class) {
            System.out.println("List do not have method getNode");
        } else {
            printError();
        }
    }

    private static void PrintOutList(String inStructureName) {
        DataStructure curStructure = dataStructure.get(inStructureName);
        curStructure.printOutList();
    }

    /* Parser Body */
    private static Boolean parseInputArgs(String[] args) {

        File dataFile = new File(args[0]);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            try {
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                //    System.out.printf("Parsing -- %s\n",line);

                    String[] words = line.split(" ");
                    int wordCount = words.length;

                    if (wordCount == 2) {
                        String command = words[0]; 
                        String param1 = words[1];

                        if (command.equals("Length")) {
                            Main.Length(param1);
                        }
                        else if (command.equals("Size")) {
                            Main.Size(param1);
                        }
                        else if (command.equals("PrintOutList")) {
                            Main.PrintOutList(param1);
                        }
                        else {
                            Main.printError();
                        }
                    }
                    else if (wordCount == 3) {
                        String command = words[0];
                        String param1 = words[1];
                        String param2 = words[2];

                        if (command.equals("Create")) {
                            Main.Create(param1, param2);
                        }
                        else if (command.equals("Add")) {
                            Main.Add(param1, param2);
                        }
                        else if (command.equals("Get")) {
                            
                            int nValue = -1;
                            try {
                                nValue = Integer.parseInt(param2);
                            }
                            catch (Exception e) {
                            }

                            if (nValue < 0) {
                                Main.printError();
                            }
                            else {
                                Main.Get(param1, nValue);
                            }

                        }
                        else if (command.equals("GetNode")) {
                            int nValue = -1;
                            try {
                                nValue = Integer.parseInt(param2);
                            }
                            catch (Exception e) {
                            }

                            if (nValue < 0) {
                                Main.printError();
                            }
                            else {
                                Main.GetNode(param1, nValue);
                            }
                        }
                        else {
                            Main.printError();
                        }
                    }
                    else {
                        Main.printError();
                    }

                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } catch (FileNotFoundException ex) {

        }
        return true;
    }
}