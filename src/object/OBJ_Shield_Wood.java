package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gp){
        super(gp);

        type = type_shield;
        name = "Wooden Shield";
        down1 = setUp("/objects/shield_wood",gp.tileSize,gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nA wooden shield.";
    }
}
