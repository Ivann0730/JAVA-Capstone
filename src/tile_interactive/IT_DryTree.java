package tile_interactive;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class IT_DryTree extends InteractiveTile{

    GamePanel gp;
    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        this.solidArea.width = 120;
        this.solidArea.height = 120;
        this.solidAreaDefaultX = 0;
        this.solidAreaDefaultY = 0;
        down1 = setUp("/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }
    public boolean isCorrectItem(Entity entity){
        return entity.currentWeapon.type == type_axe;
    }
    public void playSE(){
        gp.playSE(8);
    }
    public InteractiveTile getDestroyForm(){
        InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize,worldY/gp.tileSize);
        return tile;
    }
}
