package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Heart extends Entity {
    public static final String objName = "Heart";
    public OBJ_Heart(GamePanel gp){
        super(gp);

        type = type_pickupOnly;
        value = 2;
        name = objName;
        down1 = setUp("/objects/heart_full",gp.tileSize,gp.tileSize);
        image = setUp("/objects/heart_full",gp.tileSize,gp.tileSize);
        image2 = setUp("/objects/heart_half",gp.tileSize,gp.tileSize);
        image3 = setUp("/objects/heart_blank",gp.tileSize,gp.tileSize);
    }
    public boolean use(Entity entity){
        gp.playSE(0);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
        return true;
    }
}
