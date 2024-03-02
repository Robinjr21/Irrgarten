/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ui;

import irrgarten.Directions;
import irrgarten.GameState;

/**
 *
 * @author robinjr
 */
public interface UI {
    Directions nextMove();
    void showGame(GameState gameState);
}
