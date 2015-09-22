package simpleroguelike.src

public class MobBlob extends Entity {
    public MobBlob(int newX, int newY) {
        pos.x = newX;
        pos.y = newY;
        symbol = 'o';
    }

    public int getXPos () {
        return pos.x;
    }

    public int getYPos () {
        return pos.y;
    }

    public char getSymbol () {
        return symbol;
    }

    public void setXPos (int newX) {
        pos.x = newX;
    }

    public void setYPos (int newY) {
        pos.y = newY;
    }
}
