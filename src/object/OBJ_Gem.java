package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Gem extends Entity {
    GamePanel gp;
    public OBJ_Gem(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Gem";
        price = 200;
        value = 1;
        down1 = setUp("/objects/Emerald",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nA Gem with that can be sold\nto the merchant.";
        stackable = true;
    }
}
