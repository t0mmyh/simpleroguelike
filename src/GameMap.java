package simpleroguelike.src;

import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
import net.slashie.libjcsi.CSIColor;
import net.slashie.util.Util;
import net.slashie.util.Position;

public class GameMap {
    private char[][] charMap;
    private Entity[] entityList;
    private WSwingConsoleInterface csi;

    public GameMap (WSwingConsoleInterface newCSI,
                    int xMax, int yMax,
                    Entity[] newEntityList) {
        charMap = new char[xMax][yMax];
        csi = newCSI;
        entityList = newEntityList;
    }

    public void update () {
        for (int i = 0; i < csi.xdim; i++) {
            for (int j = 0; j < csi.ydim; j++) {
                csi.print(i, j, charMap[i][j], CSIColor.WHITE);
            }
        }
        for(Entity thing : entityList) {
            csi.print(thing.getXPos(), thing.getYPos(), thing.getSymbol());
        }
    }

    public void generateRandomWithBorder () {
        for (int i = 0; i < csi.xdim; i++) {
            for (int j = 0; j < csi.ydim; j++) {
                if (i == 0 || j == 0 || i == csi.xdim - 1 || j == csi.ydim -1 || Util.rand(0,3) == 1) {
                    charMap[i][j] = '\u2588';
                } else {
                    charMap[i][j] = ' ';
                }
            }
        }
    }

    public void generateBorder() {
        for (int i = 0; i < csi.xdim; i++) {
            for (int j = 0; j < csi.ydim; j++) {
                if (i == 0 || j == 0 || i == csi.xdim - 1 || j == csi.ydim -1) {
                    charMap[i][j] = '\u2588';
                } else {
                    charMap[i][j] = ' ';
                }
            }
        }
    }

    public void generateRandomRooms() {
        Position corner1 = new Position(Util.rand(1, csi.xdim - 2), Util.rand(1, csi.ydim - 2));
        Position corner2 = new Position(corner1.x, corner1.y);

        do {
            for(int i = 0; i < 10; i++){
                corner2 = new Position(corner1.x + Util.rand(5, csi.xdim/4),
                                   corner1.y + Util.rand(5, csi.ydim/4));
            System.out.printf("x1: %d, y1: %d\nx2: %d, y2: %d\n", corner1.x, corner1.y,
            corner2.x, corner2.y);
            }
            corner1 = new Position(Util.rand(1, csi.xdim - 2), Util.rand(1, csi.ydim - 2));
        } while ( corner2.x >= csi.xdim || corner2.y >= csi.ydim);

        for (int i = 0; i < csi.xdim; i++) {
            for (int j = 0; j < csi.ydim; j++) {
                if(    (i == corner1.x && (j >= corner1.y && j <= corner2.y))
                    || (i == corner2.x && (j >= corner1.y && j <= corner2.y))
                    || (j == corner1.y && (i >= corner1.x && i <= corner2.x))
                    || (j == corner2.y && (i >= corner1.x && i <= corner2.x))) {
                    charMap[i][j] = '\u2588';
                } else {
                    charMap[i][j] = ' ';
                }
            }
        }
    }
}
