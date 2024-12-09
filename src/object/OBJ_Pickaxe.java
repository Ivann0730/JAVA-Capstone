package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Pickaxe extends Entity {
    public static final String objName = "Pickaxe";
    public OBJ_Pickaxe(GamePanel gp) {
        super(gp);

        type = type_pickaxe;
        name = objName;
        down1 = setUp("/objects/pickaxe",gp.tileSize,gp.tileSize);
        attackValue = 1;
        attackArea.width = 32;
        attackArea.height = 32;
        description = "[" + name + "]\nA Pickaxe for digging.";
        price = 75;
        knockBackPower = 10;
        motion1_duration = 10;
        motion2_duration = 20;
    }
}
