package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Boots extends Entity {
    public OBJ_Boots(GamePanel gp){
        super(gp);

        name = "Boots";
        down1 = setUp("/objects/boots",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nA pair of boot, it's cool and normal.";
        price = 75;
    }
}
