package app.battleship.ship;

public class Submarine extends AbstractShip {
    public Submarine(Orientation o) {
		super('S', "Sous-marin", 3, o);
	}

    public Submarine() {
		this(null);
    }
}
