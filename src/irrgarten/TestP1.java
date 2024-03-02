/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package irrgarten;

/**
 *
 * @author robinjr
 */
public class TestP1 {


    public static void main(String[] args) {
        /*
        Directions direccion = Directions.LEFT;
        
        System.out.println("La direccion es " + direccion);
        
        Weapon arma = new Weapon(10.5f, 5);
        float ataque = arma.attack();
        boolean arm = arma.discard();
        System.out.println("Arma: " + arma.toString());
        
        Shield escudo = new Shield(10, 5);
        float protege = escudo.protect();
        boolean esc = escudo.discard();
        System.out.println("Escudo: "+ escudo.toString());
        
        
        int i = 0;
        
        while( i < 100){
            
            System.out.println(Dice.whoStarts(6));
            i++;
        }
        
        i = 0;
        
        while( i < 100){
            
            System.out.println(Dice.randomPos(6));
            i++;
        }
        
        
        i = 0;
        while( i < 100){
            System.out.println("Random pos: " + Dice.randomPos(6));
            System.out.println("Who start: " + Dice.whoStarts(6));
            System.out.println("Intelligence: " + Dice.randomIntelligence());
            System.out.println("Random strength: " + Dice.randomStrength());
            System.out.println("Resurrect player: " + Dice.resurrectPlayer());
            System.out.println("Weapons reward: " + Dice.weaponsReward());
            System.out.println("Shield reward: " + Dice.shieldsReward());
            System.out.println("Health reward " + Dice.healthReward());
            System.out.println("Weapon power " + Dice.weaponPower());
            System.out.println("Shield power: " + Dice.shieldPower());
            
            i++;
        }
        
        System.out.println("Booleanos");
        i = 0;
        int conT = 0, conF= 0;
        while( i < 100){
            boolean bool = Dice.discardElement(4);
            System.out.println(bool);
            i++;
            if(bool == true) conT++;
            else conF++;
        }
        
        System.out.println("Trues: " + conT);
        System.out.println("Falses "+ conF);
        
        GameState juego = new GameState("laberinto", "players", "monsters", 4, true, "log");
        
        
        System.out.println("Laberinto: " + juego.getLabyrinth() +
                "\nJugadores: " + juego.getPlayers() + 
                "\nMonstruos: " + juego.getMonsters() + 
                "\nJugador actual: " + juego.getcurrentPlayer() +
                "\nGanador: " + juego.getWinner() + 
                "\nLog: " + juego.getLog());
        
        //Prueba player
        Player p1 = new Player('0', Dice.randomIntelligence(), Dice.randomStrength());
        System.out.println(p1.toString());
        float suma = p1.sumWeapons();*/
        
        //Prueba Labyrinth
        
        Labyrinth lab = new Labyrinth(5, 7, 3, 4);
        
        Game game = new Game(4);
        
        System.out.println("Game state Labyrinth: " + game.getGameState().getLabyrinth().toString());
        System.out.println("Game state Players: " + game.getGameState().getPlayers().toString());
        System.out.println("Game state MOnsters: " + game.getGameState().getMonsters().toString());
             
    }
    
    
    
}
