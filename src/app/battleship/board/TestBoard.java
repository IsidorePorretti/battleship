package app.battleship.board;


import java.util.Arrays;
import java.util.Scanner;

import app.battleship.ship.*;
import app.battleship.ship.AbstractShip.Orientation;


public class TestBoard {
    public static void main(String args[]) {
        Board b = new Board("test");
		b.putHit(1, 2);
		b.print();

        
		boolean done = false;
		int i = 0;
		AbstractShip[] ships = {
				new Destroyer(), 
                new Submarine(), 
                new Submarine(), 
                new Battleship(), 
                new Carrier()
		};

		do {
			AbstractShip s = ships[i];	
			String msg = String.format("navire %d : # place le '%s' de taille (%d) en place, orienté %s", i + 1, s.getName(), s.getLength(), s.getOrientation());
			System.out.println(msg);
			ShipInput res = readShipInput();
			Orientation orientation = null;
			if (res.orientation.equals("n")) {
				orientation = Orientation.NORTH;
			} else if (res.orientation.equals("s")) {
				orientation = Orientation.SOUTH;
			} else if (res.orientation.equals("e")) {
				orientation = Orientation.EAST;
			} else if (res.orientation.equals("o")) {
				orientation = Orientation.WEST;
			}

			s.setOrientation(orientation);
			try {
				b.putShip(s, res.x, res.y);
			} catch(IllegalArgumentException e) {
				System.err.println("Impossible de placer le navire a cette position");
				i--;
			}
			b.print();
			++i;
			done = i == 5;

		} while (!done);
	}

	public static class ShipInput {
		public String orientation;
		public int x;
		public int y;
	}

    public static ShipInput readShipInput() {
		@SuppressWarnings("resource")
		Scanner sin = new Scanner(System.in);
		ShipInput res = new ShipInput();
		String[] validOrientations = {"n", "s", "e", "o"};
		boolean done = false;
		do {
			try {
				String[] in = sin.nextLine().toLowerCase().split(" ");
				if (in.length == 2) {
					String coord = in[0];
					if (Arrays.asList(validOrientations).contains(in[1])) {
						res.orientation = in[1];
						res.x = coord.charAt(0) - 'a';
						res.y = Integer.parseInt(coord.substring(1, coord.length())) - 1;
						done = true;
					}
				}
			} catch (Exception e) {
				// nop
			}

			if (!done) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A0 n'");
			}

		} while (!done && sin.hasNextLine());

		return res;
	}
}
