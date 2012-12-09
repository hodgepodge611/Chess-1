/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Tyler
 */
public class Gamestate {
    
    byte[] state;
    
    public Gamestate()
    {
        state = new byte[64];
        this.init();
    }
    
    public void init()
    {
        //code to load the HashMap with all the pieces and their starting locations
    }
    
          class GameMoves {
    
            public ArrayList<Integer> positions;

  
            public ArrayList<Integer> movePawn(int pos, int player)
            {
                positions = new ArrayList<Integer>();
                
                if(player == -1) //white player. for now used -1
                {                    
                  if(state[pos+8] == 0)     //make sure the next rank is empty
                  {                    
                     positions.add(pos+8);
                     
                     if(pos >= 8 && pos <= 15)  //can pawn move two ranks?
                     {
                         if(state[pos+16] == 0) //is two ranks ahead empty?                            
                         { positions.add(pos+16); }
                     }
                  }
                  else if(state[pos+8] == 7)                                         
                  {
                      if( pos % 8 > 0) //make sure we arent in file a
                      {
                          if(state[pos+7] == 0)
                          {
                              positions.add(pos+7);
                          }
                      }
                      if( pos % 8 < 7) // make sure we arent in file h
                      {
                          if(state[pos+9] == 0)
                          {
                              positions.add(pos+9);
                          }
                      }
                  }
                  if(pos % 8 > 0)        // if not in file A              
                  {                     
                     if(state[pos+7] >= 7) //if occupied by enemy
                        positions.add(pos+7);
                  }
                  if(pos % 8 < 7)
                  {
                      if(state[pos+9] >= 7)
                          positions.add(pos+9);
                  }
                }
                else
                {                                    
                  if(state[pos-8] == 0)     //make sure the next rank is empty
                  {                    
                     positions.add(pos-8);
                     
                     if(pos >= 48 && pos <= 55)  //can pawn move two ranks?
                     {
                         if(state[pos-16] == 0) //is two ranks ahead empty?                            
                         { positions.add(pos-16); }
                     }
                  }
                  else if(state[pos-8] == 7)                                         
                  {
                      if( pos % 8 > 0) //make sure we arent in file a
                      {
                          if(state[pos-7] == 0)
                          {
                              positions.add(pos-7);
                          }
                      }
                      if( pos % 8 < 7) // make sure we arent in file h
                      {
                          if(state[pos-9] == 0)
                          {
                              positions.add(pos-9);
                          }
                      }
                  }
                  if(pos % 8 > 0)        // if not in file A              
                  {                     
                     if(state[pos-7] < 7 && state[pos-7] > 0) //if occupied by enemy
                        positions.add(pos-7);
                  }
                  if(pos % 8 < 7)
                  {
                      if(state[pos-9] < 7 && state[pos-9] > 0)
                          positions.add(pos-9);
                  }
                  
              }
                  
              
              return positions;
        
        
            }
            
            public ArrayList<Integer> moveKnight(int pos, int player)
            {
                positions = new ArrayList<Integer>();
                
                if(player == -1) //white player. for now used -1
                {   
                    if(pos % 8 > 1) // able to move two files left on board
                    {                 
                        if(pos < 56) //able to move down one rank
                        {
                            if(state[pos+6] >= 7 || state[pos+6] == 0) //enemy at target or empty
                                positions.add(pos+6);
                        }
                        if(pos > 8) //able to move  up one rank
                        {
                            if(state[pos-10] >=7 || state[pos-10] == 0)
                                positions.add(pos-10);
                        }
                    }
                    if(pos % 8 < 6) //able to move two files right on board
                    {
                        if(pos < 56) //able to move a rank down
                        {
                            if(state[pos+10] >= 7 || state[pos+10] == 0) //enemy at target or empty
                                positions.add(pos+10);
                        }
                        if(pos > 8) //able to move a rank up
                        {
                            if(state[pos-6] >= 7 || state[pos-6] == 0)
                                positions.add(pos-6);
                        }
                    }
                    if(pos < 47) // able to move two ranks down
                    {
                        if(pos % 8 > 0) // able to move one file left
                        {
                            if(state[pos+15] >= 7 || state[pos+15] == 0)
                                positions.add(pos+15);
                        }
                        if(pos%8 < 7) //able to move one file right
                        {
                            if(state[pos+17] >= 7 || state[pos+17] == 0)
                                positions.add(pos+17);
                        }
                    }
                    if(pos > 16) //able to move two ranks up
                    {
                        if(pos % 8 > 0) //able to move one file left
                        {
                            if(state[pos-17] >= 7 || state[pos-17] == 0)
                                positions.add(pos-17);
                        }
                        if(pos % 8 < 7) // able to move one file right
                        {
                            if(state[pos-15] >= 7 || state[pos-15] == 0)
                                positions.add(pos-15);
                        }
                    }
                }
                else
                {
                    if(pos % 8 > 1) // able to move two files left on board
                    {                 
                        if(pos < 56) //able to move down one rank
                        {
                            if(state[pos+6] < 7) //enemy at target or empty
                                positions.add(pos+6);
                        }
                        if(pos > 8) //able to move  up one rank
                        {
                            if(state[pos-10] < 7)
                                positions.add(pos-10);
                        }
                    }
                    if(pos % 8 < 6) //able to move two files right on board
                    {
                        if(pos < 56) //able to move a rank down
                        {
                            if(state[pos+10] < 7) //enemy at target or empty
                                positions.add(pos+10);
                        }
                        if(pos > 8) //able to move a rank up
                        {
                            if(state[pos-6] < 7)
                                positions.add(pos-6);
                        }
                    }
                    if(pos < 47) // able to move two ranks down
                    {
                        if(pos % 8 > 0) // able to move one file left
                        {
                            if(state[pos+15] < 7)
                                positions.add(pos+15);
                        }
                        if(pos%8 < 7) //able to move one file right
                        {
                            if(state[pos+17] < 7)
                                positions.add(pos+17);
                        }
                    }
                    if(pos > 16) //able to move two ranks up
                    {
                        if(pos % 8 > 0) //able to move one file left
                        {
                            if(state[pos-17] < 7)
                                positions.add(pos-17);
                        }
                        if(pos % 8 < 7) // able to move one file right
                        {
                            if(state[pos-15] < 7)
                                positions.add(pos-15);
                        }
                    }                    
                } 
                return positions;
            }
            
            public ArrayList<Integer> moveBishiop(int pos, int player)
            {
                positions = new ArrayList<Integer>();
                
                if(player == -1) //white player. for now used -1
                {   
            
      }
          
}

    


  