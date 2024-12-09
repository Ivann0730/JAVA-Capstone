package monster;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import object.*;

import java.util.Random;

public class MON_Orc extends Entity {
    GamePanel gp;
    public MON_Orc(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Orc";
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 20;
        life = maxLife;
        attack  = 8;
        defence = 5;
        exp = 30;
        knockBackPower = 5;

        solidArea.x = 28;
        solidArea.y = 15;
        solidArea.width = 64;
        solidArea.height = 100;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 100;
        attackArea.height = 100;
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
        attackUp1 = setUp("/monster/orc_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 = setUp("/monster/orc_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackDown1 = setUp("/monster/orc_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 = setUp("/monster/orc_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setUp("/monster/orc_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setUp("/monster/orc_attack_left_2",gp.tileSize*2,gp.tileSize);
        attackRight1 = setUp("/monster/orc_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight2 = setUp("/monster/orc_attack_right_2",gp.tileSize*2,gp.tileSize);
    }
    public void setAction(){
        if(onPath){
            //CHECK IF IT STOPS CHASING
            checkStopChasingOrNot(gp.player, 15, 100);
            //follow player or search direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        } else{
            //check if starts chasing
            checkStartChasingOrNot(gp.player,5,100);
            // get a random direction
            getRandomDirection(120);
        }
        //check if it attacks
        if(!attacking){
            checkAttackOrNot(25, gp.tileSize*2,gp.tileSize);
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
