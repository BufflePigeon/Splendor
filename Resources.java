import java.util.ArrayList;

/**
 * La classe Resources représente un ensemble de ressources,
 * gérées sous forme d'une liste où chaque index correspond à une ressource particulière.
 */
public class Resources {

    private ArrayList<Integer> resources; // Liste des quantités pour chaque ressource

    /**
     * Constructeur par défaut.
     * Initialise la liste des ressources avec 5 éléments, tous à zéro.
     */
    public Resources() {
        resources = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) { // Par défaut, on suppose 5 types de ressources
            resources.add(0); // Initialise chaque ressource à 0
        }
    }

    /**
     * Retourne la liste brute des ressources.
     * @return Liste des quantités de ressources.
     */
    public ArrayList<Integer> getList() {
        return resources;
    }

    /**
     * Retourne la quantité d'une ressource donnée.
     * @param elt Ressource à interroger.
     * @return Quantité associée à la ressource.
     * @throws InvalidResourceException si la ressource est invalide.
     */
    public int getNbResource(Resource elt) {
        int index = elt.ordinal(); // Obtient l'index basé sur l'ordre défini dans l'énumération Resource
        if (index >= resources.size()) { // Vérifie si l'index est valide
            throw new InvalidResourceException("La ressource " + elt + " n'est pas valide ou non initialisée.");
        }
        return resources.get(index); // Retourne la quantité pour la ressource spécifiée
    }

    /**
     * Définit une nouvelle quantité pour une ressource.
     * @param elt Ressource à modifier.
     * @param valeur Nouvelle quantité à attribuer.
     * @throws InvalidResourceException si la ressource est invalide ou si la valeur est négative.
     */
    public void setNbResource(Resource elt, int valeur) {
        if (valeur < 0) { // Vérifie que la valeur est non négative
            throw new InvalidResourceException("La valeur de la ressource " + elt + " ne peut pas être négative.");
        }
        int index = elt.ordinal();
        if (index > resources.size()) { // Si l'index dépasse la taille actuelle, c'est une ressource invalide
            throw new InvalidResourceException("Ressource invalide : " + elt);
        } else if (index == resources.size()) { // Si l'index est égal à la taille, ajoute un nouvel élément
            resources.add(valeur);
        }
        resources.set(index, valeur); // Met à jour la valeur existante
    }

    /**
     * Met à jour la quantité d'une ressource en ajoutant ou retirant une valeur donnée.
     * @param elt Ressource à mettre à jour.
     * @param valeur Quantité à ajouter (peut être négative pour retirer).
     * @throws InvalidResourceException si la ressource est invalide.
     * @throws InsufficientResourceException si le retrait entraîne une quantité négative.
     */
    public void updateNbResource(Resource elt, int valeur) {
        int index = elt.ordinal();
        if (index >= resources.size()) { // Vérifie que la ressource existe
            throw new InvalidResourceException("Ressource invalide : " + elt);
        }
        int currentValue = resources.get(index); // Quantité actuelle
        if (currentValue + valeur < 0) { // Vérifie qu'il y a suffisamment de ressources pour effectuer le retrait
            throw new InsufficientResourceException("Ressources insuffisantes pour " + elt + ". Disponible : " + currentValue);
        }
        resources.set(index, currentValue + valeur); // Met à jour la nouvelle quantité
    }

    /**
     * Retourne une liste des ressources disponibles (quantité > 0).
     * @return Liste des ressources disponibles.
     */
    public ArrayList<Resource> getAvailableResources() {
        ArrayList<Resource> res = new ArrayList<Resource>();
        for (Resource x : Resource.values()) { // Parcourt toutes les ressources définies dans l'énumération Resource
            if (this.getNbResource(x) > 0) { // Vérifie si la quantité est supérieure à 0
                res.add(x); // Ajoute la ressource à la liste des ressources disponibles
            }
        }
        return res;
    }
}
