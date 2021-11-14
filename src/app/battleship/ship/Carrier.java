package app.battleship.ship;

public class Carrier extends AbstractShip {
    public Carrier(Orientation o) {
		super('C', "Porte avions", 5, o);
	}

	public Carrier(){
	this(null);
	}
}
