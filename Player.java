import java.util.ArrayList;

/**
 * Classe abstraite représentant un joueur dans le jeu. Implémente l'interface Displayable.
 */
public abstract class Player implements Displayable {
    private int id; // Identifiant unique du joueur
    private String name; // Nom du joueur
    private int points; // Points du joueur
    private ArrayList<DevCard> purchasedCards; // Liste des cartes de développement achetées par le joueur
    private Resources resources; // Ressources possédées par le joueur

    /**
     * Constructeur de la classe Player.
     * 
     * @param id   Identifiant unique du joueur.
     * @param name Nom du joueur.
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        points = 0;
        resources = new Resources();
        purchasedCards = new ArrayList<DevCard>();
    }

    /**
     * Retourne le nom du joueur.
     * 
     * @return Le nom du joueur.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne les points du joueur.
     * 
     * @return Le nombre de points du joueur.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Retourne le nombre total de jetons de ressources que le joueur possède.
     * 
     * @return Le nombre total de jetons de ressources.
     */
    public int getNbTokens() {
        int sum = 0;
        for (Resource res : Resource.values()) {
            sum += resources.getNbResource(res);
        }
        return sum;
    }

    /**
     * Retourne le nombre de cartes de développement achetées par le joueur.
     * 
     * @return Le nombre de cartes de développement achetées.
     */
    public int getNbPurshasedCards() {
        return purchasedCards.size();
    }

    /**
     * Retourne le nombre d'une ressource spécifique que possède le joueur.
     * 
     * @param resource Le type de ressource.
     * @return Le nombre de la ressource spécifiée.
     */
    public int getNbResource(Resource resource) {
        return resources.getNbResource(resource);
    }

    /**
     * Retourne la liste des types de ressources que le joueur possède en quantité non nulle.
     * 
     * @return Une liste des types de ressources disponibles.
     */
    public ArrayList<Resource> getAvailableResources() {
        return resources.getAvailableResources();
    }

    /**
     * Calcule le nombre d'une ressource spécifique obtenue à partir des cartes de développement achetées.
     * 
     * @param resource Le type de ressource.
     * @return Le nombre de la ressource spécifiée provenant des cartes.
     */
    public int getResFromCards(Resource resource) {
        int sum = 0;
        for (DevCard card : purchasedCards) {
            if (card.getResourceType() == resource) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Met à jour la quantité d'une ressource spécifique que possède le joueur.
     * 
     * @param resource Le type de ressource.
     * @param value    La quantité à ajouter (ou à soustraire si négative).
     */
    public void updateNbResource(Resource resource, int value) {
        resources.updateNbResource(resource, value);
    }

    /**
     * Met à jour les points du joueur en ajoutant une valeur spécifiée.
     * 
     * @param value La valeur à ajouter aux points du joueur.
     */
    public void updatePoints(int value) {
        points += value;
    }

    /**
     * Ajoute une carte de développement achetée à la liste des cartes du joueur.
     * 
     * @param card La carte de développement à ajouter.
     */
    public void addPurchasedCard(DevCard card) {
        purchasedCards.add(card);
    }

    /**
     * Méthode abstraite pour choisir une action.
     * Doit être implémentée par les sous-classes.
     * 
     * @param board Le plateau de jeu.
     */
    public abstract void chooseAction(Board board);

    /**
     * Méthode abstraite pour choisir les jetons à défausser.
     * Doit être implémentée par les sous-classes.
     * 
     * @param board Le plateau de jeu.
     */
    public abstract void chooseDiscardingTokens(Board board);

    /**
     * Détermine si le joueur peut acheter une carte de développement spécifiée.
     * 
     * @param card La carte de développement à vérifier.
     * @return True si le joueur peut acheter la carte, sinon False.
     */
    public boolean canBuyCard(DevCard card) {
        Resources cost = card.getCost();
        for (int i = 0; i < cost.getList().size(); i++) {
            if (cost.getList().get(i) > resources.getList().get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Convertit les données du joueur en un tableau de chaînes pour l'affichage.
     * Format exemple :
     * Player 1: Camille
     * ⓪pts
     * ♥R (0) [0]
     * ●O (0) [0]
     * ♣E (0) [0]
     * ♠S (0) [0]
     * ♦D (0) [0]
     * 
     * @return Un tableau de chaînes représentant l'état du joueur.
     */
    public String[] toStringArray() {
        String pointStr = " ";
        String[] strPlayer = new String[8];

        if (points > 0) {
            pointStr = new String(new int[] { getPoints() + 9311 }, 0, 1);
        } else {
            pointStr = "\u24EA";
        }

        strPlayer[0] = "Player " + id + ": " + name;
        strPlayer[1] = pointStr + "pts";
        strPlayer[2] = "";

        for (Resource res : Resource.values()) { // Parcourt tous les types de ressources
            strPlayer[3 + (Resource.values().length - 1 - res.ordinal())] =
                res.toSymbol() + " (" + resources.getNbResource(res) + ") [" + getResFromCards(res) + "]";
        }

        return strPlayer;
    }
}
