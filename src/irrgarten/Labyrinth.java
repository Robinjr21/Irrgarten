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
public class Labyrinth {
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR ='C';
    private static final char EXIT_CHAR ='E';
    private static final int ROW = 0;
    private static final int COL = 1;
    
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    //Atributos de referencia
    
    private Monster [][]monsters;
    private Player [][]players;
    private char [][]labyrinth;
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.players = new Player[this.nRows][this.nCols];
        this.monsters = new Monster[this.nRows][this.nCols];
        this.labyrinth = new char[this.nRows][this.nCols];
        
        for(int i = 0; i < this.nRows; i++){
            for(int j = 0; j < this.nCols; j++){
                this.players[i][j] = null;
                this.monsters[i][j] = null;
                this.labyrinth[i][j] = Labyrinth.EMPTY_CHAR;
            }
        }
        
        this.labyrinth[this.exitRow][this.exitCol] = Labyrinth.EXIT_CHAR;
            
    }
    
    public boolean haveAWinner(){
        return this.players[this.exitRow][this.exitCol] != null;
    }
    
    public String toString(){
        /*
        String sol = "nRows: " + nRows + 
                "\nnCols: " + nCols + 
                "\nexitRow: " + exitRow + 
                "\nexitCol: " + exitCol + "\nLabyrint: \n"; */
        
        String sol = "";
        for(int i = 0; i < this.nRows; i++){
            for(int j = 0; j < this.nCols; j++){
                sol += this.labyrinth[i][j] + " ";
            }
            sol += "\n";
        }
        return sol;
             
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && emptyPos(row, col)){
            this.monsters[row][col] = monster;
            this.monsters[row][col].setPos(row, col);
            this.labyrinth[row][col] = Labyrinth.MONSTER_CHAR;
        }
    }
    
    public boolean posOK(int row, int col){
        return (row >= 0 && row < this.nRows && col >= 0 && col < this.nCols);
    }
    
    public boolean emptyPos(int row, int col){
        return (labyrinth[row][col] != Labyrinth.COMBAT_CHAR && this.labyrinth[row][col] != Labyrinth.MONSTER_CHAR
                && this.labyrinth[row][col] != Labyrinth.BLOCK_CHAR);
    }
    
    public boolean monsterPos(int row, int col){
        return labyrinth[row][col] == Labyrinth.MONSTER_CHAR;
    }
    
    public boolean exitPos(int row, int col){
        return labyrinth[row][col] == Labyrinth.EMPTY_CHAR;
    }
    
    public boolean combatPos(int row, int col){
        return labyrinth[row][col] == Labyrinth.COMBAT_CHAR;
    }
    
    boolean canStepOn(int row, int col){
        return (posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col) || combatPos(row, col)));
    }
    
    public void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(combatPos(row, col)){
                labyrinth[row][col] = Labyrinth.MONSTER_CHAR;
            }else{
                labyrinth[row][col] = Labyrinth.EMPTY_CHAR;
            }
        }
    }
    
    public int [] dir2pos(int row, int col, Directions directions){
        int []sol = {row, col};
        
        switch (directions) {
            case LEFT:
                sol[Labyrinth.COL] --;
                break;
            case RIGHT:
                sol[Labyrinth.COL] ++;
                break;
                
            case UP:
                sol[Labyrinth.ROW] --;
                break;
            
            case DOWN:
                sol[Labyrinth.ROW] ++;
                break;
        }
        
        return sol;
    }
    
    public int [] randomEmptyPos(){
        int []sol = {0,0};
        boolean encontrado = false;
        
        while(!encontrado){
            int fil = Dice.randomPos(this.nRows);
            int col = Dice.randomPos(this.nCols);
            
            if(labyrinth[fil][col] == Labyrinth.EMPTY_CHAR){
                sol[Labyrinth.ROW] = fil;
                sol[Labyrinth.COL] = col;
                
                encontrado = true;
            }
        }
        
        return sol;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow = 0; 
        int incCol = 0;
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
        }else{
            incCol = 1;
        }
        
        int row = startRow;
        int col = startCol;
        
        while( posOK(row, col) && emptyPos(row, col) && length > 0){
            labyrinth[row][col] = Labyrinth.BLOCK_CHAR;
            length -= 1;
            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> output  = new ArrayList<>();
        if(canStepOn(row+1, col)){
            output.add(Directions.DOWN);
        }
        
        if(canStepOn(row-1, col)){
            output.add(Directions.UP);
        }
        
        if(canStepOn(row, col+1)){
            output.add(Directions.RIGHT);
        }
        
        if(canStepOn(row, col-1)){
            output.add(Directions.LEFT);
        }
        
        return output;
    }
    
    
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        int []newPos = this.dir2pos(oldRow, oldCol, direction);
        int row = newPos[Labyrinth.ROW];
        int col = newPos[Labyrinth.COL];
        
        Monster monster = this.putPlayer2D(oldRow, oldCol, row, col, player);
        
        return monster;
    }
    
    public Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        Monster output = null;
        
        if( canStepOn(row, col)){
            if( posOK(oldRow, oldCol)){
                Player p = this.players[oldRow][oldCol];
                if(p == player){
                    this.updateOldPos(oldRow, oldCol);
                    this.players[oldRow][oldCol] = null;
                }
            }
        }
        
        boolean monsterPos = this.monsterPos(row, col);
        
        if(monsterPos){
            this.labyrinth[row][col] = Labyrinth.COMBAT_CHAR;
            output = this.monsters[row][col];
        }else{
            char number = player.getNumber();
            this.labyrinth[row][col] = number;
        }
        
        this.players[row][col] = player;
        
        player.setPos(row, col);
        
        return output;
    }
    
    public void spreadPlayers(ArrayList<Player>players){
        for( Player p : players){
            int []pos;
            pos = this.randomEmptyPos();
            int oldRow = -1;
            int oldCol = -1;
            int row = pos[Labyrinth.ROW];
            int col = pos[Labyrinth.COL];
            this.putPlayer2D(oldRow, oldCol, row, col, p);
        }
    }
    
    public void setFuzzyPlayer(FuzzyPlayer fuzzyPlayer){
        this.players[fuzzyPlayer.getRow()][fuzzyPlayer.getCol()] = fuzzyPlayer;
    }
    
}

