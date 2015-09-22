package simpleroguelike.src;

import net.slashie.util.Position;

public abstract class Entity {
    protected Position pos = new Position(0,0);
    protected char symbol = ' ';

    public int getXPos () {
        return pos.x;
    }

    public int getYPos () {
        return pos.y;
    }

    public void setXPos (int newX) {
        pos.x = newX;
    }

    public void setYPos (int newY) {
        pos.y = newY;
    }
}
