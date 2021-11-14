package app.battleship.board;


import java.io.IOException;

public class Board {
    private int w; 
	private int h;
	private String name;
	private String[][] ships;
	private Boolean[][] hits; //frappes 
	

    
	public Board(String name, int w, int h) {
		this.w = w;
		this.h = h;
		this.name = name;
		
		this.ships = new String[w][h];
		this.hits= new Boolean[w][h];
	}
	
	public Board(String name) {
		this(name, 10, 10);
	}
	
	public void print() {
		Character currentLetter = 'A';
		Character currentLabel = '.';
		try {Runtime.getRuntime().exec("clear");} catch (IOException e) {}
		System.out.println(this.name);

		System.out.print("Navires :                 Frappes : \n   ");
		for (int i = 0; i < this.w; ++i) {
			System.out.print(currentLetter++ + " ");
		}
		
		currentLetter = 'A';
		System.out.print("      ");
		for (int i = 0; i < this.w; ++i) {
			System.out.print(currentLetter++ + " ");
		}
		
		System.out.println();

		for (int j = 0; j < this.h; ++j) {
			System.out.print(String.format("%2d ", j + 1));

			for (int i = 0; i < this.h; ++i) {
				System.out.print(currentLabel + " ");
			}
			System.out.print(" ");
			System.out.print(String.format("  %2d ", j + 1));
			for (int i = 0; i < this.h; ++i) {
				System.out.print(currentLabel + " ");
			}
			System.out.println();
		}
		
		currentLetter++;
	}
}
