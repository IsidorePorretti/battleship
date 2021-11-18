package app.battleship.board;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import app.battleship.Hit;
import app.battleship.Player;
import app.battleship.board.Board;
import app.battleship.ship.AbstractShip;
import app.battleship.ship.Battleship;
import app.battleship.ship.Carrier;
import app.battleship.ship.Destroyer;
import app.battleship.ship.Submarine;
import app.battleship.ship.AbstractShip.Orientation;

public class TestBoard {
	public static void main(String[] args){

    try {Runtime.getRuntime().exec("clear");} catch (IOException e) {}
		int[] coords = {2,2};
		Board test = new Board("Mon tableau", 10);
		Board opp = new Board("Tableau adverse",10);
		List<AbstractShip> ships = Arrays.asList(new Destroyer(),new Submarine(), new Submarine(), new Battleship(), new Carrier());
		Player player = new Player(opp , test , ships);
		test.putShip(new Submarine(Orientation.SOUTH), (2)-1, (2)-1);
		opp.putShip(new Submarine(Orientation.SOUTH), (5)-1, (5)-1);
		Hit test_hit = player.sendHit(coords);
		test.print();
		opp.print();
		System.out.println(test_hit.toString());

		
	}
	
	
}
