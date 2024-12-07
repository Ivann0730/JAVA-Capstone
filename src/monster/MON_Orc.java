package monster;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import object.*;

import java.util.Random;

public class MON_Orc extends Entity {
    public MON_Orc(GamePanel gp) {
        super(gp);

        type = type_monster;
        name = "Orc";
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 20;
        life = maxLife;
        attack  = 8;
        defence = 10;
        exp = 30;
        knockBackPower = 5;

        solidArea.x = 28;
        solidArea.y = 15;
        solidArea.width = 64;
        solidArea.height = 100;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = gp.tileSize;
        attackArea.height = gp.tileSize;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }
    public void getImage(){
        up1 = setUp("/monster/orc_up_1",gp.tileSize,gp.tileSize);
        up2 = setUp("/monster/orc_up_2",gp.tileSize,gp.tileSize);
        down1 = setUp("/monster/orc_down_1",gp.tileSize,gp.tileSize);
        down2 = setUp("/monster/orc_down_2",gp.tileSize,gp.tileSize);
        left1 = setUp("/monster/orc_left_1",gp.tileSize,gp.tileSize);
        left2 = setUp("/monster/orc_left_2",gp.tileSize,gp.tileSize);
        right1 = setUp("/monster/orc_right_1",gp.tileSize,gp.tileSize);
        right2 = setUp("/monster/orc_right_2",gp.tileSize,gp.tileSize);
    }
    public void getAttackImage(){
        attackUp1 = setUp("/monster/orc_attack_up_1",gp.tileSize,gp.tileSize+64);
        attackUp2 = setUp("/monster/orc_attack_up_2",gp.tileSize,gp.tileSize+64);
        attackDown1 = setUp("/monster/orc_attack_down_1",gp.tileSize,gp.tileSize+64);
        attackDown2 = setUp("/monster/orc_attack_down_2",gp.tileSize,gp.tileSize+64);
        attackLeft1 = setUp("/monster/orc_attack_left_1",gp.tileSize+64,gp.tileSize);
        attackLeft2 = setUp("/monster/orc_attack_left_2",gp.tileSize+64,gp.tileSize);
        attackRight1 = setUp("/monster/orc_attack_right_1",gp.tileSize+64,gp.tileSize);
        attackRight2 = setUp("/monster/orc_attack_right_2",gp.tileSize+64,gp.tileSize);
    }
    public void setAction(){
        if(onPath){
            //CHECK IF IT STOPS CHASING
            checkStopChasingOrNot(gp.player, 15, 100);
            //follow player or search direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        } else{
            //check if starts chasing
            checkStartChasingOrNot(gp.player,3,100);
            // get a random direction
            getRandomDirection();
        }
        //check if it attacks
        if(!attacking){
            checkAttackOrNot(25, gp.tileSize*4,gp.tileSize);
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
        if(i < 40){
            dropItem(new OBJ_Gem(gp));
        }
        if(i >= 40 && i < 60){
            dropItem(new OBJ_Coin(gp));
            dropItem(new OBJ_Coin(gp));
            dropItem(new OBJ_Coin(gp));
        }
        if(i >= 60 && i < 80){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 80 && i < 100){
            dropItem(new OBJ_Mana(gp));
        }
    }
}
