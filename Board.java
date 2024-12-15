import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
//import java.util.HashMap;
//import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.Set;
import java.util.Collections;
import java.util.EmptyStackException;

public class Board implements Displayable {

    // Liste des piles de cartes
    private ArrayList<Stack<DevCard>> stackCards ; 
    // Tableau des cartes visibles
    private DevCard[][] visibleCards ;
    // Ressources disponibles sur le plateau
    private Resources ressources ;

    // Constructeur de la classe Board
    public Board(int nbPlayer) throws FileNotFoundException{
        // Création de trois piles de 3 niveaux 
        Stack<DevCard> stack1 = new Stack<>() ;
        Stack<DevCard> stack2 = new Stack<>() ;
        Stack<DevCard> stack3 = new Stack<>() ;

        // Création d'un type File compatible avec la classe Scanner pour utiliser le bon constructeur (pathname:)
        File file = new File("stats.csv") ;
        // Lecture du fichier CSV grâce à la class Scanner
        Scanner csv = new Scanner(file, "UTF-8") ;
        csv.nextLine() ;
        
        // Traitement des 99 lignes du fichier CSV (correspondant à 99 cartes)
        while(csv.hasNextLine()){          
            // Passage à la nouvelle ligne
            String data = csv.nextLine() ;
            // division de la ligne par les virgules dans un tableau
            String[] dataArray = data.split(",") ;
            if (dataArray[7].equals("NOBLE")){
                // on ne prend pas en compte les nobles
                continue ;
            }
            // Création des ressources dans le type "Resources" pour la carte
            Resources ressourcesCost = new Resources() ;
            ressourcesCost.setNbResource(Resource.DIAMOND, Integer.parseInt(dataArray[1]));
            ressourcesCost.setNbResource(Resource.SAPPHIRE, Integer.parseInt(dataArray[2]));
            ressourcesCost.setNbResource(Resource.EMERALD, Integer.parseInt(dataArray[3]));
            ressourcesCost.setNbResource(Resource.RUBY, Integer.parseInt(dataArray[4]));
            ressourcesCost.setNbResource(Resource.ONYX, Integer.parseInt(dataArray[5]));

            // création du type de ressource que la carte donne
            Resource ressourceTypes ;
            switch(dataArray[7]){
                case "DIAMOND" :
                    // ajout de la ressource dans la pile
                    ressourceTypes = Resource.DIAMOND ;
                    break;
                case "SAPPHIRE" :
                    ressourceTypes = Resource.SAPPHIRE ;
                    break;
                case "EMERALD" :
                    ressourceTypes = Resource.EMERALD ;
                    break;
                case "RUBY" :
                    ressourceTypes = Resource.RUBY ;
                    break; 
                case "ONYX" :
                    ressourceTypes = Resource.ONYX ;
                    break ;
                default :
                    throw new IllegalArgumentException("Type de ressource inconnu: " + dataArray[7]);

            }
            
            // ajout dans les 3 piles selons 3 cas, niveau 1, niveau 2, niveau 3
            DevCard e = new DevCard(Integer.parseInt(dataArray[0]), ressourcesCost, Integer.parseInt(dataArray[6]), ressourceTypes) ;
            switch(dataArray[0]){
                case "1" : 
                    stack1.add(e) ;
                    break ;
                case "2" :
                    stack2.add(e) ; 
                    break ;
                case "3" :
                    stack3.add(e) ;
                    break ;
            }

        }
        csv.close() ;
        
        stackCards = new ArrayList<>() ;
        // Mélange des piles
        Collections.shuffle(stack3) ; Collections.shuffle(stack2) ; Collections.shuffle(stack1) ;
        // ajout des stacks à l'attribut stackCards
        stackCards.add(stack1) ; stackCards.add(stack2) ; stackCards.add(stack3) ;

        // Nombre de gemmes disponibles sur le plateau selon le nombre de joueurs
        int nbGemTokens;
        switch (nbPlayer) {
            case 2:
                nbGemTokens = 4;
                break;
            case 3:
                nbGemTokens = 5;
                break;
            case 4:
                nbGemTokens = 7;
                break;
            default:
                throw new IllegalArgumentException("Nombre de joueurs invalide");
        }
    
        ressources = new Resources() ;
        // initialisation des ressources sur le plateau
        ressources.setNbResource(Resource.DIAMOND, nbGemTokens);
        ressources.setNbResource(Resource.SAPPHIRE, nbGemTokens);
        ressources.setNbResource(Resource.EMERALD, nbGemTokens);
        ressources.setNbResource(Resource.RUBY, nbGemTokens);
        ressources.setNbResource(Resource.ONYX, nbGemTokens);
        
        visibleCards = new DevCard[3][4] ;
        // initialisation des visible cards
        for(int i = 0; i < 3; i++){
            for(int j = 0; j< 4; j++){
                visibleCards[i][j] = drawCard(3-i) ;
            }
        }
        

    }

