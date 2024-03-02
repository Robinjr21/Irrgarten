/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import ui.TextUI;
import controller.Controller;
import irrgarten.Game;
import ui.GraphicalUI;
import ui.UI;

/**
 *
 * @author robinjr
 */
public class main {
    
    public static void main(String[] args) {
        
        //UI otro = new TextUI();
        UI vista = new GraphicalUI();
        //GraphicalUI vista = new GraphicalUI();
        Game game = new Game(1);
        Controller controlador = new Controller(game, vista);
        controlador.play();
        
    }
    
}
