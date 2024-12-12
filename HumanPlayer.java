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
        Game.display.out.println("Entrez 4 : Passez votre tour");
        Scanner s = new Scanner(System.in);
        String choix = s.nextLine();
        s.close() ;
        if (Integer.valueOf(choix)<1 || Integer.valueOf(choix)>4){
            throw new InvalideChoiceException("Votre réponse n'est pas comprise entre 0 et 4.");
        }
        if (Integer.valueOf(choix)==1){
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
        return Integer.valueOf(choix) ;
    }
    
    public void chooseDiscardingTokens(Board board){
        while (super.getNbTokens()>10){
            Game.display.out.println("Vous avez trop de jetons ("+super.getNbTokens()+"), saisissez une ressource que vous voulez jeter.");
            Game.display.out.println("Saisir 1 pour jeter: diamants");
            Game.display.out.println("Saisir 2 pour jeter: saphirs");
            Game.display.out.println("Saisir 3 pour jeter: emeraudes");
            Game.display.out.println("Saisir 4 pour jeter: rubis");
            Game.display.out.println("Saisir 5 pour jeter: onyx");
            Scanner s = new Scanner(System.in);
            String choix = s.nextLine();
            s.close() ;
            if (Integer.valueOf(choix)<1 || Integer.valueOf(choix)>5){
                throw new InvalideChoiceException("Votre choix doit être compris entre 1 et 5.");
            }
            if(Integer.valueOf(choix)==1){
                if (super.getNbResource(Resource.values()[0])>0){
                    Game.display.out.println("Vous avez" + super.getNbResource(Resource.values()[0]) + " diamants, combien voulez vous en jeter?");
                    choix=s.nextLine();
                    if (Integer.valueOf(choix)<1||Integer.valueOf(choix)>super.getNbResource(Resource.values()[0])){
                        throw new InvalidResourceException("Vous avez saisis un nombre négatif ou vous avez saisis un nombre au dessus de votre nombre de ressoures.");
                    } else {
                        super.updateNbResource(Resource.values()[0], -Integer.valueOf(choix));
                    }
        
                } else {
                    throw new InsufficientResourceException("Vous n'avez pas de diamants");
                }
            }
            if(Integer.valueOf(choix)==2){
                if (super.getNbResource(Resource.values()[1])>0){
                    Game.display.out.println("Vous avez" + super.getNbResource(Resource.values()[1]) + " saphirs, combien voulez vous en jeter?");
                    choix=s.nextLine();
                    if (Integer.valueOf(choix)<1||Integer.valueOf(choix)>super.getNbResource(Resource.values()[1])){
                        throw new InvalidResourceException("Vous avez saisis un nombre négatif ou vous avez saisis un nombre au dessus de votre nombre de ressoures.");
                    } else {
                        super.updateNbResource(Resource.values()[1], -Integer.valueOf(choix));
                    }
        
                } else {
                    throw new InsufficientResourceException("Vous n'avez pas de saphirs");
        
                }
            }
            if(Integer.valueOf(choix)==3){
                if (super.getNbResource(Resource.values()[2])>0){
                    Game.display.out.println("Vous avez" + super.getNbResource(Resource.values()[2]) + " émeraudes, combien voulez vous en jeter?");
                    choix=s.nextLine();
                    if (Integer.valueOf(choix)<1||Integer.valueOf(choix)>super.getNbResource(Resource.values()[2])){
                        throw new InvalidResourceException("Vous avez saisis un nombre négatif ou vous avez saisis un nombre au dessus de votre nombre de ressoures.");
                    } else {
                        super.updateNbResource(Resource.values()[2], -Integer.valueOf(choix));
                    }
        
                } else {
                    throw new InsufficientResourceException("Vous n'avez pas d'émeraudes");
                }
            }
            if(Integer.valueOf(choix)==4){
                if (super.getNbResource(Resource.values()[3])>0){
                    Game.display.out.println("Vous avez" + super.getNbResource(Resource.values()[3]) + " rubis, combien voulez vous en jeter?");
                    choix=s.nextLine();
                    if (Integer.valueOf(choix)<1||Integer.valueOf(choix)>super.getNbResource(Resource.values()[3])){
                        throw new InvalidResourceException("Vous avez saisis un nombre négatif ou vous avez saisis un nombre au dessus de votre nombre de ressoures.");
                    } else {
                        super.updateNbResource(Resource.values()[3], -Integer.valueOf(choix));
                    }
        
                } else {
                    throw new InsufficientResourceException("Vous n'avez pas de rubis");
                }
            }
            if(Integer.valueOf(choix)==5){
                if (super.getNbResource(Resource.values()[4])>0){
                    Game.display.out.println("Vous avez" + super.getNbResource(Resource.values()[4]) + " onix, combien voulez vous en jeter?");
                    choix=s.nextLine();
                    if (Integer.valueOf(choix)<1||Integer.valueOf(choix)>super.getNbResource(Resource.values()[4])){
                        throw new InvalidResourceException("Vous avez saisis un nombre négatif ou vous avez saisis un nombre au dessus de votre nombre de ressoures.");
                    } else {
                        super.updateNbResource(Resource.values()[4], -Integer.valueOf(choix));
                    }
        
                } else {
                    throw new InsufficientResourceException("Vous n'avez pas d'onix");
                }
            }
        }   
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
        s.close() ;
        if (Integer.valueOf(choix1)<1 || Integer.valueOf(choix1)>3){
            throw new InvalideChoiceException("Votre x doit être compris entre 1 et 3 (le numéro de la ligne). Veuillez ressaisir votre x.");
        }
        System.out.println("Saisissez la colonne (entre 1 et 4)");
        String choix2 = s.nextLine();
        if (Integer.valueOf(choix2)<1 || Integer.valueOf(choix2)>4){
            throw new InvalideChoiceException("Votre y doit être compris entre 1 et 4 (le numéro de la colonne). Veuillez ressaisir le tout (x et y).");

        }
        if (super.canBuyCard(board.getVisibleCards()[Integer.valueOf(choix1)][Integer.valueOf(choix2)])){
            BuyCardAction action1 = new BuyCardAction(board.getVisibleCards()[Integer.valueOf(choix1)][Integer.valueOf(choix2)],Integer.valueOf(choix1),Integer.valueOf(choix2));
            action1.process(board , this);
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

        }
        if(Integer.valueOf(choix)==6){
            chooseAction(board);
        }
        if (board.getNbResource(Resource.values()[Integer.valueOf(choix)])>3){
            
            PickSameTokensAction action2 = new PickSameTokensAction(Resource.values()[Integer.valueOf(choix)]);
            action2.process(board,this);
        } else {
            throw new InsufficientResourceException("Le nombre de ces jetons restants est inférieur à 4");

        }
        
    }
    public void chooseManyRes(Board board){
        ArrayList<Resource> liste_choix= new ArrayList<Resource>();
        int nb_max_choisis = 3;
        Game.display.out.println("Saisir 1 pour valider la ressource proposée (vous taperez au total 3 fois 1), 0 sinon");
        Game.display.out.println("Saisir 2 pour revenir au choix de base a tout moment");
        Game.display.out.println("Souhaitez vous 1 jeton diamant ? ");
        
        Scanner s = new Scanner(System.in);
        String choix = s.nextLine();
        if (Integer.valueOf(choix)<0 || Integer.valueOf(choix)>2){
            throw new InvalideChoiceException("Vous devez saisir un chiffre entre 0 et 2.");

        }
        if(Integer.valueOf(choix)==2){
            chooseAction(board);
        } else {
            if (Integer.valueOf(choix)==1){
                nb_max_choisis=nb_max_choisis - 1;
                liste_choix.add(Resource.values()[0]);
            }
        }
        Game.display.out.println("Souhaitez vous 1 jeton saphir ? ");

        choix = s.nextLine();
        if (Integer.valueOf(choix)<0 || Integer.valueOf(choix)>2){
            throw new InvalideChoiceException("Vous devez saisir un chiffre entre 0 et 2.");

        }
        if(Integer.valueOf(choix)==2){
            chooseAction(board);
        } else {
            if (Integer.valueOf(choix)==1){
                nb_max_choisis=nb_max_choisis - 1;
                liste_choix.add(Resource.values()[1]);
            }
        }
        Game.display.out.println("Souhaitez vous 1 jeton emeraude ? ");

        choix = s.nextLine();
        if (Integer.valueOf(choix)<0 || Integer.valueOf(choix)>2){
            throw new InvalideChoiceException("Vous devez saisir un chiffre entre 0 et 2.");

        }
        if(Integer.valueOf(choix)==2){
            chooseAction(board);
        } else {
            if (Integer.valueOf(choix)==1){
                nb_max_choisis=nb_max_choisis - 1;
                liste_choix.add(Resource.values()[2]);
            }
        }
        Game.display.out.println("Souhaitez vous 1 jeton rubis ? ");
        if (liste_choix.size()==3){
            PickDiffTokensAction action3 = new PickDiffTokensAction(liste_choix);
            action3.process(board,this);
        }
        choix = s.nextLine();
        if (Integer.valueOf(choix)<0 || Integer.valueOf(choix)>2){
            throw new InvalideChoiceException("Vous devez saisir un chiffre entre 0 et 2.");

        }
        if(Integer.valueOf(choix)==2){
            chooseAction(board);
        } else {
            if (Integer.valueOf(choix)==1){
                nb_max_choisis=nb_max_choisis - 1;
                liste_choix.add(Resource.values()[3]);
            }
        }
        Game.display.out.println("Souhaitez vous 1 jeton onix ? ");
        if (liste_choix.size()==3){
            PickDiffTokensAction action3 = new PickDiffTokensAction(liste_choix);
            action3.process(board,this);
        }
        choix = s.nextLine();
        if (Integer.valueOf(choix)<0 || Integer.valueOf(choix)>2){
            throw new InvalideChoiceException("Vous devez saisir un chiffre entre 0 et 2.");
  
        }
        if(Integer.valueOf(choix)==2){
            chooseAction(board);
        } else {
            if (Integer.valueOf(choix)==1){
                nb_max_choisis=nb_max_choisis - 1;
                liste_choix.add(Resource.values()[4]);
            }
        }
        if (liste_choix.size()==3){
            PickDiffTokensAction action3 = new PickDiffTokensAction(liste_choix);
            action3.process(board,this);
        } else {
            throw new InvalidResourceException("Vous n'avez pas saisis trois ressources au total");

        }
    }
}
