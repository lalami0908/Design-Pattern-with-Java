import java.util.ArrayList;

public class FileViewer {
    private ArrayList<DataView> dataViews = new ArrayList<DataView>();

    public void addDataView(DataView dataView){
        dataViews.add(dataView);
    }

    public DataView getDataView(String textViewName) {
        for(DataView dataView: dataViews){

            if(dataView.getViewName().equals(textViewName)){
                return dataView;
            }
        }
        System.out.print("cannot find!!");
        return null;
    }

    public void display() {
        for(DataView dataView: dataViews){
            dataView.display();
        }
    }
}
