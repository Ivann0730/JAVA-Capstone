package Entity;

import DannyGermanSimulator.GamePanel;
import object.*;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePanel gp) {
        super(gp);
        this.solidAreaDefaultX = 32;
        this.solidAreaDefaultY = 48;
        this.solidArea.width = 64;
        this.solidArea.height = 64;
        direction = "down";
        speed = 3;

        getImage();
        setDialogue();
        setItems();
    }

    public void setDialogue(){

        dialogues[0] = "Hello I am Noob Master";
        dialogues[1] = "Check out what I have in Store";
        dialogues[2] = "I have Jude Maranga's keyboard";
        dialogues[3] = "I am from Codechumus!";
    }

    // can only use two sprite IDK why
    public void getImage() {
        up1 = setUp("/NPC/NOOB MASTER",gp.tileSize,gp.tileSize);
        up2 = setUp("/NPC/NOOB MASTER",gp.tileSize,gp.tileSize);
        down1 = setUp("/NPC/NOOB MASTER",gp.tileSize,gp.tileSize);
        down2 = setUp("/NPC/NOOB MASTER",gp.tileSize,gp.tileSize);
        left1 = setUp("/NPC/NOOB MASTER",gp.tileSize,gp.tileSize);
        left2 = setUp("/NPC/NOOB MASTER",gp.tileSize,gp.tileSize);
        right1 = setUp("/NPC/NOOB MASTER",gp.tileSize,gp.tileSize);
        right2 = setUp("/NPC/NOOB MASTER",gp.tileSize,gp.tileSize);
    }
    public void setItems(){
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Boots(gp));
    }
    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}