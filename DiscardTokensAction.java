import java.util.ArrayList;

/**
 * Décrivez votre classe DiscardTokensAction ici.
 *
 * @author Julien
 * @version 1.1
 */
public class DiscardTokensAction implements Action {
    private ArrayList<Resource> tokens;

    public DiscardTokensAction(ArrayList<Resource> tokens) {
        this.tokens = tokens;
    }

    
    public void process(Board board, Player player) {
        for (Resource ressource : tokens){
            board.updateNbResource(ressource, 1);
            player.updateNbResource(ressource, -1);
        };
    }

    
    public String toString() {
        String res = "Vous avez défaussé :" ;
        for (Resource ressource : tokens)
            switch(ressource){
                case DIAMOND:// à changer
                    res += " " + Resource.DIAMOND ;
                case SAPPHIRE:
                    res += " " + Resource.SAPPHIRE ;
                case EMERALD:
                    res += " " + Resource.EMERALD ;
                case RUBY:
                    res += " " + Resource.RUBY ;
                case ONYX:
                    res += " " + Resource.ONYX ;
            }
        res += "." ;
        return res ;
    
    }
}