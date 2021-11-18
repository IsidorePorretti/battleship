package app.battleship.ship;

public class Battleship extends AbstractShip{
	
	public Battleship(){					//premiere constructeur 
		//on utilise super pour faire reference a la methode mere
		super("B", "Battleship", 4, Orientation.EAST);
	}
	
	public Battleship(Orientation orientation){	//deuxieme constructeur en specifiant le valeur d'orientation
		super("B", "Battleship", 4, orientation);
	}
}
