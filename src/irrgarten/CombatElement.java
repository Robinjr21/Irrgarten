/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import javax.xml.transform.dom.DOMSource;

/**
 *
 * @author robinjr
 */
public abstract class CombatElement {
    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect(){
        float sol = 0f;
        
        if(this.uses > 0){
            this.uses--;
            sol = this.effect;
        }
        
        return sol;
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    public String toString(){
        return "[" + this.effect + "," + this.uses + "]";
    }
}
