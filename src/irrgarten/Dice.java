/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author robinjr
 */
public class Dice {
    static final private int MAX_USES = 5;
    static final private float MAX_INTELLIGENCE = 10.0f;
    static final private float MAX_STRENGTH = 10.0f;
    static final private float RESURRECT_PROB = 0.3f;
    static final private int WEAPONS_REWARD = 2;
    static final private int SHIELDS_REWARD = 3;
    static final private int HEALTH_REWARD = 5;
    static final private int MAX_ATTACK = 3; 
    static final private int MAX_SHIELD = 2; 
    static final private Random generator = new Random(); // Para asegurarte que nunca cambia
    static final private int UNIT = 1; 
    
    public static int randomPos(int max){
        return generator.nextInt(max);
    }
    
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    public static float randomIntelligence(){
        
        return generator.nextFloat() * Dice.MAX_INTELLIGENCE;  
    }
    
    public static float randomStrength(){
        return  generator.nextFloat() * MAX_STRENGTH;  
    }
    
    public static boolean resurrectPlayer(){
        if(generator.nextFloat() < RESURRECT_PROB){
            return false;
        }
        else return true;
    }
    
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD + UNIT);
    }
    
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD + UNIT);
    }
    
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD + UNIT);
    }
    
    public static float weaponPower(){
        return generator.nextFloat()*MAX_ATTACK;
    }
    
    public static float shieldPower(){
        return generator.nextFloat()*MAX_SHIELD;
    }
    
    public static int usesLeft(){
        return generator.nextInt(Dice.MAX_USES + UNIT);
    }
    
    public static float intensity(float competence){
        return generator.nextFloat()*competence;
    }
    
    public static boolean discardElement( int usesLeft){
        float prob = UNIT - (float)usesLeft/MAX_USES;
        
        if( prob > generator.nextFloat()){
            return true;
        }else return false;
    }
    
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
        float prob = UNIT - (float)intelligence/MAX_INTELLIGENCE;
        
        if(prob < intelligence){
            return preference;
        }else{
            int index = generator.nextInt(validMoves.size());
            return validMoves.get(index);
        }
    }
}
