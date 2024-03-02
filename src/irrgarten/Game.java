/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;

/**
 *
 * @author robinjr
 */
public class Game {
    
    static final private int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    
    private Player currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    
    private static final int nRows = 10, nCols = 10, exitRow = 7, exitCol = 8;
    private static final int MAXMONSTERS = 5;

    
    public Game(int nplayers){
        this.players =  new ArrayList<Player>(nplayers);
        this.monsters = new ArrayList<Monster>(this.MAXMONSTERS);
        this.labyrinth = new Labyrinth(Game.nRows, Game.nCols, Game.exitRow, Game.exitCol);


        for(int i = 0; i < nplayers; i++){
            Player p = new Player((char)(i + '0'), Dice.randomIntelligence(), Dice.randomStrength());
            
            this.players.add(i, p);
        }
        
        for(int i = 0; i < MAXMONSTERS; i++){
            Monster m = new Monster("Monster " + (char)(i + '0'), Dice.randomIntelligence(), Dice.randomStrength());
            this.monsters.add(i, m);
        }
                
        configureLabyrinth();
        
        currentPlayerIndex = Dice.whoStarts(nplayers);
        currentPlayer = this.players.get(currentPlayerIndex);
        
        log = " ";
                    
    }
    
    public boolean finished(){
        boolean sol = labyrinth.haveAWinner();
        if(sol){
            log += "El jugador ha ganado la partida\n";
        }
        return sol;
    }
    
    public GameState getGameState(){
        return new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, this.finished(), log);
    }
    
    public void configureLabyrinth(){
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 0, 0, Game.nCols);
        this.labyrinth.addBlock(Orientation.VERTICAL, 1, 0, Game.nRows);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, Game.nRows-1, 1, Game.nCols);
        this.labyrinth.addBlock(Orientation.VERTICAL, 1, Game.nCols-1, Game.nCols);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 3, 6, 3);
        this.labyrinth.addBlock(Orientation.VERTICAL, 5, 3, 3);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 6, 7, 2);
        
        this.labyrinth.addMonster(1,3, this.monsters.get(0));
        this.labyrinth.addMonster(2,6, this.monsters.get(1));
        this.labyrinth.addMonster(4,2, this.monsters.get(2));
        this.labyrinth.addMonster(6,5, this.monsters.get(3));
        this.labyrinth.addMonster(8,6, this.monsters.get(4));
        
        this.labyrinth.spreadPlayers(players);

    }
    
    public void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    public void logPlayerWon(){
        log += "El jugador ha ganado el combate\n";
    }
    
    public void logMasterWon(){
        log += "El monstruo ha ganado el combate\n";

    }
    
    public void logResurrected(){
        log += "El jugador ha resucitado\n";

    }
    
    public void logPlayerSkipTurn(){
        log += "El jugador ha perdido el turno\n";
       
    }
    
    public void logPlayerNoOrders(){
        log += "El jugador no ha seguido las instrucciones\n";

    }
    
    public void logNoMonsters(){
        log += "El jugador se ha molvido a una celda vacía\n";
    }
    
    public void logRounds(int rounds, int max){
        log += "Se han producido " + rounds + " de " + max + " de rondas de combate\n";
        //log += players.get(currentPlayerIndex).toString() + "\n";
    }
    
    
    
    public GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = this.currentPlayer.attack();
        
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && (rounds < Game.MAX_ROUNDS)){
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = this.currentPlayer.defend(monsterAttack);
            
            if( !lose){
                playerAttack = this.currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        logRounds(rounds, Game.MAX_ROUNDS);
        
        return winner;
    }
    
    public Directions actualDirection(Directions preferredDirection){
        int currentRow = this.currentPlayer.getRow();
        int currentCol = this.currentPlayer.getCol();
        
        ArrayList<Directions>validMoves = this.labyrinth.validMoves(currentRow, currentCol);
        
        Directions output = this.currentPlayer.move(preferredDirection, validMoves);
    
        return output;
    }
    
    public void manageReward(GameCharacter winner){
        if( winner == GameCharacter.PLAYER){
            this.currentPlayer.receiveReward();
            this.logPlayerWon();
        }else{
            this.logMasterWon();
        }
    }
    
    public void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        
        if(resurrect){
            this.currentPlayer.resurrect();
            this.logResurrected();
            
            FuzzyPlayer fuzzyPlayer = new FuzzyPlayer(currentPlayer);
            fuzzyPlayer.setPos(currentPlayer.getRow(), currentPlayer.getCol());
            currentPlayer = fuzzyPlayer;
            players.set(currentPlayerIndex, fuzzyPlayer);
            labyrinth.setFuzzyPlayer(fuzzyPlayer);
        }else{
            this.logPlayerSkipTurn();
        }
    }
    
    public boolean nextStep(Directions preferreDirection){
        log = " ";
        
        boolean dead = this.currentPlayer.dead();
        
        if( !dead){
            Directions direction = this.actualDirection(preferreDirection);
        
            if( direction != preferreDirection){
                this.logPlayerNoOrders();
            }
            
            Monster monster =this.labyrinth.putPlayer(direction, this.currentPlayer);
          
            if( monster == null){
                this.logNoMonsters();
            }else{
                GameCharacter winner = combat(monster);
                this.manageReward(winner);
            }
        }else{
            this.manageResurrection();
        }
        
        boolean endGame = this.finished();
        
        if(!endGame){
            this.nextPlayer();
        }
        
        return endGame;
        
    }

}
