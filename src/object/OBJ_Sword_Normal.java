package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Sword_Normal extends Entity {
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);

        type = type_sword;
        name = "Normal Sword";
        down1 = setUp("/objects/sword_normal",gp.tileSize,gp.tileSize);
        attackValue = 1;;
        attackArea.width = 48;
        attackArea.height = 48;
        description = "[" + name + "]\nAn old sword.";
        price = 20;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
