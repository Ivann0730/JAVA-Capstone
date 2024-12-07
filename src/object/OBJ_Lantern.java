package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Lantern extends Entity {
    public  OBJ_Lantern(GamePanel gp){
        super(gp);

        type = type_light;
        name = "Lantern";
        down1 = setUp("/objects/lantern",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nIlluminates your surroundings.";
        price = 200;
        lightRadius = 800;
    }
}
