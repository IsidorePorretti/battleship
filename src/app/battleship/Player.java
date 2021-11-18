package app.battleship;


import java.io.Serializable;
import java.util.List;

import app.battleship.board.Board;
import app.battleship.ship.AbstractShip;
import app.battleship.ship.AbstractShip.Orientation;

public class Player {
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getnom(), s.gettaille());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            Orientation o = null;
            if(res.orientation.equals("n"))
            	o = Orientation.NORTH;
            if(res.orientation.equals("s"))
            	o = Orientation.SOUTH;
            if(res.orientation.equals("e"))
            	o= Orientation.EAST;
            if(res.orientation.equals("w"))
            	o = Orientation.WEST;
            s.setOrientation(o);
            
            try 
            {
            	board.putShip(s, res.x, res.y);
            	
            }
            catch(IllegalArgumentException e)
            {
            	System.out.print("error!");
            }
            
            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;

        do {
            System.out.println("Où veux tu frapper ? (lettre puis nombre)");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            try{
            	hit = opponentBoard.sendHit(hitInput.x, hitInput.y);
            	done = true;
            	if(opponentBoard.hasShip(hitInput.x, hitInput.y)){	
            		board.setHit(true, hitInput.x, hitInput.y);		
            	}
            	else{
            		board.setHit(false, hitInput.x, hitInput.y);		
            	}
            }
            catch(Exception e){
        	   done = false;
        	   System.out.println("Not valid coordinates!");
            }
            coords[0] = hitInput.x; 
            coords[1] = hitInput.y;
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
    
    
}
