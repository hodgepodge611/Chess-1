/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;



/**
*
* @author Tyler
*/
public class Chess {
/**
* @param args the command line arguments
*/
    public static void main(String[] args) throws IOException, JSONException, InterruptedException {
        final int maxSearchDepth = 2;
        final String gameId = "286"; // Replace with game ID
        final String password = "32c68cae"; // Replace with team secret
        final int team = 1; // replace with team number
        final String baseUrl = "http://www.bencarle.com/chess/";
        final String credentials = gameId + "/" + team + "/" + password + "/";
        
        HttpClient client = new DefaultHttpClient();
        HttpGet pollRequest = new HttpGet(baseUrl + "poll/" + credentials + "/");
        HttpGet pushRequest;
        HttpResponse response;
        BufferedReader reader;
        JSONObject json = null;
        
        Gamestate board = new Gamestate();
        Minimax search = new Minimax(board, -1, maxSearchDepth);
        int[] move;
        
       
        while (true) {
            do {
                response = client.execute(pollRequest);
                reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                json = new JSONObject(reader.readLine());
                reader.close();
                EntityUtils.consume(response.getEntity());
                Thread.sleep(5000);
                System.out.println("Waiting for the opponents move...");
            } while (!json.getBoolean("ready"));
            
            if (!json.getString("lastmove").isEmpty()) {
                move = manageMove(json.getString("lastmove"));
                System.out.println("The opponent's move is " + json.getString("lastmove") + ".");
                byte removedPiece = board.doMove(move);
                if (removedPiece == 12 || removedPiece == 6) {
                    break;
                }
            }
            else {
                System.out.println("Oh wait, we're actually going first!");
            }
            
            search.makePerfectMove(maxSearchDepth);
            move = search.getBestMove();
            board.doMove(move);
            System.out.println("Our move is " + stringifyMove(move, board.state[move[1]]));
            pushRequest = new HttpGet(baseUrl + "move/" + credentials + stringifyMove(move, board.state[move[1]]) + "/");
            System.out.println(baseUrl + "move/" + credentials + stringifyMove(move, board.state[move[1]]) + "/");
            response = client.execute(pushRequest);
            EntityUtils.consume(response.getEntity());
            if (move.length == 3 && move[2] == 1) {
                break;
            }
        }
        System.out.println("Game over!");
    }
    private static int[] manageMove(String move)
    {
      char startFile = move.charAt(1);
      int startRank = Integer.parseInt(move.substring(2,3));
      int startPos = 0;
      switch (startFile)
      {
          case 'a':
              startPos = (startRank - 1) * 8;
              break;
          case 'b':
              startPos = ((startRank - 1) * 8) + 1;
              break;
          case 'c':
              startPos = ((startRank - 1) * 8) + 2;
              break;
          case 'd':
              startPos = ((startRank - 1) * 8) + 3;
              break;
          case 'e':
              startPos = ((startRank - 1) * 8) + 4;
              break;
          case 'f':
              startPos = ((startRank - 1) * 8) + 5;
              break;
          case 'g':
              startPos = ((startRank - 1) * 8) + 6;
              break;
          case 'h':
              startPos = ((startRank - 1) * 8) + 7;
              break;
              
      }
      char endFile = move.charAt(3);
      int endRank;
      if(move.length() ==6)
        endRank = Integer.parseInt(move.substring(4,5));
      else
        endRank = Integer.parseInt(move.substring(4));
      int endPos = 0;
      
     switch (endFile)
      {
          case 'a':
              endPos = (startRank - 1) * 8;
              break;
          case 'b':
              endPos = ((startRank - 1) * 8) + 1;
              break;
          case 'c':
              endPos = ((startRank - 1) * 8) + 2;
              break;
          case 'd':
              endPos = ((startRank - 1) * 8) + 3;
              break;
          case 'e':
              endPos = ((startRank - 1) * 8) + 4;
              break;
          case 'f':
              endPos = ((startRank - 1) * 8) + 5;
              break;
          case 'g':
              endPos = ((startRank - 1) * 8) + 6;
              break;
          case 'h':
              endPos = ((startRank - 1) * 8) + 7;
              break;
              
      }
      return new int[]{startPos, endPos};
    }
    
    private static String stringifyMove(int[] move, int piece)
    {
        String finalMove = "";
        int start = move[0];
        int end = move[1];
        switch (piece)
        {
            case 1:
            case 7:
                finalMove += "P";
                break;
            case 2:
            case 8:
                finalMove += "N";
                break;
            case 3:
            case 9:
                finalMove += "B";
                break;
            case 4:
            case 10:
                finalMove += "R";
                break;
            case 5:
            case 11:
                finalMove += "Q";
                break;
            case 6:
            case 12:
                finalMove += "K";
                break;
        }
        
        switch(start % 8)
        {
            case 0:
                finalMove += "a";
                break;
            case 1:
                finalMove += "b";
                break;
            case 2:
                finalMove += "c";
                break;
            case 3:
                finalMove += "d";
                break;
            case 4:
                finalMove += "e";
                break;
            case 5:
                finalMove += "f";
                break;
            case 6:
                finalMove += "g";
                break;
            case 7:
                finalMove += "h";
                break;
        }
        
        switch((start/8))
        {
            case 0:
                finalMove += "1";
                break;
            case 1:
                finalMove += "2";
                break;
            case 2:
                finalMove += "3";
                break;
            case 3:
                finalMove += "4";
                break;
            case 4:
                finalMove += "5";
                break;
            case 5:
                finalMove += "6";
                break;
            case 6:
                finalMove += "7";
                break;
            case 7:
                finalMove += "8";
                break;
        }
        switch(end % 8)
        {
            case 0:
                finalMove += "a";
                break;
            case 1:
                finalMove += "b";
                break;
            case 2:
                finalMove += "c";
                break;
            case 3:
                finalMove += "d";
                break;
            case 4:
                finalMove += "e";
                break;
            case 5:
                finalMove += "f";
                break;
            case 6:
                finalMove += "g";
                break;
            case 7:
                finalMove += "h";
                break;
        }
        
        switch((end/8))
        {
            case 0:
                finalMove += "1";
                break;
            case 1:
                finalMove += "2";
                break;
            case 2:
                finalMove += "3";
                break;
            case 3:
                finalMove += "4";
                break;
            case 4:
                finalMove += "5";
                break;
            case 5:
                finalMove += "6";
                break;
            case 6:
                finalMove += "7";
                break;
            case 7:
                finalMove += "8";
                break;
        }
         if (piece == 1 && move[1] >= 56) { //Pawn reaching end?
            finalMove+= "Q"; //Queen him
        }
        else if (piece == 7 && move[1] <= 7) {
            finalMove+= "Q";
        }
        return finalMove;
    }
}
}
