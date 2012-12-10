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
    
   public byte[] state;
    
   public Gamestate()
    {
        state = new byte[]{
            4, 2, 3, 5, 6, 3, 2, 4,
            1, 1, 1, 1, 1, 1, 1, 1, 
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 
            7, 7, 7, 7, 7, 7, 7, 7, 
            10, 8, 9, 11, 12, 9, 8, 10
        };
    }
    
          class GameMoves {
    
            public ArrayList<Integer> positions;

  
            public ArrayList<Integer> movePawn(int pos, int player)
            {
                positions = new ArrayList<Integer>();
                
                if(player == -1) //white player. for now used -1
                {
                  if(pos+8 < 63)
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
                }
                else
                {
                  if(pos-8 > 0)
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
            
            public ArrayList<Integer> moveBishop(int pos, int player)
            {
                positions = new ArrayList<Integer>();
                int i = pos;
                
                if(player == -1) //white player. for now used -1
                {
                    if(i%8 != 0)
                    {
                        while(i%8>0 && i-9 > 0) //can go left and up
                        {
                            if(state[i-9] == 0)
                            {
                                positions.add(i-9);
                                i -= 9;
                            }
                            else if(state[i-9] >= 7 ) //check one up and one left
                            {
                                positions.add(i-9);
                                break;
                            }
                            else
                            {   break;  }
                        }
                        i = pos;
                        
                        while(i % 8 > 0 && i+7 < 63) //can go left and down
                        {
                            if(state[i+7] == 0) // if empty
                            {
                                positions.add(i+7);
                                i += 7;
                            }
                            else if(state[i+7] >= 7) //if enemy
                            {
                                positions.add(i+7);
                                break;
                            }
                            else //if ally
                            {   break;  }
                        }                        
                        i = pos;
                      }
                      if(i % 8 != 7)
                      {
                          while(i % 8 < 7 && i-7 > 0) //can go right and up
                          {
                              if(state[i-7] == 0)   //if empty
                              {
                                  positions.add(i-7);
                                  i -=7;
                              }
                              else if(state[i-7] >= 7)  //if enemy
                              {
                                  positions.add(i-7);
                                  break;
                              }
                              else  // if ally
                              { break;  }
                          }
                          i = pos;
                          while(i % 8 < 7 && i+9 < 63) //can go right and down
                          {
                              if(state[i+9] == 0) //if empty
                              {
                                  positions.add(i+9);
                                  i += 9;
                              }
                              else if(state[i+9] >= 7) //if enemy
                              {
                                  positions.add(i+9);
                                  break;
                              }
                              else
                              { break;  }
                          }
                      }            
                   }
                else    //black player
                {
                    if(i%8 != 0)
                    {
                        while(i%8>0 && i-9 > 0) //can go left and up
                        {
                            if(state[i-9] == 0)
                            {
                                positions.add(i-9);
                                i -= 9;
                            }
                            else if(state[i-9] < 7 ) //check one up and one left
                            {
                                positions.add(i-9);
                                break;
                            }
                            else
                            {   break;  }
                        }
                        i = pos;
                        
                        while(i % 8 > 0 && i+7 < 63) //can go left and down
                        {
                            if(state[i+7] == 0) // if empty
                            {
                                positions.add(i+7);
                                i += 7;
                            }
                            else if(state[i+7] < 7) //if enemy
                            {
                                positions.add(i+7);
                                break;
                            }
                            else //if ally
                            {   break;  }
                        }                        
                        i = pos;
                      }
                      if(i % 8 != 7)
                      {
                          while(i % 8 < 7 && i-7 > 0) //can go right and up
                          {
                              if(state[i-7] == 0)   //if empty
                              {
                                  positions.add(i-7);
                                  i -=7;
                              }
                              else if(state[i-7] < 7)  //if enemy
                              {
                                  positions.add(i-7);
                                  break;
                              }
                              else  // if ally
                              { break;  }
                          }
                          i = pos;
                          while(i % 8 < 7 && i+9 < 63) //can go right and down
                          {
                              if(state[i+9] == 0) //if empty
                              {
                                  positions.add(i+9);
                                  i += 9;
                              }
                              else if(state[i+9] < 7) //if enemy
                              {
                                  positions.add(i+9);
                                  break;
                              }
                              else
                              { break;  }
                          }
                      } 
                 }
                return positions;
             }
            
            public ArrayList<Integer> moveRook(int pos, int player)
            {
                positions = new ArrayList<Integer>();
                int i = pos;
                
                if(player == -1) //white player. for now used -1
                {
                    if(i%8 != 0)
                    {
                        while(i%8>0) //can go left 
                        {
                            if(state[i-1] == 0)     //if empty
                            {
                                positions.add(i-1);
                                i--;
                            }
                            else if(state[i-1] >= 7 ) //if enemy
                            {
                                positions.add(i-1);
                                break;
                            }
                            else    // if ally
                            {   break;  }
                        }
                        i = pos;
                    }
                    if(i%8 != 7)
                    {
                        while(i % 8 < 7 ) //can go right
                        {
                            if(state[i+1] == 0) // if empty
                            {
                                positions.add(i+1);
                                i++;
                            }
                            else if(state[i+1] >= 7) //if enemy
                            {
                                positions.add(i+1);
                                break;
                            }
                            else //if ally
                            {   break;  }
                        }                        
                        i = pos;
                      }
                      if(i > 7)
                      {
                          while(i-8 > 0) //can go up
                          {
                              if(state[i-8] == 0)   //if empty
                              {
                                  positions.add(i-8);
                                  i -=8;
                              }
                              else if(state[i-8] >= 7)  //if enemy
                              {
                                  positions.add(i-8);
                                  break;
                              }
                              else  // if ally
                              { break;  }
                          }
                          i = pos;
                      }
                      if(i<56)
                      {
                          while(i+8 < 63) //can go down
                          {
                              if(state[i+8] == 0) //if empty
                              {
                                  positions.add(i+8);
                                  i += 8;
                              }
                              else if(state[i+8] >= 7) //if enemy
                              {
                                  positions.add(i+8);
                                  break;
                              }
                              else
                              { break;  }
                          }
                      }            
                } 
                else    //black player
                {
                   if(i%8 != 0)
                    {
                        while(i%8>0) //can go left 
                        {
                            if(state[i-1] == 0)     //if empty
                            {
                                positions.add(i-1);
                                i--;
                            }
                            else if(state[i-1] < 7) //if enemy
                            {
                                positions.add(i-1);
                                break;
                            }
                            else    // if ally
                            {   break;  }
                        }
                        i = pos;
                    }
                    if(i%8 != 7)
                    {
                        while(i % 8 < 7 ) //can go right
                        {
                            if(state[i+1] == 0) // if empty
                            {
                                positions.add(i+1);
                                i++;
                            }
                            else if(state[i+1] < 7) //if enemy
                            {
                                positions.add(i+1);
                                break;
                            }
                            else //if ally
                            {   break;  }
                        }                        
                        i = pos;
                      }
                      if(i > 7)
                      {
                          while(i-8 > 0) //can go up
                          {
                              if(state[i-8] == 0)   //if empty
                              {
                                  positions.add(i-8);
                                  i -=8;
                              }
                              else if(state[i-8] < 7)  //if enemy
                              {
                                  positions.add(i-8);
                                  break;
                              }
                              else  // if ally
                              { break;  }
                          }
                          i = pos;
                      }
                      if(i<56)
                      {
                          while(i+8 < 63) //can go down and down
                          {
                              if(state[i+8] == 0) //if empty
                              {
                                  positions.add(i+8);
                                  i += 8;
                              }
                              else if(state[i+8] < 7) //if enemy
                              {
                                  positions.add(i+8);
                                  break;
                              }
                              else
                              { break;  }
                          }
                      } 
                }
                return positions;
            }
            
            public ArrayList<Integer> moveQueen(int pos, int player)
            {
                positions = new ArrayList<Integer>();
                int i = pos;
                
                if(player == -1) //white player. for now used -1
                {
                    if(i%8 != 0)
                    {
                        while(i%8>0) //can go left 
                        {
                            if(state[i-1] == 0)     //if empty
                            {
                                positions.add(i-1);
                                i--;
                            }
                            else if(state[i-1] >= 7 ) //if enemy
                            {
                                positions.add(i-1);
                                break;
                            }
                            else    // if ally
                            {   break;  }
                        }
                        i = pos;
                    
                        while(i%8>0 && i-9 > 0) //can go left and up
                        {
                            if(state[i-9] == 0)
                            {
                                positions.add(i-9);
                                i -= 9;
                            }
                            else if(state[i-9] >= 7 ) //check one up and one left
                            {
                                positions.add(i-9);
                                break;
                            }
                            else
                            {   break;  }
                        }
                        i = pos;
                        
                        while(i % 8 > 0 && i+7 < 63) //can go left and down
                        {
                            if(state[i+7] == 0) // if empty
                            {
                                positions.add(i+7);
                                i += 7;
                            }
                            else if(state[i+7] >= 7) //if enemy
                            {
                                positions.add(i+7);
                                break;
                            }
                            else //if ally
                            {   break;  }
                        }                        
                        i = pos;
                      }
                      if(i % 8 != 7)
                      {
                           while(i % 8 < 7 ) //can go right
                           {
                             if(state[i+1] == 0) // if empty
                             {
                                positions.add(i+1);
                                i++;
                             }
                             else if(state[i+1] >= 7) //if enemy
                             {
                                positions.add(i+1);
                                break;
                             }
                             else //if ally
                             {   break;  }
                          }                        
                          i = pos;
                          
                          while(i % 8 < 7 && i-7 > 0) //can go right and up
                          {
                              if(state[i-7] == 0)   //if empty
                              {
                                  positions.add(i-7);
                                  i -=7;
                              }
                              else if(state[i-7] >= 7)  //if enemy
                              {
                                  positions.add(i-7);
                                  break;
                              }
                              else  // if ally
                              { break;  }
                          }
                          i = pos;
                          while(i % 8 < 7 && i+9 < 63) //can go right and down
                          {
                              if(state[i+9] == 0) //if empty
                              {
                                  positions.add(i+9);
                                  i += 9;
                              }
                              else if(state[i+9] >= 7) //if enemy
                              {
                                  positions.add(i+9);
                                  break;
                              }
                              else
                              { break;  }
                          }
                      }
                      if(i > 7)
                      {
                          while(i-8 > 0) //can go up
                          {
                              if(state[i-8] == 0)   //if empty
                              {
                                  positions.add(i-8);
                                  i -=8;
                              }
                              else if(state[i-8] >= 7)  //if enemy
                              {
                                  positions.add(i-8);
                                  break;
                              }
                              else  // if ally
                              { break;  }
                          }
                          i = pos;
                      }
                      if(i<56)
                      {
                          while(i+8 < 63) //can go down
                          {
                              if(state[i+8] == 0) //if empty
                              {
                                  positions.add(i+8);
                                  i += 8;
                              }
                              else if(state[i+8] >= 7) //if enemy
                              {
                                  positions.add(i+8);
                                  break;
                              }
                              else
                              { break;  }
                          }
                      }
                   }
                else    //black player
                {
                    if(i%8 != 0)
                    {
                        while(i%8>0) //can go left 
                        {
                            if(state[i-1] == 0)     //if empty
                            {
                                positions.add(i-1);
                                i--;
                            }
                            else if(state[i-1] < 7 ) //if enemy
                            {
                                positions.add(i-1);
                                break;
                            }
                            else    // if ally
                            {   break;  }
                        }
                        i = pos;
                    
                        while(i%8>0 && i-9 > 0) //can go left and up
                        {
                            if(state[i-9] == 0)
                            {
                                positions.add(i-9);
                                i -= 9;
                            }
                            else if(state[i-9] < 7 ) //check one up and one left
                            {
                                positions.add(i-9);
                                break;
                            }
                            else
                            {   break;  }
                        }
                        i = pos;
                        
                        while(i % 8 > 0 && i+7 < 63) //can go left and down
                        {
                            if(state[i+7] == 0) // if empty
                            {
                                positions.add(i+7);
                                i += 7;
                            }
                            else if(state[i+7] < 7) //if enemy
                            {
                                positions.add(i+7);
                                break;
                            }
                            else //if ally
                            {   break;  }
                        }                        
                        i = pos;
                      }
                      if(i % 8 != 7)
                      {
                           while(i % 8 < 7 ) //can go right
                           {
                             if(state[i+1] == 0) // if empty
                             {
                                positions.add(i+1);
                                i++;
                             }
                             else if(state[i+1] < 7) //if enemy
                             {
                                positions.add(i+1);
                                break;
                             }
                             else //if ally
                             {   break;  }
                          }                        
                          i = pos;
                          
                          while(i % 8 < 7 && i-7 > 0) //can go right and up
                          {
                              if(state[i-7] == 0)   //if empty
                              {
                                  positions.add(i-7);
                                  i -=7;
                              }
                              else if(state[i-7] < 7)  //if enemy
                              {
                                  positions.add(i-7);
                                  break;
                              }
                              else  // if ally
                              { break;  }
                          }
                          i = pos;
                          while(i % 8 < 7 && i+9 < 63) //can go right and down
                          {
                              if(state[i+9] == 0) //if empty
                              {
                                  positions.add(i+9);
                                  i += 9;
                              }
                              else if(state[i+9] < 7) //if enemy
                              {
                                  positions.add(i+9);
                                  break;
                              }
                              else
                              { break;  }
                          }
                      }
                      if(i > 7)
                      {
                          while(i-8 > 0) //can go up
                          {
                              if(state[i-8] == 0)   //if empty
                              {
                                  positions.add(i-8);
                                  i -=8;
                              }
                              else if(state[i-8] < 7)  //if enemy
                              {
                                  positions.add(i-8);
                                  break;
                              }
                              else  // if ally
                              { break;  }
                          }
                          i = pos;
                      }
                      if(i<56)
                      {
                          while(i+8 < 63) //can go down
                          {
                              if(state[i+8] == 0) //if empty
                              {
                                  positions.add(i+8);
                                  i += 8;
                              }
                              else if(state[i+8] < 7) //if enemy
                              {
                                  positions.add(i+8);
                                  break;
                              }
                              else
                              { break;  }
                          }
                      }
                }
                return positions;
             }
            
            public ArrayList<Integer> moveKing(int pos, int player)
            {
                positions = new ArrayList<Integer>();                
                
                if(player == -1) //white player. for now used -1
                {
                    if(pos % 8 > 0) // not in leftmost file (a)
                    {
                        if(state[pos-1] == 0 || state[pos-1] >= 7)
                            positions.add(pos-1);
                        
                        if(pos > 7) // not in top rank
                        {
                            if(state[pos-9] == 0 || state[pos-9] >= 7)
                                positions.add(pos-9);
                        }
                        if(pos < 56) // not in bottom rank
                        {
                            if(state[pos+7] == 0 || state[pos+7] >= 7)
                                positions.add(pos+7);
                        }
                    }
                    if(pos % 8 < 7) // not in rightmost file (h)
                    {
                        if(state[pos+1] == 0 || state[pos+1] >= 7)
                            positions.add(pos+1);
                        
                        if(pos > 7) //not in top rank
                        {
                            if(state[pos-7] == 0 || state[pos-7] >= 7)
                                positions.add(pos-7);
                        }
                        if(pos < 56)
                        {
                            if(state[pos+9] == 0 || state[pos+9] >= 7)
                                positions.add(pos+9);
                        }
                    }
                    if(pos>7)
                    {
                        if(state[pos-8] == 0 || state[pos-8] >= 7)
                            positions.add(pos-8);
                    }
                    if(pos < 56)
                    {
                        if(state[pos+8] == 0 || state[pos+8] >= 7)
                            positions.add(pos+8);
                    }
                   
                }
                else
                {
                    if(pos % 8 > 0) // not in leftmost file (a)
                    {
                        if(state[pos-1] < 7)
                            positions.add(pos-1);
                        
                        if(pos > 7) // not in top rank
                        {
                            if(state[pos-9] < 7)
                                positions.add(pos-9);
                        }
                        if(pos < 56) // not in bottom rank
                        {
                            if(state[pos+7] < 7)
                                positions.add(pos+7);
                        }
                    }
                    if(pos % 8 < 7) // not in rightmost file (h)
                    {
                        if(state[pos+1] < 7)
                            positions.add(pos+1);
                        
                        if(pos > 7) //not in top rank
                        {
                            if(state[pos-7] < 7)
                                positions.add(pos-7);
                        }
                        if(pos < 56)
                        {
                            if(state[pos+9] < 7)
                                positions.add(pos+9);
                        }
                    }
                    if(pos>7)
                    {
                        if(state[pos-8] < 7)
                            positions.add(pos-8);
                    }
                    if(pos < 56)
                    {
                        if(state[pos+8] < 7)
                            positions.add(pos+8);
                    }
                }
                return positions;
            }
            
            
         }
                    
      }

                
            
            
      
          


    


  