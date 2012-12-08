/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.HashMap;
/**
 *
 * @author Tyler
 */
public class Gamestate {
    
    HashMap<Object, Object> myState;
    
    public Gamestate()
    {
        myState = new HashMap<Object, Object>();
        this.init();
    }
    
    public void init()
    {
        //code to load the HashMap with all the pieces and their starting locations
    }
    
}
