package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Lantern extends Entity {
    public static final String objName = "Lantern";
    public  OBJ_Lantern(GamePanel gp){
        super(gp);

        type = type_light;
        name = objName;
        down1 = setUp("/objects/lantern",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nIlluminates your surroundings.";
        price = 200;
        lightRadius = 900;
    }
}
