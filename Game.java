/*
* @author  Corentin Dufourg
* @version     1.1
* @since       1.0
*/

import java.io.FileNotFoundException;
import java.util.*;

public class Game {
    /* L'affichage et la lecture d'entrée avec l'interface de jeu se fera entièrement via l'attribut display de la classe Game.
    * Celui-ci est rendu visible à toutes les autres classes par souci de simplicité.
    * L'intéraction avec la classe Display est très similaire à celle que vous auriez avec la classe System :
    *    - affichage de l'état du jeu (méthodes fournies): Game.display.outBoard.println("Nombre de joueurs: 2");
    *    - affichage de messages à l'utilisateur: Game.display.out.println("Bienvenue sur Splendor ! Quel est ton nom?");
    *    - demande d'entrée utilisateur: new Scanner(Game.display.in);
    */
    private static final int ROWS_BOARD=36, ROWS_CONSOLE=8, COLS=82;
    public static final  Display display = new Display(ROWS_BOARD, ROWS_CONSOLE, COLS);

    private Board board;
    private List<Player> players;

    public static void main(String[] args) {
        //-- à modifier pour permettre plusieurs scénarios de jeu
        display.outBoard.println("Bienvenue sur Splendor !");
        Game game = new Game(2);
        game.play();
        display.close();
    }

    public Game(int nbOfPlayers)throws IllegalArgumentException{
        if (nbOfPlayers<=2 || nbOfPlayers>4){
            throw new IllegalArgumentException("Le nombre de joueur doit être entre 2 et 4 joueurs inclus");
        }
        players = new ArrayList<>();
        players.add(new HumanPlayer(1, "Joueur 1"));
        for (int i = 2; i <= nbOfPlayers; i++) {
            players.add(new DumbRobotPlayer(i, "Robot " + i));
        }
        board = new Board(nbOfPlayers);
        
    }

    public int getNbPlayers(){
        return players.size();
    }
    
    public Board getBoard(){
        return board ;
    }

    private void display(int currentPlayer){
        String[] boardDisplay = board.toStringArray();
        String[] playerDisplay = Display.emptyStringArray(0, 0);
        for(int i=0;i<players.size();i++){
            String[] pArr = players.get(i).toStringArray();
            if(i==currentPlayer){
                pArr[0] = "\u27A4 " + pArr[0];
            }
            playerDisplay = Display.concatStringArray(playerDisplay, pArr, true);
            playerDisplay = Display.concatStringArray(playerDisplay, Display.emptyStringArray(1, COLS-54, "\u2509"), true);
        }
        String[] mainDisplay = Display.concatStringArray(boardDisplay, playerDisplay, false);

        display.outBoard.clean();
        display.outBoard.println(String.join("\n", mainDisplay));
    }

    public void play(){
        while (!isGameOver()){
            for (Player player : players){
                move(player);
                discard(player);
            }
        }
            
    }
    public void discard(Player player){
        
    }
    private void move(Player player){
        player.chooseAction(board);
    }

    private void discardToken(Player player){
        
        while (player.getNbTokens()>10){
            player.chooseDiscardingTokens(getBoard());
        }
    }

    public boolean isGameOver(){
        //Renvoie true si c'est la fin du jeu
        boolean res = false;
        for (Player player : players){
            if (player.getPoints()>=15){
                res = true;
            }
        }
        return res; 
    }

    private void gameOver(){ 
        System.out.println("Partie terminée !");
        int maxPoints = 0;
        List<Player> winners = new ArrayList<>();
        
        for (Player player : players) {
            if (player.getPoints() > maxPoints) {
                maxPoints = player.getPoints();
                winners.clear();
                winners.add(player);
            } else if (player.getPoints() == maxPoints) {
                winners.add(player);
            }
        }
        
        if (winners.size() == 1) {
            System.out.println("Félicitations au gagnant : " + winners.get(0).getName() + " avec " + maxPoints + " points !");
        } else {
            System.out.println("Égalité entre les joueurs suivants avec " + maxPoints + " points :");
            for (Player winner : winners) {
                System.out.println("- " + winner.getName());
            }
        }

    }


}
