import java.util.ArrayList;

public class Resources {
    private ArrayList<int> resources;

    public Resources() {
        resources = new ArrayList<Resource>();
    }

    public ArrayList getList(){
        return resources;
    }

    public int getNbResource(String name){
        if (name == "DIAMOND") {
            return resources.get(0);
        }
    }
}