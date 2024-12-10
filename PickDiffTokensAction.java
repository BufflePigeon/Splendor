import java.util.ArrayList
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

    public void process(Game game) {
        board.canGiveDiffToken(tokenTypes);
    }

    
    public String toString() {
        String res = "Prendre trois jetons de ressources différentes :"
        for (Resource ressource : tokenTypes)
            res += " " + ressource
        res += "."
        return res
    }
}