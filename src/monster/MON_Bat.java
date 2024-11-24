package monster;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import object.OBJ_Coin;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_Mana;

import java.util.Random;

public class MON_Bat extends Entity {

    public MON_Bat(GamePanel gp) {
        super(gp);

        type = type_monster;
        name = "Eye Bat";
        speed = 3;
        maxLife = 4;
        life = maxLife;
        attack  = 5;
        defence = 0;
        exp = 2;
        projectile = new OBJ_Fireball(gp);

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
        int i = new Random().nextInt(100)+1;
        if(i > 99 && !projectile.alive && shotAvailableCounter == 30){
            projectile.set(worldX,worldY,direction,true,this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    public void damageReaction() {

        //RUNS AWAY FOR NOW (PASSIVE BEHAVIOR)
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
    public void checkDrop(){
        //CAST A DIE
        int i = new Random().nextInt(100)+1;

        //SET THE MONSTER DROP
        if(i < 50){
            dropItem(new OBJ_Coin(gp));
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_Mana(gp));
        }
    }
}
