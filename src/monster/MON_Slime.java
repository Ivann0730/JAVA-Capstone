package monster;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import object.*;

import java.util.Random;

public class MON_Slime extends Entity {
    public MON_Slime(GamePanel gp) {
        super(gp);

        type = type_monster;
        name = "Slime";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack  = 1;
        defence = 0;
        exp = 5;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 28;
        solidArea.y = 48;
        solidArea.width = 64;
        solidArea.height = 64;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setUp("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        up2 = setUp("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        down1 = setUp("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        down2 = setUp("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        left1 = setUp("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        left2 = setUp("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
        right1 = setUp("/monster/greenslime_down_1",gp.tileSize,gp.tileSize);
        right2 = setUp("/monster/greenslime_down_2",gp.tileSize,gp.tileSize);
    }
    public void update(){
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if(!onPath && tileDistance < 5){
            int i = new Random().nextInt(100)+1;
            if(i > 50){
                onPath = true;
            }
        }
        if(onPath && tileDistance > 20){
            onPath = false;
        }
    }
    public void setAction(){
        if(onPath){
            //follow player
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(200)+1;
            if(i > 197 && !projectile.alive && shotAvailableCounter == 30){
                projectile.set(worldX,worldY,direction,true,this);
//                gp.projectileList.add(projectile);
                //CHECK VACANCY
                for(int ii = 0; ii < gp.projectile[1].length; ii++){
                    if(gp.projectile[gp.currentMap][ii] == null){
                        gp.projectile[gp.currentMap][ii] = projectile;
                        break;
                    }
                }
                shotAvailableCounter = 0;
            }
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
    public void damageReaction() {

        //RUNS AWAY FOR NOW (PASSIVE BEHAVIOR)
        actionLockCounter = 0;
//        direction = gp.player.direction;
        //new agro behavior
        onPath = true;
    }
    public void checkDrop(){
        //CAST A DIE
        int i = new Random().nextInt(100)+1;

        //SET THE MONSTER DROP
        if(i < 50){
            dropItem(new OBJ_Coin(gp));
            dropItem(new OBJ_Coin(gp));
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
