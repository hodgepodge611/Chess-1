/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package chess;

import chess.Gamestate.GameMoves;
import java.util.ArrayList;
import java.util.LinkedList;

/**
*
* @author Tyler
*/
public class Minimax
{
    private Gamestate state;
    private int maxSearchDepth;
    private int[] bestMove;
    private boolean gameOver = false;
    private final int player;
    
    public Minimax(Gamestate state, int player, int maxSearchDepth) {
        this.state = state;
        this.player = player;
        this.maxSearchDepth = maxSearchDepth;
    }

    public int makePerfectMove(int maxSearchDepth)
    {
        return evaluate(state, maxSearchDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, player);
    }
    
    public int evaluate(Gamestate state, int depth, int alpha, int beta, int player) {
        if (depth == 0 || gameOver) {
            return getCurrentScore(state, player);
        }
        int score = Integer.MIN_VALUE;
        LinkedList<int[]> moves = listAllLegalMoves();
        for (int[] move : moves) {
            byte removedPiece = state.doMove(move);
            int currentScore = -evaluate(state, depth-1, -beta, -alpha, -player);
            if (currentScore > score) {
                score = currentScore;
            }
            if (score > alpha) {
                alpha = score;
                if (depth == maxSearchDepth) {
                    bestMove = new int[]{move[0], move[1]};
                }
            }
            state.undoMove(move, removedPiece);
            if (alpha >= beta) {
                return alpha;
            }
        }
        return score;
    }
    
