package DannyGermanSimulator;

import Entity.PlayerDummy;
import monster.MON_SkeletonLord;
import object.OBJ_Boots;
import object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    //Scene Number
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;
        endCredit = "Program/Music/Arts\n"
                + "Tolentino,Olimba,Paradero,Alaman"
                + "\n\n\n\n\n\n\n\n\n"
                + "Special Thanks To:\n"
                + "Someone\n"
                + "Someone\n"
                + "Someone\n"
                + "Someone\n\n\n\n\n\n"
                + "Thank you for playing!";
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        switch (sceneNum){
            case skeletonLord: scene_skeletonLord(); break;
            case ending: scene_ending(); break;
        }
    }
    public void scene_skeletonLord(){
        if(scenePhase == 0){
            gp.bossBattleOn = true;

            //shut the iron door
            for(int i = 0; i < gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*149;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*203;
                    gp.obj[gp.currentMap][i].temp = true;
                    i++;
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*148;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*203;
                    gp.obj[gp.currentMap][i].temp = true;
                    i++;
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*150;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*203;
                    gp.obj[gp.currentMap][i].temp = true;
                    i++;
                    gp.playSE(21);
                    break;
                }
            }
            //SEARCH A VACANT SLOT FOR A DUMMY
            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
//                    gp.npc[gp.currentMap][i].name = PlayerDummy.npcName;
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;

            scenePhase++;
        }
        if(scenePhase == 1){
            gp.player.worldY -= 8;
            if(gp.player.worldY < gp.tileSize*192){
                scenePhase++;
            }
        }
        if(scenePhase == 2){
            //search the boss
            for(int i = 0; i < gp.monster[1].length; i++){
                if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name.equals(MON_SkeletonLord.monName)){
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3){
            //boss speaks
            gp.ui.drawDialogueScreen();
        }
        if(scenePhase == 4){
            //return camera to the player

            //search dummy
             for(int i = 0; i < gp.npc[1].length; i++){
                 if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                     //restore players position
                     gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                     gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                     //delete dummy
                     gp.npc[gp.currentMap][i] = null;
                     break;
                 }
             }
             //start drawing player
            gp.player.drawing = true;
             //reset
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
            //change music
            gp.stopMusic();
            gp.playMusic(23);
        }
    }
    public void scene_ending(){
        if(scenePhase == 0){
            gp.stopMusic();
            gp.ui.npc = new OBJ_Boots(gp);
            scenePhase++;
        }
        if(scenePhase == 1){
            //display dialogue
            gp.ui.drawDialogueScreen();
        }
        if(scenePhase == 2){
            gp.playSE(24);
            scenePhase++;
        }
        if(scenePhase == 3){
            //wait until the sound effect ends
            if(counterReached(300)){
                scenePhase++;
            }
        }
        if(scenePhase == 4){
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlackBackground(alpha);
            if(alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 5){
            drawBlackBackground(1f);
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            String text = "As the final boss falls,\n" +"The realm of DannyGerman breathes a sigh of relief.\n" +
                    "Yet, in the vast memory of existence, a question lingers:\n" +
                            "What will you compile next? The code of destiny is yours to write.\n" +
                                    "Farewell, hero... until the next loop begins.\n";
            drawString(alpha,50f,450,text,150);
            if(counterReached(1200)){
                gp.playMusic(25);
                scenePhase++;
            }
        }
        if(scenePhase == 6){
            drawBlackBackground(1f);
            drawString(1f,120f, gp.screenHeight/2,"Danny German: \nThe Bugged World",150);

            if(counterReached(480)){
                scenePhase++;
            }
        }
        if(scenePhase == 7){
            drawBlackBackground(1f);
            y = gp.screenHeight/2;
            drawString(1f,80f,y,endCredit,150);
            if(counterReached(480)){
                scenePhase++;
            }
        }
        if(scenePhase==8){
            drawBlackBackground(1f);
            //scrolling credit
            y--;
            drawString(1f,80f,y,endCredit,150);
            if(counterReached(6125)){
                scenePhase++;
            }
        }
        if(scenePhase==9){
            drawBlackBackground(1f);
            gp.stopMusic();
        }
    }
    public boolean counterReached(int target) {

        boolean counterReached = false;
        counter++;
        if (counter > target) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlackBackground(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line : text.split("\n")){
            int x = gp.ui.getXForCenteredText(line);
            g2.drawString(line,x,y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }
}
