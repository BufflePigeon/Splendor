
/**
 * Décrivez votre classe PickSameTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickSameTokensAction implements Action {
    private final String tokenType;

    public PickSameTokensAction(String tokenType) {
        this.tokenType = tokenType;
    }

    
    public void process(Game game) {
        ;
    }

    
    public String toString() {
        return "Prendre deux jetons de la ressource : " + tokenType;
    }
}