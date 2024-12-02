package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Key extends Entity {
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        super(gp);

        type = type_pickupOnly;
        value = 1;
        name = "Key";
        down1 = setUp("/objects/key",gp.tileSize,gp.tileSize);
        image = setUp("/objects/key",gp.tileSize,gp.tileSize);
        image2 = setUp("/objects/key",gp.tileSize,gp.tileSize);
        collision = true;
        description = "[" + name + "]\nA key to open a room in GLE 999.";
        price = 100;

        solidArea.x = 36;
        solidArea.y = 36;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void use(Entity entity){
        gp.playSE(4);
        gp.ui.addMessage("Key +" + value);
    }
}
