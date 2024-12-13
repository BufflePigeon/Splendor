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

    private ArrayList<Stack<DevCard>> stackCards ; 
    private DevCard[][] visibleCards ;

    private Resources ressources ;

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
        
        // Traitement des 99 lignes du fichier CSV
        while(csv.hasNextLine()){          
            // Passage à la nouvelle ligne
            String data = csv.nextLine() ;
            // division de la ligne par les virgules dans un tableau
            String[] dataArray = data.split(",") ;
            if (dataArray[7].equals("NOBLE")){
                continue ;
            }
            // Création des ressources dans le type "Resources"
            Resources ressourcesCost = new Resources() ;
            ressourcesCost.setNbResource(Resource.DIAMOND, Integer.parseInt(dataArray[1]));
            ressourcesCost.setNbResource(Resource.SAPPHIRE, Integer.parseInt(dataArray[2]));
            ressourcesCost.setNbResource(Resource.EMERALD, Integer.parseInt(dataArray[3]));
            ressourcesCost.setNbResource(Resource.RUBY, Integer.parseInt(dataArray[4]));
            ressourcesCost.setNbResource(Resource.ONYX, Integer.parseInt(dataArray[5]));

            Resource ressourceTypes ;
            switch(dataArray[7]){
                case "DIAMOND" :
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
                case "2" :
                    stack2.add(e) ; 
                case "3" :
                    stack3.add(e) ;
            }

            if(nbPlayer == 2){
                
            }

        }
        csv.close() ;
        
        stackCards = new ArrayList<>() ;
        // Mélange des piles
        Collections.shuffle(stack1) ; Collections.shuffle(stack1) ; Collections.shuffle(stack1) ;
        // ajout des stacks à l'attribut stackCards
        stackCards.add(stack3) ; stackCards.add(stack2) ; stackCards.add(stack1) ;

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
        // initialisation des ressources
        ressources.setNbResource(Resource.DIAMOND, nbGemTokens);
        ressources.setNbResource(Resource.SAPPHIRE, nbGemTokens);
        ressources.setNbResource(Resource.EMERALD, nbGemTokens);
        ressources.setNbResource(Resource.RUBY, nbGemTokens);
        ressources.setNbResource(Resource.ONYX, nbGemTokens);
        
        visibleCards = new DevCard[3][4] ;
        // initialisation des visible cards
        visibleCards[0][0] = stack1.pop() ;
        visibleCards[0][1] = stack1.pop() ;
        visibleCards[0][2] = stack1.pop() ;
        visibleCards[0][3] = stack1.pop() ;
        visibleCards[1][0] = stack2.pop() ;
        visibleCards[1][1] = stack2.pop() ;
        visibleCards[1][2] = stack2.pop() ;
        visibleCards[1][3] = stack2.pop() ;
        visibleCards[2][0] = stack3.pop() ;
        visibleCards[2][1] = stack3.pop() ;
        visibleCards[2][2] = stack3.pop() ;
        visibleCards[2][3] = stack3.pop() ;
        

    }

    public Resources getResources(){
        return ressources ;
    }

    public ArrayList<Stack<DevCard>> getStackCards(){
        return stackCards ;
    }

    public DevCard[][] getVisibleCards(){
        return visibleCards ;
    }

    public int getNbResource(Resource r){
        return ressources.getNbResource(r) ;
    }

    public void setNbRessources(Resource r, int valeur){
        ressources.setNbResource(r, valeur);
    }

    public void updateNbResource(Resource r,int valeur){
        ressources.updateNbResource(r, valeur);
    }

    public ArrayList<String> getAvailableResouces(){
        return getAvailableResouces() ;
    }

    public DevCard getCard(int tier, int colomn){
        return visibleCards[tier][colomn] ;
    }

    public void updateCard(DevCard d){
        int tier = d.getTier(); 
        for(int i = 0; i< 3; i++){
            if(visibleCards[tier][i].equals(d)){
                visibleCards[tier][i] = drawCard(tier) ;
                return ;
            }
        }
    }

    public DevCard drawCard(int tier){
        try{
            return stackCards.get(tier).pop() ;
        }catch(EmptyStackException e){
            return null ;
        }
        
    }

    public boolean canGiveSameTokens(Resource r){
        return ressources.getNbResource(r) > 4 ;
    }

    public boolean canGiveDiffTokens(Resources rs){
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
