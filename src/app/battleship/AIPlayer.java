package app.battleship;

import java.util.List;

import app.battleship.board.Board;
import app.battleship.ship.AbstractShip;
import app.battleship.ship.BattleShipsAI;


public class AIPlayer extends Player {
    private BattleShipsAI ai;
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.
    public void AiPutShip() {
    	ai.putShips(ships);
    }
    
    public Hit AiSendHit( int[] coord)
    {
    	Hit hit = ai.sendHit(coord);
    	return hit;
    }
    public int[] pickRandomCoord() {
        int [] coords =  ai.pickRandomCoord();

        return coords;
    }

}