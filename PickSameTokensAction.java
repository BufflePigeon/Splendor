
/**
 * Décrivez votre classe PickSameTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickSameTokensAction implements Action {
    private  Resource tokenType;

    public PickSameTokensAction(Resource tokenType) {
        this.tokenType = tokenType;
    }

    
    public void process(Board board, Player player) {
        board.getNbResource(tokenType);
        board.updateNbResource(tokenType, -2);
        player.updateNbResource(tokenType, 2);

    }

    
    public String toString() {
        return "Prendre deux jetons de la ressource : " + tokenType;
    }
}