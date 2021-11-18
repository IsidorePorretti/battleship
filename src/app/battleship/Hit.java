package app.battleship;


import java.util.NoSuchElementException;


public enum Hit {

	MISS(-1, "manqué"),
    STRIKE(-2, "touché"),
    DESTROYER(2, "Frégate"),
    SUBMARINE(3, "Sous-marin"),
    BATTLESHIP(4, "Croiseur"),
    CARRIER(5, "Porte-avion"),
    DEJA(-3, "Position-deja-frappé");

	private int value;
	private String label;
	
	Hit(int value, String label) {
        this.value = value;
        this.label = label;
    }
	public static Hit InttoHit(int val)
	{
		for(Hit hit : Hit.values())
		{
			if(hit.value == val )
				return hit;
		}
		
		throw new NoSuchElementException("error in receiving that enum value " + val);
	}
	public String toString() {
        return this.label;
    }
	public int getValue(){
    	return this.value;
    }


}