    public Resources getResources(){
        /* Accesseur
         * Retourne les ressources disponibles sur le plateau
         * @return ressources
         */
        return ressources ;
    }

    public ArrayList<Stack<DevCard>> getStackCards(){
        /* Accesseur
         * Retourne les piles de cartes
         * @return stackCards
         */
        return stackCards ;
    }

    public DevCard[][] getVisibleCards(){
        /* Accesseur
         * Retourne les cartes visibles sur le plateau
         * @return visibleCards
         */
        return visibleCards ;
    }

    public int getNbResource(Resource r){
        /* Accesseur
         * Retourne le nombre de ressources disponibles sur le plateau
         * @param r
         * @return ressources.getNbResource(r)
         */
        return ressources.getNbResource(r) ;
    }

    public void setNbRessources(Resource r, int valeur){
        /* Mutateur
         * Modifie le nombre de ressources disponibles sur le plateau
         * @param r
         * @param valeur
         */
        ressources.setNbResource(r, valeur);
    }

    public void updateNbResource(Resource r,int valeur){
        /* Mutateur
         * Met à jour le nombre de ressources disponibles sur le plateau
         * @param r
         * @param valeur
         */
        ressources.updateNbResource(r, valeur);
    }

    public ArrayList<String> getAvailableResouces(){
        /* Accesseur
         * Retourne les ressources disponibles sur le plateau
         * @return ressources.getList()
         */
        return getAvailableResouces() ;
    }

    public DevCard getCard(int tier, int colomn){
        /* Accesseur
         * Retourne la carte visible à la position donnée
         * @param tier
         * @param colomn
         * @return visibleCards[tier][colomn]
         */
        return visibleCards[tier][colomn] ;
    }

    public void updateCard(DevCard d){
        /* Met à jour la carte visible
         * @param d
         */
        int tier = d.getTier(); 
        for(int i = 0; i< 4; i++){
            if(visibleCards[3-tier][i].equals(d)){
                visibleCards[3-tier][i] = drawCard(tier) ;
                return ;
            }
        }
    }

    public DevCard drawCard(int tier){
        /* Retire une carte de la pile
         * @param tier
         * @return stackCards.get(tier-1).pop()
         */
        try{
            return stackCards.get(tier-1).pop() ;
        }catch(EmptyStackException e){
            return null ;
        }
        
    }

    public boolean canGiveSameTokens(Resource r){
        /* Vérifie si le joueur peut donner 2 gemmes de la même couleur
         * @param r
         * @return ressources.getNbResource(r) > 3
         */
        return ressources.getNbResource(r) > 4 ;
    }

