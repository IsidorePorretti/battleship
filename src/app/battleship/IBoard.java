package app.battleship;


import app.battleship.Hit;
import app.battleship.ship.AbstractShip;

public interface IBoard { 

 
    int getSize();

    void putShip(AbstractShip ship, int x, int y);

    boolean hasShip(int x, int y);

    void setHit(boolean hit, int x, int y);

    Boolean getHit(int x, int y);

    Hit sendHit(int x, int y);
}
