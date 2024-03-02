/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author robinjr
 */
public class Monster extends LabyrinthCharacter{
    private static final int INITIAL_HEALTH = 5;
    
    public Monster( String name, float intelligence, float strength){
        super(name, intelligence, strength, Monster.INITIAL_HEALTH);
    }
    
    @Override
    public float attack(){
        return Dice.intensity(super.getStrength());
    }

    
    @Override
    public boolean defend(float receiveAttack){
        boolean isDead = this.dead();
        
        if( !isDead){
            float defensiveEnergy = Dice.intensity(super.getIntelligence());
            if( defensiveEnergy < receiveAttack){
                super.gotWounded();
                isDead = this.dead();
            }
        }
        
        return isDead;
    } 
}
