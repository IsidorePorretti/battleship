package app.battleship.ship;

import app.battleship.ship.AbstractShip.Orientation;

public class Battleship extends AbstractShip {
    public Battleship(Orientation o) {
		super('B', "Croiseur", 4, o);
	}

	public Battleship(){
	this(null);
	}
}
