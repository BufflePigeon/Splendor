import java.util.ArrayList;

/**
 * Décrivez votre classe DumbRobotPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DumbRobotPlayer extends Player
{
    public DumbRobotPlayer(int id, String nom){
        super(id,nom);
    }
    public int chooseAction(Board board){ // pas vraiment du tout sur
        ArrayList<Resource> liste_res = new ArrayList<Resource>();
        for (int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                if (super.canBuyCard(board.getVisibleCards()[i][j])){
                    BuyCardAction action1 = new BuyCardAction(board.getVisibleCards()[i][j]);
                    action1.process(board , i,j);
                    return 0;
                }
            }
        }
        for (Resource res: Resource.values()){
            if (board.getNbResource(res)>3){
                PickSameTokensAction action2 = new PickSameTokensAction(res);
                action2.process(board);
                return 0;
            }
        }
        for (Resource res: Resource.values()){
            if (board.getNbResource(res)>0){
                liste_res.add(res);
            }
            if (liste_res.size()==3){
                PickDiffTokensAction action3 = new PickDiffTokensAction(liste_res);
                action3.process(board);
                return 0;
            }
        }
        while (super.getNbTokens()>10){
            for (Resource res: Resource.values()){
                if (super.getNbResource(res)>0){
                    super.updateNbResource(res, -1);
                }
            }
        }
        PassAction action4 = new PassAction();
        action4.process();
        return 0;
    }
}
