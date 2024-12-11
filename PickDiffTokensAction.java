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

    public void process(Board board) {
        for (Resource ressource : tokenTypes){
            board.getNbResource(ressource);
        }
    }

    
    public String toString() {
        String res = "Prendre trois jetons de ressources différentes :" ;
        for (Resource ressource : tokenTypes)
            switch(ressource){
                case 0:// à changer
                    res += " " + Resource.DIAMOND ;
                case 1:
                    res += " " + Resource.SAPPHIRE ;
                case 2:
                    res += " " + Resource.EMERALD ;
                case 3:
                    res += " " + Resource.RUBY ;
                case 4:
                    res += " " + Resource.ONYX ;
            }
        res += "." ;
        return res ;
    }
}