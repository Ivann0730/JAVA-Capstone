package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Key extends Entity {
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Key";
        down1 = setUp("/objects/key",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nA key to open a room in GLE 999.";
        price = 100;
        stackable = true;
    }
    public boolean use(Entity entity){
        gp.gameState = gp.dialogueState;
        int objIndex = getDetected(entity, gp.obj, "Door");
        if(objIndex != 999){
            gp.ui.currentDialogue = "You used the " + name + " to open the door.";
            gp.playSE(0);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else {
            gp.ui.currentDialogue = "Nothing to open.";
            return false;
        }
    }
}
