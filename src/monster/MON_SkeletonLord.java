package monster;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import data.Progress;
import object.*;

import java.util.Random;

public class MON_SkeletonLord extends Entity {
    GamePanel gp;
    public static final String monName = "Skeleton Lord";
    public MON_SkeletonLord(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        boss = true;
        name = monName;
        defaultSpeed = 5;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack  = 10;
        defence = 2;
        exp = 50;
        knockBackPower = 20;
        sleep = true;

        solidArea.x = 90;
        solidArea.y = 15;
        solidArea.width = 64*2+32;
        solidArea.height = 350;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = gp.tileSize*2;
        attackArea.height = gp.tileSize*2;
        motion1_duration = 20;
        motion2_duration = 85;

        getImage();
        getAttackImage();
        setDialogue();
    }
    public void getImage(){

        int i = 3;
        if(!inRage){
            up1 = setUp("/monster/skeletonlord_up_1",gp.tileSize*i,gp.tileSize*i);
            up2 = setUp("/monster/skeletonlord_up_2",gp.tileSize*i,gp.tileSize*i);
            down1 = setUp("/monster/skeletonlord_down_1",gp.tileSize*i,gp.tileSize*i);
            down2 = setUp("/monster/skeletonlord_down_2",gp.tileSize*i,gp.tileSize*i);
            left1 = setUp("/monster/skeletonlord_left_1",gp.tileSize*i,gp.tileSize*i);
            left2 = setUp("/monster/skeletonlord_left_2",gp.tileSize*i,gp.tileSize*i);
            right1 = setUp("/monster/skeletonlord_right_1",gp.tileSize*i,gp.tileSize*i);
            right2 = setUp("/monster/skeletonlord_right_2",gp.tileSize*i,gp.tileSize*i);
        }
        if(inRage){
            up1 = setUp("/monster/skeletonlord_phase2_up-1",gp.tileSize*i,gp.tileSize*i);
            up2 = setUp("/monster/skeletonlord_phase2_up-",gp.tileSize*i,gp.tileSize*i);
            down1 = setUp("/monster/skeletonlord_phase2_down-1",gp.tileSize*i,gp.tileSize*i);
            down2 = setUp("/monster/skeletonlord_phase2_down-2",gp.tileSize*i,gp.tileSize*i);
            left1 = setUp("/monster/skeletonlord_phase2_left-1",gp.tileSize*i,gp.tileSize*i);
            left2 = setUp("/monster/skeletonlord_phase2_left-2",gp.tileSize*i,gp.tileSize*i);
            right1 = setUp("/monster/skeletonlord_phase2_right-1",gp.tileSize*i,gp.tileSize*i);
            right2 = setUp("/monster/skeletonlord_phase2_right-2",gp.tileSize*i,gp.tileSize*i);
        }
    }
    public void getAttackImage(){
        int i = 3;
        if(!inRage){
            attackUp1 = setUp("/monster/skeletonlord_attack_up_1",gp.tileSize*i,gp.tileSize*i*2);
            attackUp2 = setUp("/monster/skeletonlord_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
            attackDown1 = setUp("/monster/skeletonlord_attack_down_1",gp.tileSize*i,gp.tileSize*i*2);
            attackDown2 = setUp("/monster/skeletonlord_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
            attackLeft1 = setUp("/monster/skeletonlord_attack_left_1",gp.tileSize*i*2,gp.tileSize*i);
            attackLeft2 = setUp("/monster/skeletonlord_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
            attackRight1 = setUp("/monster/skeletonlord_attack_right_1",gp.tileSize*i*2,gp.tileSize*i);
            attackRight2 = setUp("/monster/skeletonlord_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
        }
        if(inRage){
            attackUp1 = setUp("/monster/skeletonlord_phase2_attack_up-1",gp.tileSize*i,gp.tileSize*i*2);
            attackUp2 = setUp("/monster/skeletonlord_phase2_attack_up-2",gp.tileSize*i,gp.tileSize*i*2);
            attackDown1 = setUp("/monster/skeletonlord_phase2_attack_down-1",gp.tileSize*i,gp.tileSize*i*2);
            attackDown2 = setUp("/monster/skeletonlord_phase2_attack_down-2",gp.tileSize*i,gp.tileSize*i*2);
            attackLeft1 = setUp("/monster/skeletonlord_phase2_attack_left-1",gp.tileSize*i*2,gp.tileSize*i);
            attackLeft2 = setUp("/monster/skeletonlord_phase2_attack_left-2",gp.tileSize*i*2,gp.tileSize*i);
            attackRight1 = setUp("/monster/skeletonlord_phase2_attack_right-1",gp.tileSize*i*2,gp.tileSize*i);
            attackRight2 = setUp("/monster/skeletonlord_phase2_attack_right-2",gp.tileSize*i*2,gp.tileSize*i);
        }
    }
    public void setDialogue(){
        dialogues[0][0] = "I am Ugangus Maximus!. The Assembly Lord!";
        dialogues[0][1] = "Prepare to be decompiled, hero!";
        dialogues[0][2] = "I’ll reduce your existence to nothing more than \nunoptimized assembly code—line by line";
    }
    public void setAction(){
        if(!inRage && life < maxLife/2){
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }
        //within 10 tiles
        if(getTileDistance(gp.player) < 10){
            moveTowardPlayer(60); //60 frames to check player positions
        } else {
            // get a random direction
            getRandomDirection(120);
        }
        //check if it attacks
        if(!attacking){
            checkAttackOrNot(60, gp.tileSize*7,gp.tileSize*5);
        }
    }
    public void damageReaction() {

        actionLockCounter = 0;
    }
    public void checkDrop(){

        gp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;
        //restore the previous music
        gp.stopMusic();
        gp.playMusic(18);
        //search temps and delete
        for(int i = 0; i < gp.obj[1].length; i++){
             if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)){
                 gp.playSE(21);
                 gp.obj[gp.currentMap][i] = null; i++;
                 gp.obj[gp.currentMap][i] = null; i++;
                 gp.obj[gp.currentMap][i] = null; i++;
             }
        }
        //CAST A DIE
        int i = new Random().nextInt(100)+1;

        //SET THE MONSTER DROP
        if(i > 1){
            dropItem(new OBJ_Gem(gp));
        }
//        if(i >= 40 && i < 60){
//            dropItem(new OBJ_Coin(gp));
//            dropItem(new OBJ_Coin(gp));
//            dropItem(new OBJ_Coin(gp));
//        }
//        if(i >= 60 && i < 80){
//            dropItem(new OBJ_Heart(gp));
//        }
//        if(i >= 80 && i < 100){
//            dropItem(new OBJ_Mana(gp));
//        }
    }
}
