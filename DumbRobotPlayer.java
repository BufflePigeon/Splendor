import java.util.ArrayList;

/**
 * Décrivez votre classe DumbRobotPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DumbRobotPlayer extends Player implements Displayable
{
    public DumbRobotPlayer(int id, String nom){
        super(id,nom);
    }
    public void chooseAction(Board board){ 
        ArrayList<Resource> liste_res = new ArrayList<Resource>();
        for (int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                if (super.canBuyCard(board.getVisibleCards()[i][j])){
                    BuyCardAction action1 = new BuyCardAction(board.getVisibleCards()[i][j],i,j);
                    action1.process(board , this);
                    return;
                }
            }
        }
        for (Resource res: Resource.values()){
            if (board.getNbResource(res)>3){
                PickSameTokensAction action2 = new PickSameTokensAction(res);
                action2.process(board,this);
                return;
            }
        }
        for (Resource res: Resource.values()){
            if (board.getNbResource(res)>0){
                liste_res.add(res);
            }
            if (liste_res.size()==3){
                PickDiffTokensAction action3 = new PickDiffTokensAction(liste_res);
                action3.process(board,this);
                return;
            }
        }
        
        PassAction action4 = new PassAction();
        action4.process(board,this);
        return;
    }
    public void chooseDiscardingTokens(Board board){
        while (super.getNbTokens()>10){
            for (Resource res: Resource.values()){
                if (super.getNbResource(res)>0){
                    super.updateNbResource(res, -1);
                }
            }
        }
    }
}
