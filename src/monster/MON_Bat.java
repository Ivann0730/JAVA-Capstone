package monster;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import java.util.Random;

public class MON_Bat extends Entity {

    public MON_Bat(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Eye Bat";
        speed = 3;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 34;
        solidArea.y = 48;
        solidArea.width = 44;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setUp("/monster/New Piskel-1.png (1)",gp.tileSize,gp.tileSize);
        up2 = setUp("/monster/New Piskel-2.png (1)",gp.tileSize,gp.tileSize);
        down1 = setUp("/monster/New Piskel-1.png (1)",gp.tileSize,gp.tileSize);
        down2 = setUp("/monster/New Piskel-2.png (1)",gp.tileSize,gp.tileSize);
        left1 = setUp("/monster/New Piskel-1.png (1)",gp.tileSize,gp.tileSize);
        left2 = setUp("/monster/New Piskel-2.png (1)",gp.tileSize,gp.tileSize);
        right1 = setUp("/monster/New Piskel-1.png (1)",gp.tileSize,gp.tileSize);
        right2 = setUp("/monster/New Piskel-2.png (1)",gp.tileSize,gp.tileSize);
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

    public void damageReaction() {

        //RUNS AWAY FOR NOW (PASSIVE BEHAVIOR)
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
