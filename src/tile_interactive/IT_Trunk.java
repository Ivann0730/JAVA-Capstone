package tile_interactive;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class IT_Trunk extends InteractiveTile{
    GamePanel gp;
    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidArea.width = 0;
        this.solidArea.height = 0;
        this.solidAreaDefaultX = 0;
        this.solidAreaDefaultY = 0;
        down1 = setUp("/tiles_interactive/trunk", gp.tileSize, gp.tileSize);
    }
}
