
/**
 * Décrivez votre classe PickSameTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickSameTokensAction implements Action {
    private  Resource tokenType;

    /**
     * Constructeur de la classe PickSameTokensAction.
     */
    public PickSameTokensAction(Resource tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Exécute l'action de prendre 2 ressources identiques.
     */        
    public void process(Board board, Player player) {
        board.getNbResource(tokenType);
        board.updateNbResource(tokenType, -3);
        player.updateNbResource(tokenType, 3);

    }

    /**
     * Affichage de l'action de prendre 2 ressources identiques.
     */        
    public String toString() {
        return "Prendre deux jetons de la ressource : " + tokenType;
    }
}