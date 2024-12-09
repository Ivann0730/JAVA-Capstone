package DannyGermanSimulator;

import Entity.Entity;
import data.Progress;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventMaster = new Entity(gp);
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;

        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 48;
            eventRect[map][col][row].height = 48;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }
    public void setDialogue(){
        eventMaster.dialogues[0][0] = "You have fallen into a Pit";
        eventMaster.dialogues[1][0] = "You drank holy water, life restored\nProgress has been saved.";
    }
    public void checkEvent() {
        //check player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent){
            if(hit(2,75,35,"any")) {teleport(4,127,237,gp.outside);}//SPAWN TO PATHWAY
            if(hit(4,127,237,"any")) {teleport(2,75,35,gp.outside);}//PATHWAY TO SPAWN
            else if(hit(0,42,1,"any")) {damagePit(gp.dialogueState);}
            else if(hit(0,13,26,"any")) {healingPool(gp.dialogueState);}
            else if(hit(0,13,27,"any")) {healingPool(gp.dialogueState);}
            else if(hit(0,13,28,"any")) {healingPool(gp.dialogueState);}
            else if(hit(0,13,29,"any")) {healingPool(gp.dialogueState);}
            else if(hit(0,39,18,"any")) {teleport(1,12,13,gp.outside);}
            else if(hit(1,12,13,"any")) {teleport(0,39,18,gp.outside);}
            else if(hit(1,12,9,"up")) {speak(gp.npc[1][0]);}
            else if(hit(2,153,60,"up")) {speak(gp.npc[2][1]);}
            else if(hit(3,39,157,"any")) {teleport(3,79,121,gp.dungeon);}//D1 to D2
            else if(hit(3,79,121,"any")) {teleport(3,39,157,gp.dungeon);}//D2 to D1
            else if(hit(3,127,124,"any")) {teleport(3,48,56,gp.dungeon);}//D2 to D3
            else if(hit(3,48,56,"any")) {teleport(3,127,124,gp.dungeon);}//D3 to D2
            else if(hit(3,77,110,"any")) {teleport(3,56,100,gp.dungeon);}
            else if(hit(3,56,100,"any")) {teleport(3,77,110,gp.dungeon);}
            else if(hit(3,101,44,"any")) {teleport(3,184,76,gp.dungeon);}
            else if(hit(3,184,76,"any")) {teleport(3,101,44,gp.dungeon);}
            else if(hit(3,184,58,"any")) {teleport(3,149,212,gp.dungeon);}
            else if(hit(3,149,212,"any")) {teleport(3,184,58,gp.dungeon);}
            else if(hit(3,149,212,"any")) {teleport(3,149,202,gp.dungeon);}
            else if(hit(3,148,202,"any")) {skeletonLord();}
            else if(hit(3,149,202,"any")) {skeletonLord();}
            else if(hit(3,150,202,"any")) {skeletonLord();}
        }
    }
    public boolean hit(int map,int col, int row, String reqDirection){
        boolean hit = false;
        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col *gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row *gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.playSE(6);
        gp.player.life-=1;
        eventMaster.startDialogue(eventMaster,0);
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void healingPool(int gameState){
        if(gp.keyH.ePressed){
            gp.gameState = gameState;
            eventMaster.startDialogue(eventMaster,1);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
            gp.saveLoad.save();
        }
        gp.keyH.ePressed  = false;
    }
    public void teleport(int map, int col, int row, int area){
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(10);
    }
    public void teleportExpansion(int map, int col, int row){
        gp.gameState = gp.transitionStateExpansion;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(12);
    }
    public void speak(Entity entity){
        if(gp.keyH.enterPressed){
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }
    public void skeletonLord(){

        if(!gp.bossBattleOn && !Progress.skeletonLordDefeated){
            gp.gameState = gp.cutSceneState;
            gp.csManager.sceneNum = gp.csManager.skeletonLord;
        }
    }
}
