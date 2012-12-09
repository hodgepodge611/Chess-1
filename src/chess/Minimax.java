/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.Gamestate.GameMoves;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

/**
 *
 * @author Tyler
 */
public class Minimax implements Cloneable
{
    public static final int UNLIMITED_SEARCH_DEPTH = -1;
    public static final int MINI_HAS_WON           = Integer.MAX_VALUE;
    public static final int STALE_MATE             = 0;
    public static final int MAX_HAS_WON            = Integer.MIN_VALUE;
    public static final int MAX_TURN               = 1;
    public static final int MIN_TURN               = -1;

    private int player = Minimax.MAX_TURN; // Must always be 1 or -1
    
    public Gamestate myState = new Gamestate();
    

    public final int getPlayer()
    {
        return this.player;
    }

    public final void makePerfectMove(int maxSearchDepth)
    {
        if(maxSearchDepth == 0)
        {
            return;
        }

        LinkedList<int[]> moves = this.listAllLegalMoves();
        if(moves.isEmpty())
        {
            this.staleMate();
            return;
        }
        else if(moves.size() == 1)
        {
            doMove(moves.get(0));
            return;
        }

        int     bestScore = this.player == Minimax.MAX_TURN ? Minimax.MINI_HAS_WON : Minimax.MAX_HAS_WON;
        int[]  bestMove  = null;

        for(int[] move : moves)
        {
            Minimax tempBoard = (Minimax)this.clone();
            tempBoard.doMove(move);
            int score = evaluate(maxSearchDepth == Minimax.UNLIMITED_SEARCH_DEPTH ? Minimax.UNLIMITED_SEARCH_DEPTH : maxSearchDepth - 1, new AlphaBeta());
            if(score * player < bestScore || bestMove == null)
            {
                bestScore = score * player;
                bestMove  = move;
            }
        }
        doMove(bestMove);
    }

    public final int evaluate(int maxSearchDepth, AlphaBeta alphaBeta)
    {
        int currentScore = this.getCurrentScore();
        if(currentScore == Minimax.MINI_HAS_WON || currentScore == Minimax.MAX_HAS_WON)
        {
            return currentScore;
        }
        LinkedList<int[]> moves = this.listAllLegalMoves();
        if(moves.isEmpty())
        {
            return Minimax.STALE_MATE;
        }
        int bestScore = 0;
        for(int[] move : moves)
        {
            Minimax tempBoard = (Minimax)this.clone();
            tempBoard.doMove(move);
            int score;
            if(maxSearchDepth == 0)
            {
                score = tempBoard.getCurrentScore();
            }
            else
            {
                score = tempBoard.evaluate(maxSearchDepth == Minimax.UNLIMITED_SEARCH_DEPTH ? Minimax.UNLIMITED_SEARCH_DEPTH : maxSearchDepth - 1, alphaBeta);

                // Alpha-beta pruning
                if(this.player != Minimax.MIN_TURN)
                {
                    if(score < alphaBeta.alpha)
                    {
                        return score;
                    }
                    else if(score < alphaBeta.beta)
                    {
                        alphaBeta.beta = score;
                    }
                }
                else
                {
                    if(score > alphaBeta.beta)
                    {
                        return score;
                    }
                    else if(score > alphaBeta.alpha)
                    {
                        alphaBeta.alpha = score;
                    }
                }
            }
            if(score == Minimax.MINI_HAS_WON && player == -1)
            {
                return Minimax.MINI_HAS_WON;
            }
            else if(score == Minimax.MAX_HAS_WON && player == 1)
            {
                return Minimax.MAX_HAS_WON;
            }
            if(score * player > bestScore)
            {
                bestScore = score * player;
            }
        }
        return bestScore;
    }

    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public int getCurrentScore()
    {
        return 0;
    }
    
    public LinkedList<int[]> listAllLegalMoves()
    {
        Gamestate.GameMoves gm = myState.new GameMoves();
        LinkedList<int[]> moves = new LinkedList<int[]>();
        for(int i = 0; i < myState.state.length; i++)
        {
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            byte temp = myState.state[i];
            if(temp == 1 && this.getPlayer() == -1)
            {
                tempList = gm.movePawn(temp, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i, tempList.get(j)});                    
                }
                //gm.validatePawnMove(temp, tempList);
                
            }
            if(temp == 7 && this.getPlayer() == 1)
            {
                tempList = gm.movePawn(temp, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i, tempList.get(j)});                    
                }
               // myState.validatePawn(tempList);                
            }
            if(temp == 2 && this.getPlayer() == -1)
            {
                tempList = gm.moveKnight(temp, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 8 && this.getPlayer() == 1)
            {
                tempList = gm.moveKnight(temp, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 3 && this.getPlayer() == -1)
            {
                tempList = gm.moveBishop(temp, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 9 && this.getPlayer() == 1)
            {
                tempList = gm.moveBishop(temp, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 4 && this.getPlayer() == -1)
            {
                tempList = gm.moveRook(temp, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 10 && this.getPlayer() == 1)
            {
                tempList = gm.moveRook(temp, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 5 && this.getPlayer() == -1)
            {
                tempList = gm.moveQueen(temp, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 11 && this.getPlayer() == 1)
            {
                tempList = gm.moveQueen(temp, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 6 && this.getPlayer() == -1)
            {
                tempList = gm.moveKing(temp, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 7 && this.getPlayer() == 1)
            {
                tempList = gm.moveKing(temp, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            
        }
        
        return moves;
    }
    
    public void moveAction(int[] move)
    {
        //work with myState.state as your byte array.perform move then handle
        //conditions like remove oppenent piece or get piece back (which we need to implement eventually)
        // can use this.player to get who holds current move(-1=W , 1=B)
        //or can just simply tell by what number is in the initial position myState.state[move[0]] 
        //move[0] holds inital positon, move[1] holds resulting position
        //myState.state[position] holds the number of that position.
        
        Byte movingPiece = myState.state[move[0]];
        myState.state[move[1]] = movingPiece;
        
    }
    public final void doMove(int[] move)
    {
        this.moveAction(move);
        player *= -1;
        
    }
    public void staleMate()
    {
        
    }
}