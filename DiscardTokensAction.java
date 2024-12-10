
/**
 * Décrivez votre classe DiscardTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DiscardTokensAction implements Action {
    private final String tokenType;
    private final int count;

    public DiscardTokensAction(String tokenType, int count) {
        this.tokenType = tokenType;
        this.count = count;
    }

    
    public void process(Game game) {
        ;
    }

    
    public String toString() {
        return "Défausser " + count + " jetons de la ressource : " + tokenType;
    }
}