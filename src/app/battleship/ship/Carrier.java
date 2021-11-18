package app.battleship.ship;

import app.battleship.ship.AbstractShip;

public class Carrier extends AbstractShip{
	
	public Carrier(){					//premiere constructeur 
		//on utilise super pour faire reference a la methode mere
		super("C", "Carrier", 2, Orientation.EAST);
	}
	
	public Carrier(Orientation orientation){	//deuxieme constructeur en specifiant le valeur d'orientation
		super("C", "Carrier", 2, orientation);
	}
}
