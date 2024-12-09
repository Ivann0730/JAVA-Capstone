package Entity;

import DannyGermanSimulator.GamePanel;
import java.util.Random;

public class NPC_radish extends Entity{


    public NPC_radish(GamePanel gp) {
        super(gp);
        this.solidAreaDefaultX = 32;
        this.solidAreaDefaultY = 48;
        this.solidArea.width = 60;
        this.solidArea.height = 64;
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
            int goalCol = 39, goalRow = 157;
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
        dialogues[0][0] = "Hi I'm Gyatdish, nice to meet you 00-0000-001!";
        dialogues[0][1] = "Small but my GYATT is unbeatable!";
        dialogues[0][2] = "Yoooo, check out my GYATT!";
        dialogues[0][3] = "I know I know, im GYATTIFULL";
        dialogues[0][4] = "BOMBOGYATTT";

        dialogues[1][0] = "Take care out there!";
        dialogues[1][1] = "NEBA GIB UP!";
        dialogues[1][2] = "It's nice to be important, \nbut it's more important to be nice.!";
        dialogues[1][3] = "Stop counting the days and \nstart making the days count";
        dialogues[1][4] = "You wouldn't know pleasure if there wasn't pain,\nYou wouldn't know happiness if there wasn't sadness,\nYou wouldn't know what you have if you knew there \ncould be less";

        dialogues[2][0] = "But... why?!";
        dialogues[2][1] = "!@)(#(%!)@%!))!_@!$$$!";
    }

    // can only use two sprite IDK why
    public void getImage() {
        idleleft = setUp("/NPC/radish/running radish-5.png",gp.tileSize,gp.tileSize);
        idleright = setUp("/NPC/radish/running radish-11.png",gp.tileSize,gp.tileSize);
        idledown = setUp("/NPC/radish/running radish-2.png",gp.tileSize,gp.tileSize);
        idleup = setUp("/NPC/radish/running radish-8.png",gp.tileSize,gp.tileSize);
        up1 = setUp("/NPC/radish/running radish-7.png",gp.tileSize,gp.tileSize);
        up2 = setUp("/NPC/radish/running radish-9.png",gp.tileSize,gp.tileSize);
        up3 = setUp("/NPC/radish/running radish-9.png",gp.tileSize,gp.tileSize);
        up4 = setUp("/NPC/radish/running radish-8.png",gp.tileSize,gp.tileSize);
        down1 = setUp("/NPC/radish/running radish-1.png",gp.tileSize,gp.tileSize);
        down2 = setUp("/NPC/radish/running radish-3.png",gp.tileSize,gp.tileSize);
        down3 = setUp("/NPC/radish/running radish-3.png",gp.tileSize,gp.tileSize);
        down4 = setUp("/NPC/radish/running radish-2.png",gp.tileSize,gp.tileSize);
        left1 = setUp("/NPC/radish/running radish-4.png",gp.tileSize,gp.tileSize);
        left2 = setUp("/NPC/radish/running radish-6.png",gp.tileSize,gp.tileSize);
        left3 = setUp("/NPC/radish/running radish-6.png",gp.tileSize,gp.tileSize);
        left4 = setUp("/NPC/radish/running radish-5.png",gp.tileSize,gp.tileSize);
        right1 = setUp("/NPC/radish/running radish-10.png",gp.tileSize,gp.tileSize);
        right2 = setUp("/NPC/radish/running radish-12.png",gp.tileSize,gp.tileSize);
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
//        onPath = true;
//        gp.eHandler.teleportExpansion(2,186,190);
    }
}
