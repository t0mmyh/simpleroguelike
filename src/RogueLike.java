package simpleroguelike.src;

import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.util.Util;
import java.util.Properties;
import java.awt.event.KeyEvent;

public class RogueLike{
    public static void main(String[] args){
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 12;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        WSwingConsoleInterface csi = initCSI();
        Keyboard keyboard = new Keyboard();
        csi.addKeyListener(keyboard);
        PlayerCharacter player = new PlayerCharacter(10, 20);
        int curXPos;
        int curYPos;

        GameMap map = new GameMap(csi, csi.xdim, csi.ydim);
        map.generateRandomWithBorder();

        Projectile p = new Projectile(1,1);
        while(player.isPlaying){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            csi.cls();
            map.update();

            csi.print(player.getXPos(),player.getYPos(), '@', CSIColor.YELLOW);
            csi.print(p.getXPos(), p.getYPos(), '.', CSIColor.RED);


            curXPos = player.getXPos();
            curYPos = player.getYPos();
            keyboard.update();
            getInput(keyboard, player, csi, map);
            csi.refresh();

            //p.updateLocation();

            try{
                Thread.sleep( (lastLoopTime - System.nanoTime() + OPTIMAL_TIME)/1000000);
            } catch (InterruptedException ie){}
        }
        return;
    }

    public static WSwingConsoleInterface initCSI() {
        Properties text = new Properties();
        text.setProperty("fontSize", "20");
        text.setProperty("font", "Classic Console");

        WSwingConsoleInterface csi = null;
        try {
            csi = new WSwingConsoleInterface("Space 31337", text);
        }
        catch (ExceptionInInitializerError eiie) {
            System.out.println("*** Error: Swing Console Box cannot be initialized. Exiting...");
            eiie.printStackTrace();
            System.exit(-1);
        }

        return csi;
    }

    public static void getInput(    Keyboard keyboard,
                                    PlayerCharacter player,
                                    WSwingConsoleInterface csi,
                                    GameMap map) {

        int curXPos = player.getXPos();
        int curYPos = player.getYPos();

        if (keyboard.isKeyPressed(KeyEvent.VK_UP) && (player.getYPos()-1 >= 0)) {
            if (csi.peekChar(curXPos, curYPos-1) == ' ') {
                player.setYPos(--curYPos);
            }
        } else if (keyboard.isKeyPressed(KeyEvent.VK_DOWN) && (curYPos+1 < csi.ydim)) {
            if (csi.peekChar(curXPos, curYPos+1) == ' ') {
                player.setYPos(++curYPos);
            }
        } else if (keyboard.isKeyPressed(KeyEvent.VK_LEFT) && (curXPos - 1 >= 0)) {
            if (csi.peekChar(curXPos-1, curYPos) == ' ') {
                player.setXPos(--curXPos);
            }
        } else if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && (curXPos+1 < csi.xdim)) {
            if (csi.peekChar(curXPos+1, curYPos) == ' ') {
                player.setXPos(++curXPos);
            }
        } else if (keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
            map.generateRandomWithBorder();
        }
    }

}
