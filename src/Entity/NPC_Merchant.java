package Entity;

import DannyGermanSimulator.GamePanel;
import object.*;

public class NPC_Merchant extends Entity{

    public static final String npcName = "Merchant";
    public NPC_Merchant(GamePanel gp) {
        super(gp);
        this.solidAreaDefaultX = 32;
        this.solidAreaDefaultY = 48;
        this.solidArea.width = 64;
        this.solidArea.height = 64;
        direction = "down";
        speed = 3;
        name = npcName;
        getImage();
        setDialogue();
        setItems();
    }

    public void setDialogue(){
        dialogues[0][0] = "Hello I am Noob Master";
        dialogues[0][1] = "Check out what I have in Store";
        dialogues[0][2] = "I have Jude Maranga's keyboard!";
        dialogues[0][3] = "I am from Codechumus!";
        dialogues[0][4] = "Niggas in Paris!";

        dialogues[1][0] = "Come again hehe.";

        dialogues[2][0] = "You need more coins to buy this item.";

        dialogues[3][0] = "Your Inventory is Full!";

        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    // can only use two sprite IDK why
    public void getImage() {
        up1 = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        up2 = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        down1 = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        down2 = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        left1 = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        left2 = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        right1 = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        right2 = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
    }
    public void setItems(){
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Pickaxe(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Tent(gp));
        inventory.add(new OBJ_Lantern(gp));
    }
    public void speak(){
        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
