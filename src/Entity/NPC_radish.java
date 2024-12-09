package Entity;

import DannyGermanSimulator.GamePanel;
import java.util.Random;

public class NPC_radish extends Entity{

    public NPC_radish(GamePanel gp) {
        super(gp);
        this.solidAreaDefaultX = 90;
        this.solidAreaDefaultY = 100;
        this.solidArea.width = 60;
        this.solidArea.height = 90;
        direction = "down";
        speed = 5;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }
    public void setAction(){
        if(onPath){
            //change to follow player
//            int goalCol = 23, goalRow = 15;
            int goalCol = 33, goalRow = 102;
            //follow player
//            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
//            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol, goalRow);
        } else {
            actionLockCounter++;
            if(actionLockCounter == 120){
                Random random = new Random();
                int i = random.nextInt(100)+1;

                if(i <= 25){
                    direction = "up";
                }
                if(i > 25 && i <= 50){
                    direction = "down";
                }
                if(i > 50 && i <= 75){
                    direction = "left";
                }
                if(i > 75){
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }
    public void setDialogue(){
        dialogues[0][0] = "Hi I'm Gyattdish, nice to meet you 00-0000-001!";
        dialogues[0][1] = "Welcome to Danny German!";
        dialogues[0][2] = "You need to earn coins to survive and buy items.";
        dialogues[0][3] = "There is a merchant on the right-most side of the map.";
        dialogues[0][4] = "Keep an eye out for Chest around the map!";
        dialogues[0][5] = "Also watch out for Slimes!";

        dialogues[1][0] = "Take care out there!";
        dialogues[1][1] = "NEBA GIB UP!";
        dialogues[1][2] = "It's nice to be important, \nbut it's more important to be nice.!";
        dialogues[1][3] = "Stop counting the days and \nstart making the days count";
        dialogues[1][4] = "You wouldn't know pleasure if there wasn't pain,\nYou wouldn't know happiness if there wasn't sadness,\nYou wouldn't know what you have if you knew there \ncould be less";

        dialogues[2][0] = "How's your day everyone?";
        dialogues[2][1] = "Cool and Normal!";
        dialogues[2][2] = "Ayg lingi sa kilid!";
    }

    // can only use two sprite IDK why
    public void getImage() {
        idleleft = setUp("/NPC/radish/running radish-5.png",gp.tileSize*2,gp.tileSize*2);
        idleright = setUp("/NPC/radish/running radish-11.png",gp.tileSize*2,gp.tileSize*2);
        idledown = setUp("/NPC/radish/running radish-2.png",gp.tileSize,gp.tileSize);
        idleup = setUp("/NPC/radish/running radish-8.png",gp.tileSize,gp.tileSize);
        up1 = setUp("/NPC/radish/running radish-7.png",gp.tileSize*2,gp.tileSize*2);
        up2 = setUp("/NPC/radish/running radish-9.png",gp.tileSize*2,gp.tileSize*2);
        up3 = setUp("/NPC/radish/running radish-9.png",gp.tileSize,gp.tileSize);
        up4 = setUp("/NPC/radish/running radish-8.png",gp.tileSize,gp.tileSize);
        down1 = setUp("/NPC/radish/running radish-1.png",gp.tileSize*2,gp.tileSize*2);
        down2 = setUp("/NPC/radish/running radish-3.png",gp.tileSize*2,gp.tileSize*2);
        down3 = setUp("/NPC/radish/running radish-3.png",gp.tileSize,gp.tileSize);
        down4 = setUp("/NPC/radish/running radish-2.png",gp.tileSize,gp.tileSize);
        left1 = setUp("/NPC/radish/running radish-4.png",gp.tileSize*2,gp.tileSize*2);
        left2 = setUp("/NPC/radish/running radish-6.png",gp.tileSize*2,gp.tileSize*2);
        left3 = setUp("/NPC/radish/running radish-6.png",gp.tileSize,gp.tileSize);
        left4 = setUp("/NPC/radish/running radish-5.png",gp.tileSize,gp.tileSize);
        right1 = setUp("/NPC/radish/running radish-10.png",gp.tileSize*2,gp.tileSize*2);
        right2 = setUp("/NPC/radish/running radish-12.png",gp.tileSize*2,gp.tileSize*2);
        right3 = setUp("/NPC/radish/running radish-12.png",gp.tileSize,gp.tileSize);
        right4 = setUp("/NPC/radish/running radish-11.png",gp.tileSize,gp.tileSize);
    }
    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        if(dialogues[dialogueSet][0] == null){
            dialogueSet = 0;
            //to set the last dialogue set
//            dialogueSet--;
        }
//        if(gp.player.life < gp.player.maxLife/3){
//            dialogueSet = 1;
//        }
        onPath = true;
//        gp.eHandler.teleportExpansion(2,186,190);
    }
}
