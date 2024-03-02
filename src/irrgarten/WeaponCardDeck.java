/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author robinjr
 */
public class WeaponCardDeck extends CardDeck<Weapon>{

    @Override
    protected void addCards() {
        int MAXcards = 10;
        
        for(int i = 0; i < MAXcards; i++){
            Weapon wnew = new Weapon(Dice.weaponPower(), Dice.usesLeft());
            this.addCard(wnew);
        }
           
    }
    
}
