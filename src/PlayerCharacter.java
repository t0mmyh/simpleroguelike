package simpleroguelike.src;

public class PlayerCharacter extends Entity {
    public boolean isPlaying;

    public PlayerCharacter(int xPos, int yPos) {
        pos.x = xPos;
        pos.y = yPos;
        isPlaying = true;
        symbol = '@';
    }

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
