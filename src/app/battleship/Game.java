package app.battleship;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import app.battleship.board.Board;
import app.battleship.ship.AbstractShip;
import app.battleship.ship.BattleShipsAI;
import app.battleship.ship.Battleship;
import app.battleship.ship.Carrier;
import app.battleship.ship.ColorUtil;
import app.battleship.ship.Destroyer;
import app.battleship.ship.Submarine;

public class Game {


    public static final File SAVE_FILE = new File("savegame.dat");


    private Player player1;
    private AIPlayer player2;
    private Scanner sin;

    /*
     * *** Constructeurs
     */
    public Game() {
    }

    public Game init() {
            System.out.println("entre ton nom:");

            Scanner keyboard = new Scanner(System.in);
            
            String nom = keyboard.nextLine();
            System.out.println("entre la taille:");
            int size = keyboard.nextInt();
            //  init boards
            Board b1 = new Board(nom, size);
            Board b2 = new Board("AIPlayer", size);

            this.player1 = new Player(b1, b2, createDefaultShips());
            this.player2 = new AIPlayer(b2, b1, createDefaultShips());
            b1.print();
            player1.putShips();
            player2.AiPutShip();
        return this;
    }

    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Board b2 = player2.board;
        Hit hit;
        Scanner keyboard = new Scanner(System.in);

        // main loop
        b1.print();
        boolean done;
        do {
            hit = player1.sendHit(coords); 
            boolean strike = (hit.getValue() >0);  
            
            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(strike, coords, hit));

            //save();

            if (!done && !strike) {
                do {
                    hit = player2.AiSendHit(coords); 
                    
                    strike = (hit.getValue() >0);
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(strike, coords, hit));
                    done = updateScore();

                    if (!done) {
                        //save
                    }
                } while (strike && !done);
            }

        } while (!done);

        //save

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.issunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;

    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }


    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new Battleship(),
                new Carrier() });
    }
    public static void main(String args[]) {
        new Game().init().run();
    }

}
