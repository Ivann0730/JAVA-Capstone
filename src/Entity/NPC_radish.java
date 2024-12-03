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
        speed = 3;

        getImage();
        setDialogue();
    }
    public void setAction(){
        if(onPath){
            //change to follow player
            int goalCol = 23, goalRow = 15;
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
        int i = 0;
        dialogues[i] = "Hi I'm Gyatdish, nice to meet you 00-0000-001!"; i++;
        dialogues[i] = "Small but my GYATT is unbeatable!"; i++;
        dialogues[i] = "Yoooo, check out my GYATT!"; i++;
        dialogues[i] = "I know I know, im GYATTIFULL"; i++;
        dialogues[i] = "BOMBOGYATTT"; i++;
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
        super.speak();
        onPath = true;
    }
}
