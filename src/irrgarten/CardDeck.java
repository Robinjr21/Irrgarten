/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author robinjr
 */
public abstract class CardDeck <T>{
    private ArrayList<T> cardDeck;
    
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    protected abstract void addCards();
    
    protected void addCard(T card){
        this.cardDeck.add(card);
    }
    
    public T nextCard(){
        if( cardDeck.isEmpty()){
            this.addCards();
        }
        
        T card = cardDeck.get(0);
        cardDeck.remove(cardDeck.get(0));
        
        Collections.shuffle(cardDeck);
        
        return card;
    }
}
