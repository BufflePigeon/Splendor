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
    
    public void chooseAction(Board board){
        Game.display.out.println("Voici les action possibles : ");
        Game.display.out.println("");
        Game.display.out.println("Entrez 1 : Acheter une carte sur le plateau");
        Game.display.out.println("Entrez 2 : Prendre deux jetons de ressources (même type)");
        Game.display.out.println("Entrez 3 : Prendre trois jetons de types de ressources differents");
        Game.display.out.println("Entrez 4 : Passez votre tour");
        Game.display.out.println("");
        Scanner s = new Scanner(Game.display.in);
        String choix = s.nextLine();
        Game.display.out.println(choix);
        if (Integer.valueOf(choix)<1 || Integer.valueOf(choix)>4){
            Game.display.out.println("Erreur : Votre réponse n'est pas comprise entre 0 et 4.");
            Game.display.out.println("");
            chooseAction(board);
        }
        else if (Integer.valueOf(choix)==1){
            chooseCard(board);
        } else if (Integer.valueOf(choix)==2){
            chooseUniqueRes(board);
        } else if (Integer.valueOf(choix)==3){
            chooseManyRes(board);
        } else {
            PassAction action4 = new PassAction();
            action4.process(board,this);
        }
        chooseDiscardingTokens(board);
        s.close();
    }
    
    public void chooseDiscardingTokens(Board board){
        while (super.getNbTokens()>10){
            Game.display.out.println("Vous avez trop de jetons ("+super.getNbTokens()+"), saisissez une ressource que vous voulez jeter.");
            Game.display.out.println("");
            Game.display.out.println("Saisir 1 pour jeter: diamants");
            Game.display.out.println("Saisir 2 pour jeter: saphirs");
            Game.display.out.println("Saisir 3 pour jeter: emeraudes");
            Game.display.out.println("Saisir 4 pour jeter: rubis");
            Game.display.out.println("Saisir 5 pour jeter: onyx");
            Game.display.out.println("");
            Scanner s = new Scanner(Game.display.in);
            String choix = s.nextLine();
            Game.display.out.println(choix);
            if (Integer.valueOf(choix)<1 || Integer.valueOf(choix)>5){
                Game.display.out.println("Erreur : Votre choix doit être compris entre 1 et 5.");
                Game.display.out.println("");
                chooseDiscardingTokens(board);
            }
            for (int i=1;i<=5;i++){
                if(Integer.valueOf(choix)==i){
                    if (super.getNbResource(Resource.values()[i-1])>0){
                        Game.display.out.println("Vous avez" + super.getNbResource(Resource.values()[i-1]) + " " +Resource.values()[i-1].toString()+ ", combien voulez vous en jeter?");
                        Game.display.out.println("");
                        choix=s.nextLine();
                        Game.display.out.println(choix);
                        if (Integer.valueOf(choix)<1||Integer.valueOf(choix)>super.getNbResource(Resource.values()[0])){
                            Game.display.out.println("Erreur : Vous avez saisis un nombre négatif ou vous avez saisis un nombre au dessus de votre nombre de ressoures.");
                            Game.display.out.println("");
                            chooseDiscardingTokens(board);
                        } else {
                            Action action = new DiscardTokensAction(Resource.values()[i-1], Integer.valueOf(choix));
                            action.process(board, this);
                        }
                    } else {
                        Game.display.out.println("Erreur : Vous n'avez pas de " + Resource.values()[i-1].toString());
                        Game.display.out.println("");
                        chooseDiscardingTokens(board);
                    }
                }
            }
            s.close();
        }  
    }
    
    public void chooseCard(Board board){
        Game.display.out.println("Choisissez la carte que vous souhaitez acheter : ");
        Game.display.out.println("");
        // Aficher les cartes ?
        Game.display.out.println("Pour selectionner votre carte, ecrivez sous le format suivant : x/y");
        Game.display.out.println("Avec x le numéro de la ligne (entre 1 et 3)");
        Game.display.out.println("Avec y le numéro de la colone (entre 1 et 4)");
        Game.display.out.println("");
        Scanner s = new Scanner(Game.display.in);
        Game.display.out.println("Saisissez la ligne (entre 1 et 3)");
        Game.display.out.println("");
        String choix1 = s.nextLine();
        Game.display.out.println(choix1);
        if (Integer.valueOf(choix1)<1 || Integer.valueOf(choix1)>3){
            Game.display.out.println("Erreur : Votre x doit être compris entre 1 et 3 (le numéro de la ligne). Veuillez ressaisir votre x.");
            Game.display.out.println("");
            chooseCard(board);
        }
        Game.display.out.println("Saisissez la colonne (entre 1 et 4)");
        Game.display.out.println("");
        String choix2 = s.nextLine();
        Game.display.out.println(choix2);
        if (Integer.valueOf(choix2)<1 || Integer.valueOf(choix2)>4){
            Game.display.out.println("Erreur : Votre y doit être compris entre 1 et 4 (le numéro de la colonne). Veuillez ressaisir le tout (x et y).");
            Game.display.out.println("");
            chooseCard(board);
        }
        if (super.canBuyCard(board.getVisibleCards()[Integer.valueOf(choix1)-1][Integer.valueOf(choix2)-1])){
            BuyCardAction action1 = new BuyCardAction(board.getVisibleCards()[Integer.valueOf(choix1)-1][Integer.valueOf(choix2)-1],Integer.valueOf(choix1)-1,Integer.valueOf(choix2)-1);
            action1.process(board , this);
        } else {
            Game.display.out.println("Erreur : Vous ne pouvez pas acheter cette carte, veuillez choisir une autre action");
            Game.display.out.println("");
            chooseAction(board);
        }
        s.close();
    }
    
    public void chooseUniqueRes(Board board){
        Game.display.out.println("Selectionner la ressource pour laquelle vous voulez recevoir 2 jetons : ");
        Game.display.out.println("");
        for (int i=0;i<5;i++){
            Game.display.out.println("Saisir "+(i+1)+" pour recevoir 2 "+ Resource.values()[i]);
        }
        Game.display.out.println("Saisir 6 pour revenir en arrière");
        Game.display.out.println("");
        Scanner s = new Scanner(Game.display.in);
        String choix = s.nextLine();
        Game.display.out.println(choix);
        if (Integer.valueOf(choix)<1 || Integer.valueOf(choix)>6){
            Game.display.out.println("Erreur : Vous devez saisir un chiffre entre 1 et 6.");
            Game.display.out.println("");
            chooseUniqueRes(board);
        }
        if(Integer.valueOf(choix)==6){
            chooseAction(board);
        }else if (board.getNbResource(Resource.values()[Integer.valueOf(choix)-1])>3){
            PickSameTokensAction action2 = new PickSameTokensAction(Resource.values()[Integer.valueOf(choix)-1]);
            action2.process(board,this);
        } else {
            Game.display.out.println("Erreur : Le nombre de ces jetons restants est inférieur à 4, vous ne pouvez pas en prendre 2. Refaites votre choix.");
            Game.display.out.println("");
            chooseUniqueRes(board);
        }
        s.close();
    }
    public void chooseManyRes(Board board){
        ArrayList<Resource> liste_choix= new ArrayList<Resource>();
        int nb_max_choisis = 3;
        
        String choix = "";
        Game.display.out.println("Saisir 1 pour valider la ressource proposée (vous taperez au total 3 fois 1), 0 sinon");
        Game.display.out.println("Saisir 2 pour revenir au choix de base a tout moment");
        Game.display.out.println("");
        for (int i=0; i<5; i++){
            Game.display.out.println("Souhaitez vous 1 jeton "+Resource.values()[i].toString()+" ? ");
            Game.display.out.println("");
            Scanner s = new Scanner(Game.display.in);
            choix = s.nextLine();
            Game.display.out.println(choix);
            if (Integer.valueOf(choix)<0 || Integer.valueOf(choix)>2){
                Game.display.out.println("Erreur : Vous devez saisir un chiffre entre 0 et 2.");
                Game.display.out.println("");
                chooseManyRes(board);
            }
            if(Integer.valueOf(choix)==2){
                chooseAction(board);
                s.close();
                return;
            }else {
                if (Integer.valueOf(choix)==1){
                    nb_max_choisis=nb_max_choisis - 1;
                    liste_choix.add(Resource.values()[i]);
                }
            }
            if (liste_choix.size()==3){
                PickDiffTokensAction action = new PickDiffTokensAction(liste_choix);
                action.process(board,this);
            }
            s.close();
        }
        if (liste_choix.size()<3){
            Game.display.out.println("Erreur : Vous n'avez pas saisis trois ressources au total, vous retournez au choix d'action.");
            Game.display.out.println("");
            chooseAction(board);
        }
        
    }
}
