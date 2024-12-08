
/**
 * Décrivez votre classe BuyCardAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class BuyCardAction implements Action {
    private final int cardId;

    public BuyCardAction(int cardId) {
        this.cardId = cardId;
    }

    
    public void process(Game game) {
        ;
    }

    
    public String toString() {
        return "Acheter la carte avec l'ID : " + cardId;
    }
}