    public boolean canGiveDiffTokens(Resources rs){
        /* Vérifie si le joueur peut donner 3 gemmes de couleurs différentes
         * @param rs
         * @return ressources.getNbResource(Resource.DIAMOND) > 0 && ressources.getNbResource(Resource.SAPPHIRE) > 0 && ressources.getNbResource(Resource.EMERALD) > 0 && ressources.getNbResource(Resource.RUBY) > 0 && ressources.getNbResource(Resource.ONYX) > 0
         */
        for(Integer r : rs.getList()){
            switch(r){
                case 0:
                    if(ressources.getNbResource(Resource.DIAMOND) < 1){
                        return false ;
                    }
                case 1:
                    if(ressources.getNbResource(Resource.SAPPHIRE) < 1){
                        return false ;
                    }
                case 2:
                    if(ressources.getNbResource(Resource.EMERALD) < 1){
                        return false ;
                    }
                case 3:
                    if(ressources.getNbResource(Resource.RUBY) < 1){
                        return false ;
                    }
                case 4:
                    if(ressources.getNbResource(Resource.ONYX) < 1){
                        return false ;
                    }
            }
        }
        return true ;
    }

    /* --- Stringers --- */

    private String[] deckToStringArray(int tier){
        /** EXAMPLE
         * ┌────────┐
         * │        │╲ 
         * │ reste: │ │
         * │   16   │ │
         * │ cartes │ │
         * │ tier 3 │ │
         * │        │ │
         * └────────┘ │
         *  ╲________╲│
         */
        int nbCards = stackCards.get(tier - 1).size(); //- AREMPLEACER par le nombre de cartes présentes
        String[] deckStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510  ",
                            "\u2502        \u2502\u2572 ",
                            "\u2502 reste: \u2502 \u2502",
                            "\u2502   "+String.format("%02d", nbCards)+"   \u2502 \u2502",
                            "\u2502 carte"+(nbCards>1 ? "s" : " ")+" \u2502 \u2502",
                            "\u2502 tier "+tier+" \u2502 \u2502",
                            "\u2502        \u2502 \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518 \u2502",
                            " \u2572________\u2572\u2502"};
        return deckStr;
    }

    private String[] resourcesToStringArray(){
        /** EXAMPLE
         * Resources disponibles : 4♥R 4♣E 4♠S 4♦D 4●O
         */
        String[] resStr = {"Resources disponibles : "};

        for(int res = 0; res <  5; res ++){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            resStr[0] += ressources.getNbResource(Resource.values()[res])+Resource.values()[res].toSymbol()+" ";
        }
        
        resStr[0] += "        ";
        return resStr;
    }

    private String[] boardToStringArray(){
        String[] res = Display.emptyStringArray(0, 0);

        //Deck display
        String[] deckDisplay = Display.emptyStringArray(0, 0);
        for(int i=stackCards.size();i>0;i--){
            deckDisplay = Display.concatStringArray(deckDisplay, deckToStringArray(i), true);
        }

        //Card display
        String[] cardDisplay = Display.emptyStringArray(0, 0);
        for(int i = 0; i< 3; i ++){ //-- parcourir les différents niveaux de carte (i)
            String[] tierCardsDisplay = Display.emptyStringArray(8, 0);
            for(int j = 0; j < 4 ; j++){ //-- parcourir les 4 cartes faces visibles pour un niveau donné (j)
                tierCardsDisplay = Display.concatStringArray(tierCardsDisplay, visibleCards[i][j]!=null ? visibleCards[i][j].toStringArray() : DevCard.noCardStringArray(), false);
            }
            cardDisplay = Display.concatStringArray(cardDisplay, Display.emptyStringArray(1, 40), true);
            cardDisplay = Display.concatStringArray(cardDisplay, tierCardsDisplay, true);
        }
        
        res = Display.concatStringArray(deckDisplay, cardDisplay, false);
        res = Display.concatStringArray(res, Display.emptyStringArray(1, 52), true);
        res = Display.concatStringArray(res, resourcesToStringArray(), true);
        res = Display.concatStringArray(res, Display.emptyStringArray(35, 1, " \u250A"), false);
        res = Display.concatStringArray(res, Display.emptyStringArray(1, 54, "\u2509"), true);

        return res;
    }

    @Override
    public String[] toStringArray() {
        return boardToStringArray();
    }
}
