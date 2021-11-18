package app.battleship;


import java.util.Arrays;
import java.util.List;

import app.battleship.board.Board;
import app.battleship.ship.AbstractShip;
import app.battleship.ship.BattleShipsAI;
import app.battleship.ship.Battleship;
import app.battleship.ship.Carrier;
import app.battleship.ship.Destroyer;
import app.battleship.ship.Submarine;

public class TestGame {
	private static void sleep(int ms){
		try{
			Thread.sleep(ms);	//the timeout is in milliseconds
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

public static void main(String[] args){
	
		
		Board board = new Board("GAME", 15);
 
		AbstractShip[] ships = new AbstractShip []{new Destroyer(),new Submarine(), new Submarine(), new Battleship(), new Carrier()};

		Game jeu = new Game();
		jeu.init();
		jeu.run();

		}

}
