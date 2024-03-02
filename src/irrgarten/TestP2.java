/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author robinjr
 */
public class TestP2 {
    public static void main(String[] args) {
        
        Player jugador = new Player('0', Dice.randomIntelligence(), Dice.randomStrength());
        
        ArrayList<Directions> validMoves = new ArrayList<>();
        
        validMoves.add(Directions.RIGHT);
        validMoves.add(Directions.DOWN);
        
        Directions direccion = jugador.move(Directions.LEFT, validMoves);
        
        Game game = new Game(2);
    
    }
}
