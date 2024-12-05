import java.util.ArrayList;

public abstract class Player implements Displayable {
    private int id;
    private String name;
    private int points;
    private ArrayList<DevCard> purchasedCards;
    private Resources resources;
    
    public Player(int id, String name){
        this.id=id;
        this.name=name;
        points=0;
        purchasedCards= new ArrayList<DevCard>();
        
    }
    
    public int getNbTokens(){ // pas sur sur
        return resources.getNbResource("DIAMANT")+resources.getNbResource("SAPHIRE")+
            resources.getNbResource("EMERAUDES")+resources.getNbResource("RUBY")+
            resources.getNbResource("ONYX");
    }
    
    public int getNbPurshasedCards(){
        return purchasedCards.size();
    }
    
    public int getNbResource(String ressource){
        return resources.getNbResource(ressource);
    }
    
    public Resources getAvailableResoucres(){ //pas sur sur
        return resources;
    }
    
    public int getResFromCards(String ressource){
        int somme = 0;
        for (DevCard card : purchasedCards){
            somme = somme + 0; //a revoir
        }
        return somme;
    }
    
    public void updateNbResource(String ressource, int v){
        if (v+resources.getNbResource(ressource)<0){
            System.out.println("Update impossible"); // faire une gestion d'erreur
        } else {
            resources.setNbResources(ressource,v+resources.getNbResource(ressource));
        }
    }
    
    public void updatePoints(int v){
        points=points+v;
    }
    
    public void addPurchasedCard(DevCard carte){
        purchasedCards.add(carte);
    }
    
    public boolean canBuyCard(DevCard carte){
        for (
    }
    /* --- Stringers --- */
   
     
    public String[] toStringArray(){
        /** EXAMPLE. The number of resource tokens is shown in brackets (), and the number of cards purchased from that resource in square brackets [].
         * Player 1: Camille
         * ⓪pts
         * 
         * ♥R (0) [0]
         * ●O (0) [0]
         * ♣E (0) [0]
         * ♠S (0) [0]
         * ♦D (0) [0]
         */
        String pointStr = " ";
        String[] strPlayer = new String[8];
         /*
            * A decommenter une fois la classe implémentée
        if(points>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }else{
            pointStr = "\u24EA";
        }

        
        strPlayer[0] = "Player "+(id+1)+": "+name;
        strPlayer[1] = pointStr + "pts";
        strPlayer[2] = "";
        for(ACOMPLETER){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            strPlayer[3+(Resource.values().length-1-res.ordinal())] = res.toSymbol() + " ("+resources.getNbResource(res)+") ["+getResFromCards(res)+"]";
        }
        */
        return strPlayer;
    }
}
