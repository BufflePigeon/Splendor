import java.util.ArrayList ;
/**
 * Décrivez votre classe PickDiffTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickDiffTokensAction implements Action {
    private ArrayList<Resource> tokenTypes;

    public PickDiffTokensAction(ArrayList<Resource> tokenTypes) {
        if (tokenTypes.size() != 3) {
            throw new IllegalArgumentException("Il faut exactement trois types de ressources différents.");
        }
        this.tokenTypes = tokenTypes;
    }

    public void process(Board board, Player player) {
        for (Resource ressource : tokenTypes){
            board.getNbResource(ressource);
            board.updateNbResource(ressource, -1);
            player.updateNbResource(ressource, 1);
        }
    }

    
    public String toString() {
        String res = "Prendre trois jetons de ressources différentes :" ;
        for (Resource ressource : tokenTypes)
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