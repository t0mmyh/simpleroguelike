package simpleroguelike.src;

public class Projectile extends Entity {
    public Projectile (int xPos, int yPos) {
        pos.x = xPos;
        pos.y = yPos;
    }

    public void updateLocation() {
        this.setXPos(this.getXPos() + 1);
    }
}
