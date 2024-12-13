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
        resources=new Resources();
        purchasedCards= new ArrayList<DevCard>();
        
    }
    
    public String getName(){
        return name;
    }
    
    public int getPoints(){
        return points;
    }
    
    public int getNbTokens(){
        int somme=0;
        for(Resource res: Resource.values()){
            somme=somme+resources.getNbResource(res);
        }
        return somme;
    }
    
    public int getNbPurshasedCards(){
        return purchasedCards.size();
    }
    
    public int getNbResource(Resource ressource){
        return resources.getNbResource(ressource);
    }
    
    public ArrayList<Resource> getAvailableResources(){
        return resources.getAvailableResources();
    }
    
    public int getResFromCards(Resource ressource){
        int somme = 0;
        for (DevCard card : purchasedCards){
            if (card.getResourceType()==ressource){
                somme = somme + 1;
            }
        }
        return somme;
    }
    
    public void updateNbResource(Resource ressource, int v){
        resources.updateNbResource(ressource,v);
    }
    
    public void updatePoints(int v){
        points=points+v;
    }
    
    public void addPurchasedCard(DevCard carte){
        purchasedCards.add(carte);
    }
    
    public abstract void chooseAction(Board board);
    public abstract void chooseDiscardingTokens(Board board); //Type de retour?

    public boolean canBuyCard(DevCard carte){
        Resources cost=carte.getCost();
        for (int i=0; i<=cost.getList().size(); i++){
            if (cost.getList().get(i)>resources.getList().get(i)){
                return false;
            }
        }
        return true;
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
        
        if(points>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }else{
            pointStr = "\u24EA";
        }

        
        strPlayer[0] = "Player "+(id)+": "+name;
        strPlayer[1] = pointStr + "pts";
        strPlayer[2] = "";
        for(Resource res: Resource.values()){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            strPlayer[3+(Resource.values().length-1-res.ordinal())] = res.toSymbol() + " ("+resources.getNbResource(res)+") ["+getResFromCards(res)+"]";
        }
        
        return strPlayer;
    }
}
