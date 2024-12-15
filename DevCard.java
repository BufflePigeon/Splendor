public class DevCard implements Displayable {
    // Représente une carte de développement dans le jeu

    private int tier; // Niveau de la carte (1, 2 ou 3)
    private Resources resources; // Coût en ressources pour acquérir la carte
    private int points; // Points de victoire que la carte rapporte
    private Resource resourceType; // Type de ressource produit par la carte

    // Constructeur pour initialiser une carte de développement
    public DevCard(int tier, Resources resources, int points, Resource resourceType) {
        // Validation du niveau : doit être compris entre 1 et 3 inclus
        if (tier < 1 || tier > 3) {
            throw new InvalidDevCardException("Le niveau de la carte doit être entre 1 et 3. Valeur fournie : " + tier);
        }
        // Validation des points de victoire : doivent être positifs
        if (points < 0) {
            throw new InvalidDevCardException("Les points de victoire doivent être positifs. Valeur fournie : " + points);
        }
        // Validation du type de ressource : ne peut pas être nul
        if (resourceType == null) {
            throw new InvalidDevCardException("Le type de ressource ne peut pas être nul.");
        }
        // Initialisation des attributs
        this.tier = tier;
        this.resources = resources;
        this.points = points;
        this.resourceType = resourceType;
    }

    // Retourne le niveau de la carte
    public int getTier() {
        return tier;
    }

    // Retourne le coût en ressources pour acquérir la carte
    public Resources getCost() {
        return resources;
    }

    // Retourne les points de victoire de la carte
    public int getPoints() {
        return points;
    }

    // Retourne le type de ressource produit par la carte
    public Resource getResourceType() {
        return resourceType;
    }

    // Génère une représentation textuelle de la carte sous forme d'un tableau de chaînes
    public String[] toStringArray() {
        /** EXEMPLE
         * ┌────────┐
         * │①    ♠S│
         * │        │
         * │        │
         * │2 ♠S    │
         * │2 ♣E    │
         * │3 ♥R    │
         * └────────┘
         */
        String pointStr = "  "; // Espace vide par défaut pour les points

        // Si la carte rapporte des points, les représenter en caractères Unicode
        if (getPoints() > 0) {
            pointStr = new String(new int[]{getPoints() + 9311}, 0, 1);
        }

        // Création de la structure visuelle de la carte
        String[] cardStr = {
                "\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                "\u2502" + pointStr + "    " + resourceType.toSymbol() + "\u2502",
                "\u2502        \u2502",
                "\u2502        \u2502",
                "\u2502        \u2502",
                "\u2502        \u2502",
                "\u2502        \u2502",
                "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"
        };

        // Ajout des coûts en ressources à la représentation
        int i = 6; // Ligne à remplir
        for (Resource res : Resource.values()) {
            // Vérifier si la carte nécessite cette ressource
            if (getCost().getNbResource(res) > 0) {
                cardStr[i] = "\u2502" + getCost().getNbResource(res) + " " + res.toSymbol() + "    \u2502";
                i--; // Remonter d'une ligne
            }
        }
        return cardStr;
    }

    // Génère une représentation textuelle pour une "carte vide"
    public static String[] noCardStringArray() {
        /** EXEMPLE
         * ┌────────┐
         * │ \    / │
         * │  \  /  │
         * │   \/   │
         * │   /\   │
         * │  /  \  │
         * │ /    \ │
         * └────────┘
         */
        String[] cardStr = {
                "\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                "\u2502 \\    / \u2502",
                "\u2502  \\  /  \u2502",
                "\u2502   \\/   \u2502",
                "\u2502   /\\   \u2502",
                "\u2502  /  \\  \u2502",
                "\u2502 /    \\ \u2502",
                "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"
        };

        return cardStr;
    }

    // Génère une description textuelle simple de la carte
    public String toString() {
        String cardStr = "";

        // Ajouter les points, le type de ressource et le coût en ressources
        cardStr = getPoints() + "pts, type " + resourceType.toSymbol() + " | coût: ";
        for (Resource res : Resource.values()) {
            // Ajouter chaque ressource nécessaire si la quantité est supérieure à zéro
            if (getCost().getNbResource(res) > 0) {
                cardStr += getCost().getNbResource(res) + res.toSymbol() + " ";
            }
        }
        return cardStr;
    }
}