    public int getCurrentScore(Gamestate state, int player)
    {
        int whitePawns;
        int whiteKnights = 0;
        int whiteBishops = 0;
        int whiteRooks = 0;
        int whiteQueens = 0;
        int whiteKings = 0;
        
        int blackPawns;
        int blackKnights = 0;
        int blackBishops = 0;
        int blackRooks = 0;
        int blackQueens = 0;
        int blackKings = 0;

        int doubledWhitePawns = 0;
        int doubledBlackPawns = 0;
        int backwardWhitePawns = 0;
        int backwardBlackPawns = 0;
        int isolatedWhitePawns = 0;
        int isolatedBlackPawns = 0;

        int fileAWhitePawns = 0;
        int fileBWhitePawns = 0;
        int fileCWhitePawns = 0;
        int fileDWhitePawns = 0;
        int fileEWhitePawns = 0;
        int fileFWhitePawns = 0;
        int fileGWhitePawns = 0;
        int fileHWhitePawns = 0;
        int fileABlackPawns = 0;
        int fileBBlackPawns = 0;
        int fileCBlackPawns = 0;
        int fileDBlackPawns = 0;
        int fileEBlackPawns = 0;
        int fileFBlackPawns = 0;
        int fileGBlackPawns = 0;
        int fileHBlackPawns = 0;

        for (int i = 0; i < 8; i++) { // Doubled Pawns and Backward Pawns Calculator
            int pos = i;
            switch (i) {
                case 0:
                    while (pos <= 56) {
                        if (state.state[pos] == 1) {
                            fileAWhitePawns++;
                            // backward pawns calculator
                            if (pos <= 40) {
                                if (state.state[pos + 9] == 1 &&
                                        state.state[pos + 17] == 7) {
                                    backwardWhitePawns++; // friendly pawn diagonal that's blocking enemy pawn?
                                }
                            }
                            
                        }
                        else if (state.state[pos] == 7) {
                            fileABlackPawns++;
                            if (pos >= 16) { // backward pawns calculator
                                if (state.state[pos - 7] == 7 &&
                                        state.state[pos - 15] == 1) {
                                    backwardBlackPawns++;
                                }
                            }
                        }
                        pos += 8;
                    }
                    if (fileAWhitePawns > 1) { // if more than one like-playered pawn in file, doubled pawns
                        doubledWhitePawns += fileAWhitePawns;
                    }
                    if (fileABlackPawns > 1) {
                        doubledBlackPawns += fileABlackPawns;
                    }
                    break;
                case 1:
                    while (pos <= 57) {
                        if (state.state[pos] == 1) {
                            fileBWhitePawns++;
                            if (pos <= 41) {
                                if ((state.state[pos + 7] == 1 &&
                                        state.state[pos + 15] == 7) ||
                                        (state.state[pos + 9] == 1 &&
                                        state.state[pos + 17] == 7)) {
                                    backwardWhitePawns++;
                                }
                            }
                        }
                        else if (state.state[pos] == 7) {
                            fileBBlackPawns++;
                            if (pos >= 17) {
                                if ((state.state[pos - 9] == 7 &&
                                        state.state[pos - 17] == 1) ||
                                        (state.state[pos - 7] == 7 &&
                                        state.state[pos - 15] == 1)) {
                                    backwardBlackPawns++;
                                }
                            }
                        }
                        pos += 8;
                    }
                    if (fileBWhitePawns > 1) {
                        doubledWhitePawns += fileBWhitePawns;
                    }
                    if (fileBBlackPawns > 1) {
                        doubledBlackPawns += fileBBlackPawns;
                    }
                    break;
                case 2:
                    while (pos <= 58) {
                        if (state.state[pos] == 1) {
                            fileCWhitePawns++;
                            if (pos <= 42) {
                                if ((state.state[pos + 7] == 1 &&
                                        state.state[pos + 15] == 7) ||
                                    ( state.state[pos + 9] == 1 &&
                                        state.state[pos + 17] == 7)) {
                                    backwardWhitePawns++;
                                }
                            }
                        }
                        else if (state.state[pos] == 7) {
                            fileCBlackPawns++;
                            if (pos >= 18) {
                                if ((state.state[pos - 9] == 7 &&
                                        state.state[pos - 17] == 1) ||
                                        (state.state[pos - 7] == 7 &&
                                        state.state[pos - 15] == 1)) {
                                    backwardBlackPawns++;
                                }
                            }
                        }
                        pos += 8;
                    }
                    if (fileCWhitePawns > 1) {
                        doubledWhitePawns += fileCWhitePawns;
                    }
                    if (fileCBlackPawns > 1) {
                        doubledBlackPawns += fileCBlackPawns;
                    }
                    break;
                case 3:
                    while (pos <= 59) {
                        if (state.state[pos] == 1) {
                            fileDWhitePawns++;
                            if (pos <= 43) {
                                if ((state.state[pos + 7] == 1 &&
                                        state.state[pos + 15] == 7) ||
                                        (state.state[pos + 9] == 1 &&
                                        state.state[pos + 17] == 7)) {
                                    backwardWhitePawns++;
                                }
                            }
                        }
                        else if (state.state[pos] == 7) {
                            fileDBlackPawns++;
                            if (pos >= 19) {
                                if ((state.state[pos - 9] == 7 &&
                                        state.state[pos - 17] == 1) ||
                                        (state.state[pos - 7] == 7 &&
                                        state.state[pos - 15] == 1)) {
                                    backwardBlackPawns++;
                                }
                            }
                        }
                        pos += 8;
                    }
                    if (fileDWhitePawns > 1) {
                        doubledWhitePawns += fileDWhitePawns;
                    }
                    if (fileDBlackPawns > 1) {
                        doubledBlackPawns += fileDBlackPawns;
                    }
                    break;
                case 4:
                    while (pos <= 60) {
                        if (state.state[pos] == 1) {
                            fileEWhitePawns++;
                            if (pos <= 44) {
                                if ((state.state[pos + 7] == 1 &&
                                        state.state[pos + 15] == 7) ||
                                        (state.state[pos + 9] == 1 &&
                                        state.state[pos + 17] == 7)) {
                                    backwardWhitePawns++;
                                }
                            }
                        }
                        else if (state.state[pos] == 7) {
                            fileEBlackPawns++;
                            if (pos >= 20) {
                                if ((state.state[pos - 9] == 7 &&
                                        state.state[pos - 17] == 1) ||
                                        (state.state[pos - 7] == 7 &&
                                        state.state[pos - 15] == 1)) {
                                    backwardBlackPawns++;
                                }
                            }
                        }
                        pos += 8;
                    }
                    if (fileEWhitePawns > 1) {
                        doubledWhitePawns += fileEWhitePawns;
                    }
                    if (fileEBlackPawns > 1) {
                        doubledBlackPawns += fileEBlackPawns;
                    }
                    break;
                case 5:
                    while (pos <= 61) {
                        if (state.state[pos] == 1) {
                            fileFWhitePawns++;
                            if (pos <= 45) {
                                if ((state.state[pos + 7] == 1 &&
                                        state.state[pos + 15] == 7) ||
                                        (state.state[pos + 9] == 1 &&
                                        state.state[pos + 17] == 7)) {
                                    backwardWhitePawns++;
                                }
                            }
                        }
                        else if (state.state[pos] == 7) {
                            fileFBlackPawns++;
                            if (pos >= 21) {
                                if ((state.state[pos - 9] == 7 &&
                                        state.state[pos - 17] == 1) ||
                                        (state.state[pos - 7] == 7 &&
                                        state.state[pos - 15] == 1)) {
                                    backwardBlackPawns++;
                                }
                            }
                        }
                        pos += 8;
                    }
                    if (fileFWhitePawns > 1) {
                        doubledWhitePawns += fileFWhitePawns;
                    }
                    if (fileFBlackPawns > 1) {
                        doubledBlackPawns += fileFBlackPawns;
                    }
                    break;
                case 6:
                    while (pos <= 62) {
                        if (state.state[pos] == 1) {
                            fileGWhitePawns++;
                            if (pos <= 46) {
                                if ((state.state[pos + 7] == 1 &&
                                        state.state[pos + 15] == 7) ||
                                        (state.state[pos + 9] == 1 &&
                                        state.state[pos + 17] == 7)) {
                                    backwardWhitePawns++;
                                }
                            }
                        }
                        else if (state.state[pos] == 7) {
                            fileGBlackPawns++;
                            if (pos >= 22) {
                                if ((state.state[pos - 9] == 7 &&
                                        state.state[pos - 17] == 1) ||
                                        (state.state[pos - 7] == 7 &&
                                        state.state[pos - 15] == 1)) {
                                    backwardBlackPawns++;
                                }
                            }
                        }
                        pos += 8;
                    }
                    if (fileGWhitePawns > 1) {
                        doubledWhitePawns += fileGWhitePawns;
                    }
                    if (fileGBlackPawns > 1) {
                        doubledBlackPawns += fileGBlackPawns;
                    }
                    break;
                case 7:
                    while (pos <= 63) {
                        if (state.state[pos] == 1) {
                            fileHWhitePawns++;
                            if (pos <= 47) {
                                if (state.state[pos + 7] == 1 &&
                                        state.state[pos + 15] == 7) {
                                    backwardWhitePawns++;
                                }
                            }
                        }
                        else if (state.state[pos] == 7) {
                            fileHBlackPawns++;
                            if (pos >= 23) {
                                if (state.state[pos - 9] == 7 &&
                                        state.state[pos - 17] == 1) {
                                    backwardBlackPawns++;
                                }
                            }
                        }
                        pos += 8;
                    }
                    if (fileHWhitePawns > 1) {
                        doubledWhitePawns += fileHWhitePawns;
                    }
                    if (fileHBlackPawns > 1) {
                        doubledBlackPawns += fileHBlackPawns;
                    }
                    break;
            }
        } // End doubled pawns and backward pawns calculator
         
        for (int i = 0; i < 8; i++) { // Isolated Pawns Calculator
            switch (i) { // i represents file. 0 = A, 7 = H
                case 0:
                    if (fileBWhitePawns == 0) {
                        isolatedWhitePawns += fileAWhitePawns;
                    }
                    if (fileBBlackPawns == 0) {
                        isolatedBlackPawns += fileABlackPawns;
                    }
                    break;
                case 1:
                    if (fileAWhitePawns == 0 && fileCWhitePawns == 0) {
                        isolatedWhitePawns += fileBWhitePawns;
                    }
                    if (fileABlackPawns == 0 && fileCBlackPawns == 0) {
                        isolatedBlackPawns += fileBBlackPawns;
                    }
                    break;
                case 2:
                    if (fileBWhitePawns == 0 && fileDWhitePawns == 0) {
                        isolatedWhitePawns += fileCWhitePawns;
                    }
                    if (fileBBlackPawns == 0 && fileDBlackPawns == 0) {
                        isolatedBlackPawns += fileCBlackPawns;
                    }
                    break;
                case 3:
                    if (fileCWhitePawns == 0 && fileEWhitePawns == 0) {
                        isolatedWhitePawns += fileDWhitePawns;
                    }
                    if (fileCBlackPawns == 0 && fileEBlackPawns == 0) {
                        isolatedBlackPawns += fileDBlackPawns;
                    }
                    break;
                case 4:
                    if (fileDWhitePawns == 0 && fileFWhitePawns == 0) {
                        isolatedWhitePawns += fileEWhitePawns;
                    }
                    if (fileDBlackPawns == 0 && fileFBlackPawns == 0) {
                        isolatedBlackPawns += fileEBlackPawns;
                    }
                    break;
                case 5:
                    if (fileEWhitePawns == 0 && fileGWhitePawns == 0) {
                        isolatedWhitePawns += fileFWhitePawns;
                    }
                    if (fileEBlackPawns == 0 && fileGBlackPawns == 0) {
                        isolatedBlackPawns += fileFBlackPawns;
                    }
                    break;
                case 6:
                    if (fileFWhitePawns == 0 && fileHWhitePawns == 0) {
                        isolatedWhitePawns += fileGWhitePawns;
                    }
                    if (fileFBlackPawns == 0 && fileHBlackPawns == 0) {
                        isolatedBlackPawns += fileGBlackPawns;
                    }
                    break;
                case 7:
                    if (fileGWhitePawns == 0) {
                        isolatedWhitePawns += fileHWhitePawns;
                    }
                    if (fileGBlackPawns == 0) {
                        isolatedBlackPawns += fileHBlackPawns;
                    }
                    break;
            }
        } // End isolated pawns calculator

        for (int i = 0; i < state.state.length; i++) {
            int piece = state.state[i];
            switch (piece) {
                case 2:
                    whiteKnights++;
                    break;
                case 3:
                    whiteBishops++;
                    break;
                case 4:
                    whiteRooks++;
                    break;
                case 5:
                    whiteQueens++;
                    break;
                case 6:
                    whiteKings++;
                    break;
                case 8:
                    blackKnights++;
                    break;
                case 9:
                    blackBishops++;
                    break;
                case 10:
                    blackRooks++;
                    break;
                case 11:
                    blackQueens++;
                    break;
                case 12:
                    blackKings++;
                    break;
            }
        }

        whitePawns = (fileAWhitePawns + fileBWhitePawns + fileCWhitePawns +
                fileDWhitePawns + fileEWhitePawns + fileFWhitePawns +
                fileGWhitePawns + fileHWhitePawns);
        blackPawns = (fileABlackPawns + fileBBlackPawns + fileCBlackPawns +
                fileDBlackPawns + fileEBlackPawns + fileFBlackPawns +
                fileGBlackPawns +fileHWhitePawns);
        
        if (player == -1) {// White player
            return (int)((200 * (whiteKings - blackKings)) +
                    (9 * (whiteQueens - blackQueens)) +
                    (5 * (whiteRooks - blackRooks)) +
                    (3 * (whiteBishops - blackBishops +
                        whiteKnights - blackKnights)) +
                    (1 * (whitePawns - blackPawns)) -
                    (0.5 * (doubledWhitePawns - doubledBlackPawns +
                        backwardWhitePawns - backwardBlackPawns +
                        isolatedWhitePawns - isolatedBlackPawns)) +
                    (0.1 * (this.listAllLegalMoves().size())));
        }
        else {
            return (int)((200 * (blackKings - whiteKings)) +
                    (9 * (blackQueens - whiteQueens)) +
                    (5 * (blackRooks - whiteRooks)) +
                    (3 * (blackBishops - whiteBishops +
                        whiteKnights - whiteKnights)) +
                    (1 * (blackPawns - whitePawns)) -
                    (0.5 * (doubledBlackPawns - doubledWhitePawns +
                        backwardBlackPawns - backwardWhitePawns +
                        isolatedBlackPawns - isolatedWhitePawns)) +
                    (0.1 * (this.listAllLegalMoves().size())));
        }
    }
    
