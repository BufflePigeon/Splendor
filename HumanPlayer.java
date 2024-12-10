import java.util.ArrayList;
import java.util.Scanner;

/**
 * Décrivez votre classe HumanPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class HumanPlayer extends Player{
    
    public HumanPlayer(int id, String nom){
        
        super(id,nom);
    }
    public int chooseAction(Board board){ // pas vraiment du tout sur
        Game.display.out.println("Voici les action possibles : ");
        Game.display.out.println("Entrez 1 : Acheter une carte sur le plateau");
        Game.display.out.println("Entrez 2 : Prendre deux jetons de ressources (même type)");
        Game.display.out.println("Entrez 3 : Prendre trois jetons de types de ressources differents");
        Scanner s = new Scanner(System.in);
        String choix = s.nextLine();
        if (Integer.valueOf(choix)<1 || Integer.valueOf(choix)>3){
            throw new InvalideChoiceException("Votre réponse n'est pas comprise entre 0 et 3.");
            chooseAction(board);
        }
        if (Integer.valueOf(choix)==1){
            chooseCard(board);
        } else if (Integer.valueOf(choix)==2){
            chooseUniqueRes(board);
        }
        
        
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
    
    public void chooseCard(Board board){
        Game.display.out.println("Choisissez la carte que vous souhaitez acheter :  ");
        // Aficher les cartes ?
        Game.display.out.println("Pour selectionner votre carte, ecrivez sous le format suivant : x/y");
        Game.display.out.println("Avec x le numéro de la ligne (entre 1 et 3)");
        Game.display.out.println("Avec y le numéro de la colone (entre 1 et 4)");
        Scanner s = new Scanner(System.in);
        System.out.println("Saisissez la ligne (entre 1 et 3)");
        String choix1 = s.nextLine();
        if (Integer.valueOf(choix1)<1 || Integer.valueOf(choix1)>3){
            throw new InvalideChoiceException("Votre x doit être compris entre 1 et 3 (le numéro de la ligne). Veuillez ressaisir votre x.");
            chooseCard(board);
        }
        System.out.println("Saisissez la colonne (entre 1 et 4)");
        String choix2 = s.nextLine();
        if (Integer.valueOf(choix2)<1 || Integer.valueOf(choix2)>4){
            throw new InvalideChoiceException("Votre y doit être compris entre 1 et 4 (le numéro de la colonne). Veuillez ressaisir le tout (x et y).");
            chooseCard(board);
        }
        if (super.canBuyCard(board.getVisibleCards()[Integer.valueOf(choix1)][Integer.valueOf(choix2)])){
            BuyCardAction action1 = new BuyCardAction(board.getVisibleCards()[Integer.valueOf(choix1)][Integer.valueOf(choix2)]);
            action1.process(board , Integer.valueOf(choix1),Integer.valueOf(choix2));
        } else {
            chooseAction(board);
        }
    }
    
    public void chooseUniqueRes(Board board){
        Game.display.out.println("Selectionner la ressource pour laquelle vous voulez recevoir 2 jetons : ");
        Game.display.out.println("Saisir 1 pour recevoir 2 diamants");
        Game.display.out.println("Saisir 2 pour recevoir 2 saphirs");
        Game.display.out.println("Saisir 3 pour recevoir 2 emeraudes");
        Game.display.out.println("Saisir 4 pour recevoir 2 rubis");
        Game.display.out.println("Saisir 5 pour recevoir 2 onyx");
        Game.display.out.println("Saisir 6 pour revenir en arrière");
        Scanner s = new Scanner(System.in);
        String choix = s.nextLine();
        if (Integer.valueOf(choix)<1 || Integer.valueOf(choix)>6){
            throw new InvalideChoiceException("Vous devez saisir un chiffre entre 1 et 6.");
            chooseUniqueRes(board);
        }
        if (board.getNbResource(Resource.values()[Integer.valueOf(choix)])>3){
            
            PickSameTokensAction action2 = new PickSameTokensAction(Resource.values()[Integer.valueOf(choix)]);
            action2.process(board);
        } else {
            throw new InsufficientResourceException("Le nombre de ces jetons restants est inférieur à 4");
            chooseAction(board);
        }
        
    }
}
