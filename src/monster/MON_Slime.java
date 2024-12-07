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
    public void setAction(){
        if(onPath){
            //CHECK IF IT STOPS CHASING
            checkStopChasingOrNot(gp.player, 10, 100);
            //follow player or search direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            //Check if it shoots a projectile
            checkShootOrNot(200, 30);
        } else{
            //check if starts chasing
            checkStartChasingOrNot(gp.player,5,100);
            // get a random direction
            getRandomDirection();
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
        if(i < 20){
            dropItem(new OBJ_Gem(gp));
        }
        if(i >= 20 && i < 50){
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
