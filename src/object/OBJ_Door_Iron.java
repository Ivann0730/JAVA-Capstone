package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Door_Iron extends Entity {
    public static final String objName = "Iron Door";
    GamePanel gp;
    public OBJ_Door_Iron(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = objName;
        down1 = setUp("/objects/door_iron",gp.tileSize,gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 120;
        solidArea.height = 120;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "It won't open";
    }
    public void interact(){
        startDialogue(this,0);
    }
}
