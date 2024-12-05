package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Door extends Entity{
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name = "Door";
        down1 = setUp("/objects/door",gp.tileSize,gp.tileSize);
        collision = true;
    }
}