    public LinkedList<int[]> listAllLegalMoves()
    {
        Gamestate.GameMoves gm = state.new GameMoves();
        LinkedList<int[]> moves = new LinkedList<int[]>();
        for(int i = 0; i < state.state.length; i++)
        {
            
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            byte temp = state.state[i];
            if(temp == 1 && this.player == -1)
            {
                tempList = gm.movePawn(i, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i, tempList.get(j)});
                }
                //gm.validatePawnMove(temp, tempList);
                
            }
            if(temp == 7 && this.player == 1)
            {
                tempList = gm.movePawn(i, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i, tempList.get(j)});
                }
               // myState.validatePawn(tempList);
            }
            if(temp == 2 && this.player == -1)
            {
                tempList = gm.moveKnight(i, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 8 && this.player == 1)
            {
                tempList = gm.moveKnight(i, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 3 && this.player == -1)
            {
                tempList = gm.moveBishop(i, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 9 && this.player == 1)
            {
                tempList = gm.moveBishop(i, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 4 && this.player == -1)
            {
                tempList = gm.moveRook(i, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 10 && this.player == 1)
            {
                tempList = gm.moveRook(i, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 5 && this.player == -1)
            {
                tempList = gm.moveQueen(i, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 11 && this.player == 1)
            {
                tempList = gm.moveQueen(i, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 6 && this.player == -1)
            {
                tempList = gm.moveKing(i, -1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
            if(temp == 7 && this.player == 1)
            {
                tempList = gm.moveKing(i, 1);
                for(int j = 0; j < tempList.size(); j++)
                {
                    moves.add(new int[]{i,tempList.get(j)});
                }
            }
        }
        return moves;
    }
    
    public int[] getBestMove() {
        return bestMove;
    }
}
