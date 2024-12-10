import java.util.ArrayList ;
/**
 * Décrivez votre classe PickDiffTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickDiffTokensAction implements Action {
    private Resources tokenTypes;

    public PickDiffTokensAction(Resources tokenTypes) {
        if (tokenTypes.getList().size() != 3) {
            throw new IllegalArgumentException("Il faut exactement trois types de ressources différents.");
        }
        this.tokenTypes = tokenTypes;
    }

    public void process(Game game) {
        game.getBoard().canGiveDiffTokens(tokenTypes);
    }

    
    public String toString() {
        String res = "Prendre trois jetons de ressources différentes :" ;
        for (Integer ressource : tokenTypes.getList())
            switch(ressource){
                case 0:
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