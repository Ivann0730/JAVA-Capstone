package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Key extends Entity {
    public OBJ_Key(GamePanel gp){
        super(gp);

        name = "Mana";
        down1 = setUp("/objects/manacrystal_full");
        collision = true;

        solidArea.x = 36;
        solidArea.y = 36;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
