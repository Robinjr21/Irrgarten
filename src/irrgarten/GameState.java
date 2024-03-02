/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author robinjr
 */
public class GameState {
    private String labyrinthv;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    public GameState(String laberinto, String players, String monsters, int jugActual, boolean win, String log){
        labyrinthv = laberinto;
        this.players = players;
        this.monsters = monsters;
        currentPlayer = jugActual;
        winner = win;
        this.log = log;
    }

    public String getLabyrinth(){
        return labyrinthv;
    }
            
    public String getPlayers(){
        return players;
    }
    
    public String getMonsters(){
        return monsters;
    }
    
    public int getcurrentPlayer(){
        return currentPlayer;
    }
    
    public boolean getWinner(){
        return winner;
    }
    
    public String getLog(){
        return log;
    }
}
