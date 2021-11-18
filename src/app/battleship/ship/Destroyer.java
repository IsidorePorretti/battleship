package app.battleship.ship;

import app.battleship.ship.AbstractShip;

public class Destroyer extends AbstractShip{
	
	public Destroyer(){	
		super("D", "Destroyer", 3, Orientation.EAST);
	}
	
	public Destroyer(Orientation orientation){	
		super("D", "Destroyer", 3, orientation);
	}
}
