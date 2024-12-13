import java.util.ArrayList;

public class Resources {
    private ArrayList<Integer> resources;

    public Resources() {
        resources = new ArrayList<Integer>();
        for (int i=0; i<5;i++){
            resources.add(0);
        }
    }

    public ArrayList<Integer> getList(){
        return resources;
    }

    public int getNbResource(Resource elt) {
        int index = elt.ordinal();
        if (index >= resources.size()) {
            throw new InvalidResourceException("La ressource " + elt + " n'est pas valide ou non initialisée.");
        }
        return resources.get(index);
    }

    public void setNbResource(Resource elt, int valeur) {
        if (valeur < 0) {
            throw new InvalidResourceException("La valeur de la ressource " + elt + " ne peut pas être négative.");
        }
        int index = elt.ordinal();
        if (index > resources.size()) {
            throw new InvalidResourceException("Ressource invalide : " + elt);
        }else if(index == resources.size()){
            resources.add(valeur) ;
        }
        resources.set(index, valeur);
        System.out.println(resources.get(index));
    }

    public void updateNbResource(Resource elt, int valeur) {
        int index = elt.ordinal();
        if (index >= resources.size()) {
            throw new InvalidResourceException("Ressource invalide : " + elt);
        }
        int currentValue = resources.get(index);
        if (currentValue + valeur < 0) {
            throw new InsufficientResourceException("Ressources insuffisantes pour " + elt + ". Disponible : " + currentValue);
        }
        resources.set(index, currentValue + valeur);
    }

    public ArrayList<Resource> getAvailableResources(){
        ArrayList<Resource> res = new ArrayList<Resource>();
        for(Resource x: Resource.values()){
            if (this.getNbResource(x) > 0){
                res.add(x);
            }
        }
        return res;
    }
}