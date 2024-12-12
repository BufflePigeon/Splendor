
/**
 * Décrivez votre classe BuyCardAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class BuyCardAction implements Action {
    private DevCard card;
    private int tier;
    private int colomn;


    public BuyCardAction(DevCard card, int tier, int colomn) {
        this.card = card;
    }

    
    public void process(Board board, Player player) {
        board.getCard(tier, colomn);
    }

    
    public String toString() {
        return "Acheter la carte avec l'ID : " + card;
    }
}