package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Mana extends Entity {
    public OBJ_Mana(GamePanel gp){
        super(gp);

        name = "Mana Crystal";
        down1 = setUp("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
        collision = true;
        description = "[" + name + "]\nA mystic crystal filled\nwith codechum essence.";

        solidArea.x = 36;
        solidArea.y = 36;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
