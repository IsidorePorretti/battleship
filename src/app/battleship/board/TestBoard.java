package app.battleship.board;


import app.battleship.ship.*;
import app.battleship.ship.AbstractShip.Orientation;

public class TestBoard {
    public static void main(String args[]) {
        Board b = new Board("test");
		b.putHit(1, 2);
		b.putShip(new Carrier(Orientation.NORTH), 4, 4);
		b.putShip(new Destroyer(Orientation.EAST), 5, 4);
		b.print();
	}
}
