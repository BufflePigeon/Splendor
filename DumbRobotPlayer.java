import java.util.ArrayList;

/**
 * Classe représentant un robot joueur basique qui hérite de Player.
 * Ce robot joue de manière déterministe et simple, en suivant une logique prédéfinie.
 */
public class DumbRobotPlayer extends Player {

    /**
     * Constructeur de la classe DumbRobotPlayer.
     * 
     * @param id  Identifiant unique du joueur.
     * @param nom Nom du joueur.
     */
    public DumbRobotPlayer(int id, String nom) {
        super(id, nom);
    }

    /**
     * Permet au robot de choisir une action sur le plateau de jeu.
     * 
     * @param board Le plateau de jeu actuel.
     * 
     * La logique suivie par le robot est la suivante :
     * 1. Si une carte de développement peut être achetée, il l'achète.
     * 2. Sinon, si une ressource est disponible en quantité suffisante (>3), il prend deux jetons de cette ressource.
     * 3. Sinon, si trois types de ressources différents sont disponibles, il prend un jeton de chaque type.
     * 4. Sinon, il passe son tour.
     */
    public void chooseAction(Board board) {
        ArrayList<Resource> liste_res = new ArrayList<Resource>();

        // Vérifie si une carte de développement peut être achetée
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (super.canBuyCard(board.getVisibleCards()[i][j])) {
                    BuyCardAction action1 = new BuyCardAction(board.getVisibleCards()[i][j], i, j);
                    action1.process(board, this);
                    return;
                }
            }
        }

        // Si aucune carte ne peut être achetée, essaye de prendre deux jetons identiques
        for (Resource res : Resource.values()) {
            if (board.getNbResource(res) > 3) {
                PickSameTokensAction action2 = new PickSameTokensAction(res);
                action2.process(board, this);
                return;
            }
        }

        // Sinon, essaye de prendre trois jetons de types différents
        for (Resource res : Resource.values()) {
            if (board.getNbResource(res) > 0) {
                liste_res.add(res);
            }
            if (liste_res.size() == 3) {
                PickDiffTokensAction action3 = new PickDiffTokensAction(liste_res);
                action3.process(board, this);
                return;
            }
        }

        // Si aucune autre action n'est possible, passe son tour
        PassAction action4 = new PassAction();
        action4.process(board, this);
        return;
    }

    /**
     * Permet au robot de choisir les jetons à défausser si le nombre de jetons dépasse la limite autorisée.
     * 
     * @param board Le plateau de jeu actuel.
     * 
     * Le robot défausse un jeton de chaque type de ressource qu'il possède jusqu'à ce qu'il ait 10 jetons ou moins.
     */
    public void chooseDiscardingTokens(Board board) {
        while (super.getNbTokens() > 10) {
            for (Resource res : Resource.values()) {
                if (super.getNbResource(res) > 0) {
                    super.updateNbResource(res, -1);
                }
            }
        }
    }
}
