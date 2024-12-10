
/**
 * Décrivez votre classe BuyCardAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class BuyCardAction implements Action {
    private DevCard card;

    public BuyCardAction(DevCard card) {
        this.card = card;
    }

    
    public void process(Game game) {
        game.getBoard().drawcard();
    }

    
    public String toString() {
        return "Acheter la carte avec l'ID : " + card;
    }
}