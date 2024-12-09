package tile_interactive;

import DannyGermanSimulator.GamePanel;

public class IT_MetalPlate extends InteractiveTile{
    GamePanel gp;
    public static final String itName = "Metal Plate";
    public IT_MetalPlate(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        name = itName;
        down1 = setUp("/tiles_interactive/metalplate", gp.tileSize, gp.tileSize);
        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidArea.width = 0;
        this.solidArea.height = 0;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
    }
}
