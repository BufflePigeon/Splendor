
/**
 * Décrivez votre classe PickDiffTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickDiffTokensAction implements Action {
    private final String[] tokenTypes;

    public PickDiffTokensAction(String[] tokenTypes) {
        if (tokenTypes.length != 3) {
            throw new IllegalArgumentException("Il faut exactement trois types de ressources différents.");
        }
        this.tokenTypes = tokenTypes;
    }

    public void process(Game game) {
        ;
    }

    
    public String toString() {
        return "Prendre trois jetons de ressources différentes : " + String.join(", ", tokenTypes);
    }
}