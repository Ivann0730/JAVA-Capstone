package Entity;

import DannyGermanSimulator.GamePanel;
import java.util.Random;

public class NPC_radish extends Entity{


    public NPC_radish(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 3;

        getImage();
        setDialogue();
    }
    public void setAction(){
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
    public void setDialogue(){

        dialogues[0] = "Small but my GYATT is unbeatable!";
        dialogues[1] = "Yoooo, check out my GYATT!";
        dialogues[2] = "I know I know, im GYATTIFULL";
        dialogues[3] = "BOMBOGYATTT";
    }

    // can only use two sprite IDK why
    public void getImage() {
        idleleft = setUp("/NPC/radish/running radish-5.png");
        idleright = setUp("/NPC/radish/running radish-11.png");
        idledown = setUp("/NPC/radish/running radish-2.png");
        idleup = setUp("/NPC/radish/running radish-8.png");
        up1 = setUp("/NPC/radish/running radish-7.png");
        up2 = setUp("/NPC/radish/running radish-9.png");
        up3 = setUp("/NPC/radish/running radish-9.png");
        up4 = setUp("/NPC/radish/running radish-8.png");
        down1 = setUp("/NPC/radish/running radish-1.png");
        down2 = setUp("/NPC/radish/running radish-3.png");
        down3 = setUp("/NPC/radish/running radish-3.png");
        down4 = setUp("/NPC/radish/running radish-2.png");
        left1 = setUp("/NPC/radish/running radish-4.png");
        left2 = setUp("/NPC/radish/running radish-6.png");
        left3 = setUp("/NPC/radish/running radish-6.png");
        left4 = setUp("/NPC/radish/running radish-5.png");
        right1 = setUp("/NPC/radish/running radish-10.png");
        right2 = setUp("/NPC/radish/running radish-12.png");
        right3 = setUp("/NPC/radish/running radish-12.png");
        right4 = setUp("/NPC/radish/running radish-11.png");
    }
    public void speak(){
        super.speak();
    }
}
