import java.util.LinkedHashMap;

public abstract class Chart {
    private ApplicationData applicationData;

    public void addApplicationData(ApplicationData applicationData){
        this.applicationData =  applicationData;
    };
    public void update(){
        LinkedHashMap<String, Integer> items = applicationData.getData();
        display(items);
    }
    public void change(String itemKey, int value){
        applicationData.change(itemKey, value);

    }

    public abstract void display( LinkedHashMap<String, Integer> items);
}
