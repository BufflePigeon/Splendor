import java.util.ArrayList;

public class Resources {
    private ArrayList<Integer> resources;

    public Resources() {
        resources = new ArrayList<Integer>();
    }

    public ArrayList getList(){
        return resources;
    }

    public int getNbResource(Resource elt){
        int i = 0;
        for(Resource valeur: Resource.values()){
            if (valeur == elt){
                return resources.get(i);
            }
            i ++;
        }
        return -1;
    }

    public void setNbResource(Resource elt, int valeur){
        int i = 0;
        for(Resource x: Resource.values()){
            if (elt == x){
                resources.set(i, valeur);
            }
            i ++;
        }
    }

    public void updateNbResource(Resource elt, int valeur){
        int i = 0;
        for(Resource x: Resource.values()){
            if (elt == x){
                if (resources.get(i)+valeur >= 0) {
                    resources.set(i, resources.get(i)+valeur);
                }
            }
            i ++;
        }
    }

    public ArrayList getAvailableResources(){
        ArrayList<Resource> res = new ArrayList<Resource>();
        for(Resource x: Resource.values()){
            if (this.getNbResource(x) > 0){
                res.add(x);
            }
        }
        return res;
    }
}