package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Chest extends Entity {
    GamePanel gp;
    public OBJ_Chest(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Chest";
        image = setUp("/objects/chest",gp.tileSize,gp.tileSize);
        image2 = setUp("/objects/chest_opened",gp.tileSize,gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 120;
        solidArea.height = 120;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void setLoot(Entity loot){
        this.loot = loot;
    }
    public void interact(){
        gp.gameState = gp.dialogueState;
        if(!opened){
            gp.playSE(0);
            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and found a " + loot.name + "!");

        if(!gp.player.canObtainItem(loot)){
                sb.append("\n...But you don't have enough inventory space.");
            } else {
                sb.append("\nYou Obtained the " + loot.name + "!");
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        }
        else {
            gp.ui.currentDialogue = "It's Empty.";
        }
    }
}
