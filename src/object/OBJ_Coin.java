package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Coin extends Entity {
    GamePanel gp;
    public OBJ_Coin(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Bronze Coin";
        value = 1;
        down1 = setUp("/objects/coin_bronze",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nA coin to use in Jude Maranga's \npiso net in CIT-U Universe.";
    }
    public boolean use(Entity entity){
        gp.playSE(0);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coins += value;
        return true;
    }
}
