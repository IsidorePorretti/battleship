package app.battleship.board;

import java.io.IOException;

import app.battleship.Hit;
import app.battleship.IBoard;
import app.battleship.ship.AbstractShip;
import app.battleship.ship.ColorUtil;
import app.battleship.ship.StateShip;
import app.battleship.ship.AbstractShip.Orientation;

public class Board implements IBoard {
	
	private int size;
	private String nom;
	
	private StateShip[][] navires; 
	private Boolean[][] frappes ;
	
	
	
	public Board(String nom, int size){
	this.size = size;
	this.nom = nom;
	 this.frappes = new Boolean[this.size][this.size];
	 this.navires = new StateShip[this.size][this.size];
	}

	public Board(String nom){ 
	this.nom = nom;
	this.size = 10;
	this.frappes = new Boolean[this.size][this.size];
	this.navires = new StateShip[this.size][this.size];
	
	}

public void print(){

char letter = 'A'; 

try {Runtime.getRuntime().exec("clear");} catch (IOException e) {}
System.out.println();
System.out.println(this.nom);
System.out.print("Navires :   ");
System.out.println();


for (int i = -1; i < this.size; ++i){

	for(int j = -1; j< this.size;j++)
	{
		if(i == -1)
		{
			if(j == -1)
				System.out.print("   ");
			else
				System.out.print(" "+ ((char)(letter+j)));
				
		}
		else if(i!= -1 && j != -1) 
		{
			if(this.navires[j][i] == null){
				System.out.print(" .");
			}
			else{
				System.out.print(" " + this.navires[j][i].toString()); 
			}

		}
    }
    
	System.out.println();
	if(i < 8)
		System.out.print(" ");
	if(i+1 != this.size)
	 System.out.print(" "+ (i+2));
	
}


for (int i = -1; i < this.size; ++i){

	for(int j = -1; j< this.size;j++)
	{
		if(i == -1)
		{
			if(j == -1)
				System.out.print("   ");
			else
				System.out.print(" "+ ((char)(letter+j)));
				
		}
		else if(i!= -1 && j != -1)
		{
			if(frappes[j][i]== null)
				System.out.print(ColorUtil.colorize(" .", ColorUtil.Color.YELLOW));
			else if (frappes[j][i]== false)
				System.out.print(ColorUtil.colorize(" X", ColorUtil.Color.WHITE));
			else if (frappes[j][i]== true)
				System.out.print(ColorUtil.colorize(" X", ColorUtil.Color.RED));
		}
			
	}
	System.out.println();
	if(i < 8)
		System.out.print(" ");
	if(i+1 != this.size)
	 System.out.print(" "+ (i+2));
	
}


	}

@Override
public int getSize() {
	return size;
}

@Override
public void putShip(AbstractShip ship, int x, int y) {	
	Orientation o = ship.getorientation();
	int dirx = 0;
	int diry = 0;
	int curx = x;
	int cury = y;
	if(o == Orientation.NORTH){
		if(y - ship.gettaille() < -1){		
			throw new IllegalArgumentException("Not possible... outside of the grid!");
		}
		diry=-1;
	}
	else if(o == Orientation.SOUTH){
		if(y + ship.gettaille() >= this.size ){		
			throw new IllegalArgumentException("Not possible... outside of the grid!");
		}
		diry=1;
	}
	else if(o == Orientation.EAST){
		if(x + ship.gettaille() >= this.size){
			throw new IllegalArgumentException("Not possible... outside of the grid!");
		}
		dirx=1;
	}
	else if(o == Orientation.WEST){
		if(x - ship.gettaille() < -1){
			throw new IllegalArgumentException("Not possible... outside of the grid!");
		}
		dirx=-1;
	}
	
	
	
	for(int i=0; i < ship.gettaille(); i++){			
		if(hasShip(curx, cury)){
			throw new IllegalArgumentException("Not possible... outside of the grid!");
		}
	}
	
	curx = x;
	cury = y;
	
	for(int i=0; i < ship.gettaille(); i++){			
		this.navires[curx][cury] = new StateShip(ship);
		curx = curx + dirx;
		cury = cury + diry;
	}
}

@Override
public boolean hasShip(int x, int y) {
	if(x > this.size || y > this.size){
		throw new IllegalArgumentException("Not possible... outside of the grid!"); 
	}
	if(this.navires[x][y] != null){
		return true;
	}
	else
		return false; 
}

@Override
public void setHit(boolean hit, int x, int y) {
	if(x > this.size || y > this.size){
		throw new IllegalArgumentException("Not possible... outside of the grid!"); 
	}
	this.frappes[x][y] = hit;
	
}

@Override
public Boolean getHit(int x, int y) {
	if(x > this.size || y > this.size){
		throw new IllegalArgumentException("Not possible... outside of the grid!"); 
	}
	return this.frappes[x][y];
}

@Override
public Hit sendHit(int x, int y) {
	if(navires[x][y] == null){ 
		return Hit.MISS;
		
	}
	else
	{

		if(navires[x][y].isStruck() == true){
				return Hit.DEJA;
		}
		else{
			navires[x][y].addStrike();
			if(navires[x][y].isSunk() == true){
				System.out.println(Hit.InttoHit(navires[x][y].getShip().gettaille()) + " coulé");
				return Hit.InttoHit(navires[x][y].getShip().gettaille());
			}
			return Hit.STRIKE;
		}
	}
}


}
