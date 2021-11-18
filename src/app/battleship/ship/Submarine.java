package app.battleship.ship;

import app.battleship.ship.AbstractShip;


public class Submarine extends AbstractShip{
	
	public Submarine(){					
		super("S", "Submarine", 2, Orientation.EAST);
	}
	
	public Submarine(Orientation orientation){	
		super("S", "Submarine", 2, orientation);
	}
}
