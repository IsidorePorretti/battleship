package app.battleship.ship;



import java.io.Serializable;
import java.util.*;
import java.util.Random;

import app.battleship.Hit;
import app.battleship.IBoard;
import app.battleship.ship.AbstractShip.Orientation;

public class BattleShipsAI implements Serializable {

 
    private final int size;

    private final IBoard board;

    private final IBoard opponent;

    private int lastStrike[];

    private Boolean lastVertical;

 
    public BattleShipsAI(IBoard myBoard, IBoard opponentBoard) {
        this.board = myBoard;
        this.opponent = opponentBoard;
        size = board.getSize();
    }

    public void putShips(AbstractShip ships[]) {
        int x, y;
        AbstractShip.Orientation o;
        Random rnd = new Random();
        AbstractShip.Orientation[] orientations = AbstractShip.Orientation.values();

        for (AbstractShip s : ships) {
            do {
            	x = rnd.nextInt(size);								
            	y = rnd.nextInt(size);					
            	o = orientations[rnd.nextInt(4)];		
            	s.setOrientation(o);

            } while (!canPutShip(s, x, y));
            board.putShip(s, x, y);
        }
    }
    public Hit sendHit(int[] coords) {
        int res[] = null;
        if (coords == null || coords.length < 2) {
            throw new IllegalArgumentException("must provide an initialized array of size 2");
        }

        if (lastVertical != null) {
            if (lastVertical) {
                res = pickVCoord();
            } else {
                res = pickHCoord();
            }

            if (res == null) {
                lastStrike = null;
                lastVertical = null;
            }
        } else if (lastStrike != null) {
            res = pickVCoord();
            if (res == null) {
                res = pickHCoord();
            }
            if (res == null) {
                lastStrike = null;
            }
        }

        if (lastStrike == null) {
            res = pickRandomCoord();
        }

        Hit hit = opponent.sendHit(res[0], res[1]);
        board.setHit(hit != Hit.MISS, res[0], res[1]);

        if (hit != Hit.MISS) {
            if (lastStrike != null) {
                lastVertical = guessOrientation(lastStrike, res);
            }
            lastStrike = res;
        }

        coords[0] = res[0];
        coords[1] = res[1];
        return hit;
    }


    private boolean canPutShip(AbstractShip ship, int x, int y) {
        AbstractShip.Orientation o = ship.getorientation();
        int dx = 0, dy = 0;
        if (o == AbstractShip.Orientation.EAST) {
            if (x + ship.gettaille() >= this.size) {
                return false;
            }
            dx = 1;
        } else if (o == AbstractShip.Orientation.SOUTH) {
            if (y + ship.gettaille() >= this.size) {
                return false;
            }
            dy = 1;
        } else if (o == AbstractShip.Orientation.NORTH) {
            if (y + 1 - ship.gettaille() < 0) {
                return false;
            }
            dy = -1;
        } else if (o == AbstractShip.Orientation.WEST) {
            if (x + 1 - ship.gettaille() < 0) {
                return false;
            }
            dx = -1;
        }

        int ix = x;
        int iy = y;

        for (int i = 0; i < ship.gettaille(); ++i) {
            if (board.hasShip(ix, iy)) {
                return false;
            }
            ix += dx;
            iy += dy;
        }

        return true;
    }

    private boolean guessOrientation(int[] c1, int[] c2) {
        return c1[0] == c2[0];
    }

    private boolean isUndiscovered(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && board.getHit(x, y) == null;
    }

    public int[] pickRandomCoord() {
        Random rnd = new Random();
        int x;
        int y;

        do {
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
        } while (!isUndiscovered(x, y));

        return new int[] { x, y };
    }

    
    private int[] pickVCoord() {
        int x = lastStrike[0];
        int y = lastStrike[1];

        for (int iy : new int[] { y - 1, y + 1 }) {
            if (isUndiscovered(x, iy)) {
                return new int[] { x, iy };
            }
        }
        return null;
    }

    private int[] pickHCoord() {
        int x = lastStrike[0];
        int y = lastStrike[1];

        for (int ix : new int[] { x - 1, x + 1 }) {
            if (isUndiscovered(ix, y)) {
                return new int[] { ix, y };
            }
        }
        return null;
    }
}
