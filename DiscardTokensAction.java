import java.util.ArrayList;

/**
 * Décrivez votre classe DiscardTokensAction ici.
 *
 * @author Julien
 * @version 1.1
 */
public class DiscardTokensAction implements Action {
    private Resource token;
    private int nb;

    public DiscardTokensAction(Resource token, int nb) {
        this.token = token;
        this.nb = nb;
    }

    
    public void process(Board board, Player player) {
        board.updateNbResource(token, nb);
        player.updateNbResource(token, -nb);
    }

    
    public String toString() {
        String res = "Vous avez défaussé : " ;
        res = res + nb + " " + token.toString()+".";
        return res ;
    }
}