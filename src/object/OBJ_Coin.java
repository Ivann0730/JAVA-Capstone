package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Coin extends Entity {
    public OBJ_Coin(GamePanel gp){
        super(gp);

        name = "Coin";
        down1 = setUp("/objects/coin_bronze.png",gp.tileSize,gp.tileSize);
    }
}
