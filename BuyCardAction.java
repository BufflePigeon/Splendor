
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
        this.tier = tier;
        this.colomn=colomn;
    }

    
    public void process(Board board, Player player) {
        if (player.canBuyCard(card)){
            player.addPurchasedCard(card);
            int i = 0;
            for (int res : card.getCost().getList()){
                player.updateNbResource(Resource.values()[i],-res);
                i++;
            }
            player.updatePoints(card.getPoints());
            board.updateCard(card);
        } else {
            Game.display.out.println("Pas assez de ressources pour acheter la carte !");
        }
        
    }

    
    public String toString() {
        return "Acheter la carte avec l'ID : " + card;
    }
}