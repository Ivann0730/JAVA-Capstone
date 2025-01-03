package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Shield_Blue extends Entity {
    public static final String objName = "Blue Shield";
    public OBJ_Shield_Blue(GamePanel gp){
        super(gp);

        type = type_shield;
        name = objName;
        down1 = setUp("/objects/shield_blue",gp.tileSize,gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shield imbued with\ncodechum essence.";
        price = 250;
    }
}
