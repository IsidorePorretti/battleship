package app.battleship.ship;

import app.battleship.ship.ColorUtil;

public class StateShip {
	
	private AbstractShip ship;		
	boolean struck;			
	
	public StateShip(AbstractShip ship){	
		this.ship = ship;
		this.struck = false;
	}
	
	public void addStrike(){			
		if(this.ship.getstrikeCount() >= this.ship.gettaille()){	
			throw new IllegalArgumentException("The number of hits is already maximum");
		}
		this.struck = true;
		this.ship.addstrike();
	}
	
	public boolean isStruck(){				
		return this.struck;
	}
	
	public String toString(){				
		if(this.struck == true){
			return ColorUtil.colorize(this.ship.getlabel(), ColorUtil.Color.RED);	
		}
		else {
			return ColorUtil.colorize(this.ship.getlabel(), ColorUtil.Color.RESET);	
		}
		
	}
	
	public boolean isSunk(){				
		return this.ship.issunk();
	}
	
	public AbstractShip getShip(){			
		return this.ship;
	}
}
