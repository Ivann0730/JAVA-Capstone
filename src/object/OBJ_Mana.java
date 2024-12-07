package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Mana extends Entity {
    GamePanel gp;
    public OBJ_Mana(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 1;
        name = "Mana Crystal";
        down1 = setUp("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
        image = setUp("/objects/manacrystal_full",gp.tileSize,gp.tileSize);
        image2 = setUp("/objects/manacrystal_blank",gp.tileSize,gp.tileSize);
        collision = true;
        description = "[" + name + "]\nA mystic crystal filled\nwith codechum essence.";
    }
    public boolean use(Entity entity){
        gp.playSE(0);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
        return true;
    }
}
