package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp) {
        super(gp);

        type = type_axe;
        name = "Woodcutter Axe";
        down1 = setUp("/objects/axe",gp.tileSize,gp.tileSize);
        attackValue = 2;
        attackArea.width = 32;
        attackArea.height = 32;
        description = "[" + name + "]\nA wooden Axe.";
    }
}