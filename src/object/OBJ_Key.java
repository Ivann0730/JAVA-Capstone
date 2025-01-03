package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Key extends Entity {
    public static final String objName = "Key";
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;
        down1 = setUp("/objects/key",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nA key to open a room in GLE 999.";
        price = 100;
        stackable = true;
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "You used the " + name + " to open the door.";
        dialogues[1][0] = "Nothing to open.";
    }
    public boolean use(Entity entity){
        int objIndex = getDetected(entity, gp.obj, "Door");
        if(objIndex != 999){
            startDialogue(this,0);
            gp.playSE(0);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else {
            startDialogue(this,1);
            return false;
        }
    }
}
