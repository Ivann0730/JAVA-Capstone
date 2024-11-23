package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Sword_Normal extends Entity {
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);

        type = type_sword;
        name = "Normal Sword";
        down1 = setUp("/objects/sword_normal",gp.tileSize,gp.tileSize);
        attackValue = 1;
        attackArea.width = 64;
        attackArea.height = 48;
        description = "[" + name + "]\nAn old sword.";
    }
}
