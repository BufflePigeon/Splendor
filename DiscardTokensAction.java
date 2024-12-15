

/**
 * Décrivez votre classe DiscardTokensAction ici.
 *
 * @author Julien
 * @version 2.1
 */
public class DiscardTokensAction implements Action {
    private Resource token;
    private int nb;

    /**
     * Constructeur de la classe DiscardTokensAction.
     */
    public DiscardTokensAction(Resource token, int nb) {
        this.token = token;
        this.nb = nb;
    }

    /**
     * Exécute l'action de défausse.
     */    
    public void process(Board board, Player player) {
        board.updateNbResource(token, nb);
        player.updateNbResource(token, -nb);
    }

    /**
     * Affichage de l'action de défausse.
     */    
    public String toString() {
        String res = "Vous avez défaussé : " ;
        res = res + nb + " " + token.toString()+".";
        return res ;
    }
}