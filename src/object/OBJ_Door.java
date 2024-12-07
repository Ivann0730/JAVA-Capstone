package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Door extends Entity{
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = "Door";
        down1 = setUp("/objects/Door",gp.tileSize,gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 120;
        solidArea.height = 120;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void interact(){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open this.";
    }
}
