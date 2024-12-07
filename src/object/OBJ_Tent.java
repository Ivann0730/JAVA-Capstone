package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Tent extends Entity {
    public  OBJ_Tent(GamePanel gp){
        super(gp);

        type = type_consumable;
        name = "Tent";
        down1 = setUp("/objects/tent",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nYou can sleep inside this.";
        price = 300;
        stackable = true;
    }
    public boolean use(Entity entity){
        gp.gameState = gp.sleepsState;
        gp.playSE(13);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
}
