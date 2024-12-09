package Entity;

import DannyGermanSimulator.GamePanel;
import object.OBJ_Door_Iron;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

import java.awt.*;
import java.util.ArrayList;

public class NPC_BigRock extends  Entity{
    public static final String npcName = "Big Rock";
    public NPC_BigRock(GamePanel gp) {
        super(gp);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 24;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 105;
        solidArea.height = 88;
        direction = "down";
        speed = 4;
        name = npcName;

        dialogueSet = -1;

        getImage();
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "It's a Giant rock";
    }

    // can only use two sprite IDK why
    public void getImage() {
        idleleft = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        idleright = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        idledown = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        idleup = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        up1 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        up2 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        up3 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        up4 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        down1 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        down2 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        down3 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        down4 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        left1 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        left2 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        left3 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        left4 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        right1 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        right2 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        right3 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
        right4 = setUp("/NPC/bigrock",gp.tileSize,gp.tileSize);
    }
    public void update(){

    }
    public void setAction(){

    }
    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        if(dialogues[dialogueSet][0] == null){
            dialogueSet = 0;
        }
        detectPlate();
    }
    public void move(String d){
        this.direction = d;
        checkCollision();
        if(!collisionOn){
            switch (direction){
                case "up": worldY -= speed;break;
                case "down": worldY += speed;break;
                case "left": worldX -= speed;break;
                case "right": worldX += speed;break;
            }
        }
        detectPlate();
    }
    public void detectPlate(){
        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        //create a plate list
        for(int i = 0; i < gp.iTile[1].length; i++){
            if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].name != null && gp.iTile[gp.currentMap][i].name.equals(IT_MetalPlate.itName)){
                plateList.add(gp.iTile[gp.currentMap][i]);
            }
        }
        //create a rock list
        for(int i = 0; i < gp.npc[1].length; i++){
            if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name != null && gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.npcName)){
                rockList.add(gp.npc[gp.currentMap][i]);
            }
        }
        int count = 0;
        //scan plate list
        for(int i = 0; i < plateList.size(); i++){
            int xDistance = Math.abs(worldX - plateList.get(i).worldX);
            int yDistance = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistance, yDistance);

            if(distance < 32){
                if(linkedEntity == null){
                    linkedEntity = plateList.get(i);
                    gp.playSE(22);
                }
            } else {
                if(linkedEntity == plateList.get(i)){
                    linkedEntity = null;
                }
            }
        }
        //scan rock list
        for(int i = 0; i < rockList.size(); i++){

            if(rockList.get(i).linkedEntity != null){
                count++;
            }
        }
        if(count == rockList.size()){
            for(int i = 0; i < gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)){
                    gp.obj[gp.currentMap][i] = null;
                    gp.playSE(21);
                }
            }
        }
    }
}
