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
public class Player extends LabyrinthCharacter{
    static final private int MAX_WEAPONS = 2;
    static final private int MAX_SHIELDS = 3;
    static final private int INITIAL_HEALTH = 10;
    static final private int HITS2LOSE = 3;
    static final private String PREFIJO = "Player #";
    
    private char number;
    private int consecutiveHits = 0;
    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    WeaponCardDeck weaponCardDeck;
    ShieldCardDeck shieldCardDeck;

    public char getNumber() {
        return number;
    }

    public WeaponCardDeck getWeaponCardDeck() {
        return weaponCardDeck;
    }

    public ShieldCardDeck getShieldCardDeck() {
        return shieldCardDeck;
    }
    
    
    
    public Player( char number, float intelligence, float strength){
        super(PREFIJO + number, intelligence, strength, Player.INITIAL_HEALTH);
        this.number = number;
        
        this.weapons = new ArrayList<Weapon>(MAX_WEAPONS);
        this.shields = new ArrayList<>(MAX_SHIELDS);
    
        weaponCardDeck = new WeaponCardDeck();
        shieldCardDeck = new ShieldCardDeck();
        
    }
    
    public Player(Player other){
        super(PREFIJO + other.getNumber(), other.getIntelligence(), other.getStrength(), other.getHealth());

        this.number = other.getNumber();

        // Copiar la lista de armas
        this.weapons = new ArrayList<>(other.weapons.size());
        for (Weapon weapon : other.weapons) {
            this.weapons.add(weapon);
        }

        // Copiar la lista de escudos
        this.shields = new ArrayList<>(other.shields.size());
        for (Shield shield : other.shields) {
            this.shields.add(shield); 
        }
        
        weaponCardDeck = other.getWeaponCardDeck();
        shieldCardDeck = other.getShieldCardDeck();
    }
    
    public void resurrect(){
        this.setHealth(Player.INITIAL_HEALTH);
        this.resetHits();
        weapons.clear();
        shields.clear();
    }
    
    @Override
    public float attack(){
        return this.getStrength() + this.sumWeapons();
    }
    
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    protected float defensiveEnergy(){
        return this.getIntelligence() + sumShields();
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }

    protected float sumWeapons(){
        float total = 0.0f;
        
        for(Weapon w : this.weapons){
            total += w.attack();
        }
        
        return total;
    }
    
    protected float sumShields(){
        float total = 0.0f;
        
        for(Shield s:this.shields){
            total += s.protect();
        }
        
        return total;
    }

    private boolean manageHit(float receivedAttack) {
        float defense = this.defensiveEnergy();
        
        if( defense < receivedAttack){
            super.gotWounded();
            this.incConsecutiveHits();
        }else{
            this.resetHits();
        }
        
        boolean lose = false;
        if( this.consecutiveHits == Player.HITS2LOSE || this.dead()){
            this.resetHits();
            lose = true;
        }
        
        return lose;
    }
    
    private void receiveWeapon(Weapon w){
        
        for(int i = this.weapons.size()-1; i > -1; i--){
            Weapon wi = this.weapons.get(i);
            boolean discard = wi.discard();
            if(discard){
                this.weapons.remove(wi);
            }
        }
        
        int size = this.weapons.size();
        
        if( size < Player.MAX_WEAPONS ){
            this.weapons.add(w);
        }
    }
    
    private void receiveShields( Shield s){
        
        for(int i = this.shields.size()-1; i > -1; i--){
            Shield si = this.shields.get(i);
            boolean discard = si.discard();
            if(discard){
                this.shields.remove(si);
            }
        }
        
        int size = shields.size();
        
        if( size < Player.MAX_SHIELDS ){
            this.shields.add(s);
        }
    }
    
    public void receiveReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for(int i= 0; i < wReward; i++){
            Weapon wnew = weaponCardDeck.nextCard();
            this.receiveWeapon(wnew);
        }
        
        for(int i = 0; i < sReward; i++){
            Shield snew = shieldCardDeck.nextCard();
            this.receiveShields(snew);
        }
        
        int extraHealth = Dice.healthReward();
        super.setHealth(super.getHealth() +extraHealth);
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        
        if(size > 0 && !contained){
            Directions firstElement = validMoves.get(0);
            return firstElement;
        }else{
            return direction;
        }
    }
    
    @Override
    public boolean defend(float receiveAttack){
        return this.manageHit(receiveAttack);
    }
    
    @Override
    public String toString(){
        
        return super.toString();
    }
}
