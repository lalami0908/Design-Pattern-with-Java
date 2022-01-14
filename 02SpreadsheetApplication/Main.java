import java.io.*;

public class Main {
    private static void excuteCommand(ApplicationData applicationData, String[] command){
        String commandType = command[0];
        switch (commandType) {
            case "data": {
                String itemKey = command[1];
                int value = Integer.parseInt(command[2]);
                applicationData.addData(itemKey, value);
                break;
            }
            case "addChart": {
                String chartType =  command[1];
                Chart chart;
                switch (chartType) {
                    case "Spreadsheet":{
                        chart = new Spreadsheet();
                        break;
                    }
                    case "BarChart":{
                        chart = new BarChart();
                        break;
                    }
                    case "PieChart":{
                        chart = new PieChart();
                        break;
                    }
                    default:{
                        chart = new Spreadsheet();
                    }
                }
                chart.addApplicationData(applicationData);
                applicationData.attach(chartType, chart);
                break;
            }
            case "change": {
                String chartType =  command[1];
                String itemKey = command[2];
                int value = Integer.parseInt(command[3]);
                System.out.printf("%s change %s %d.\n", chartType, itemKey, value);
                applicationData.getChart(chartType).change(itemKey, value);
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
                ApplicationData applicationData = new ApplicationData();

                while ((strLine = br.readLine()) != null){
//                    System.out.println(strLine);
                    String[] command = strLine.split(" ");
                    excuteCommand( applicationData, command);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File reading error.");
            e.printStackTrace();
        }
    }

}