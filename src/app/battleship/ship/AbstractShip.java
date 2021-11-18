package app.battleship.ship;

public abstract class AbstractShip {
	public enum Orientation{
		NORTH, SOUTH, EAST, WEST;
		}
	
	private String label; 
	private String nom;
	private int taille;
	private Orientation orientation;
	private int strikeCount;
	
	public AbstractShip(String c, String nom, int taille, Orientation orientation){
		this.label = c;
		this.nom = nom;
		this.taille = taille;
		this.orientation = orientation; 
	}
	public Character getlabel()
	{
		return this.label.charAt(0);
	}
	public String getnom()
	{
		return this.nom;
		
	}
	public int gettaille()
	{
		return this.taille;
	}
	public Orientation getorientation()
	{
		return this.orientation;
	}
	public void setOrientation(Orientation o )
	{
		this.orientation = o;
	}
	
	public void addstrike(){					
		if(this.strikeCount >= this.taille){
			throw new IllegalArgumentException("The number of Hit is already maximum");
		}
		this.strikeCount++;
	}
	
	public boolean issunk(){				
		if(this.taille == this.strikeCount)		
			return true;
		else
			return false;
	}
	
	public int getstrikeCount(){
		return this.strikeCount;
	}



}
