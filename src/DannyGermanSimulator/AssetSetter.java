package DannyGermanSimulator;

import object.OBJ_Coin;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 36 * gp.tileSize;
        gp.obj[0].worldY = 5 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 38 * gp.tileSize;
        gp.obj[1].worldY = 27 * gp.tileSize;

        gp.obj[2] = new OBJ_Coin();
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 13 * gp.tileSize;

        gp.obj[3] = new OBJ_Coin();
        gp.obj[3].worldX = 37 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[4] = new OBJ_Coin();
        gp.obj[4].worldX = 37 * gp.tileSize;
        gp.obj[4].worldY = 13 * gp.tileSize;

        gp.obj[5] = new OBJ_Coin();
        gp.obj[5].worldX = 38 * gp.tileSize;
        gp.obj[5].worldY = 12 * gp.tileSize;
    }
}
