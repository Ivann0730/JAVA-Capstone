package monster;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import object.*;

import java.util.Random;

public class MON_Bat extends Entity {
    public MON_Bat(GamePanel gp) {
        super(gp);

        type = type_monster;
        name = "Bat";
        defaultSpeed = 7;
        speed = defaultSpeed;
        maxLife = 3;
        life = maxLife;
        attack  = 1;
        defence = 0;
        exp = 2;
        projectile = new OBJ_Fireball(gp);

        solidArea.x = 38;
        solidArea.y = 40;
        solidArea.width = 52;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setUp("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        up2 = setUp("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        down1 = setUp("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        down2 = setUp("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        left1 = setUp("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        left2 = setUp("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        right1 = setUp("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        right2 = setUp("/monster/bat_down_2",gp.tileSize,gp.tileSize);
    }

    public void setAction(){
        if(onPath){
            //CHECK IF IT STOPS CHASING
//            checkStopChasingOrNot(gp.player, 10, 100);
//            //follow player or search direction to go
//            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
//            //Check if it shoots a projectile
//            checkShootOrNot(200, 30);
        } else{
            //check if starts chasing
//            checkStartChasingOrNot(gp.player,5,100);
            // get a random direction
            getRandomDirection(10);
        }
    }
    public void damageReaction() {

        //RUNS AWAY FOR NOW (PASSIVE BEHAVIOR)
        actionLockCounter = 0;
//        direction = gp.player.direction;
        //new agro behavior
        onPath = false;
    }
    public void checkDrop(){
        //CAST A DIE
        int i = new Random().nextInt(100)+1;

        //SET THE MONSTER DROP
        //SET THE MONSTER DROP
        if(i < 25){
            dropItem(new OBJ_Gem(gp));
        }
        if(i >= 25 && i < 50){
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
