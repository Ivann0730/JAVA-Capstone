package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Chest extends Entity {
    GamePanel gp;
    public static final String objName = "Chest";
    public OBJ_Chest(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = objName;
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
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "You opened the chest and found a " + loot.name + "!\n...But you don't have enough inventory space.";
        dialogues[1][0] = "You opened the chest and found a " + loot.name + "!\nYou Obtained the " + loot.name + "!";
        dialogues[2][0] = "It's Empty.";
    }
    public void interact(){
        if(!opened){
            gp.playSE(0);

        if(!gp.player.canObtainItem(loot)){
                startDialogue(this,0);
            } else {
                startDialogue(this,1);
                down1 = image2;
                opened = true;
            }
        }
        else {
            startDialogue(this,2);
        }
    }
}
