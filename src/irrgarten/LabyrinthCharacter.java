/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author robinjr
 */
public abstract class LabyrinthCharacter {
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public LabyrinthCharacter(String name,float intelligence, float strength, float health){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        this.row = -1;
        this.col = -1;
    }
    
    public LabyrinthCharacter( LabyrinthCharacter other){
        this(other.name, other.getIntelligence(), other.getStrength(), other.getHealth());
        this.row = other.getRow();
        this.col = other.getCol();
    }
    
    public boolean dead(){
        return (this.health == 0);
    }
    
    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    protected float getIntelligence() {
        return this.intelligence;
    }

    protected float getStrength() {
        return this.strength;
    }

    protected float getHealth() {
        return this.health;
    }
    
    protected void setHealth(float health){
        if(health >= 0){
            this.health = health;
        }
    }

    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public String toString(){
        return this.name +
                "\nIntelligence: " + this.intelligence + 
                " Strength: " + this.strength +
                " Health: " + this.health + 
                " Row: " + this.row +
                " Col: " + this.col;
    }
    
    protected void gotWounded(){
        this.health--;
    }
    
    public abstract float attack();
    
    public abstract boolean defend(float receivedAttack);

}
