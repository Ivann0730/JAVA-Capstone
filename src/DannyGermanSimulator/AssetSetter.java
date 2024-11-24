package DannyGermanSimulator;

import Entity.NPC_radish;
import monster.MON_Bat;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        int i = 0;
        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 36;
        i++;
        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 7;
        gp.obj[i].worldY = gp.tileSize * 28;
        i++;
        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 7;
        gp.obj[i].worldY = gp.tileSize * 41;
        i++;
        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 39;
        gp.obj[i].worldY = gp.tileSize * 5;
        i++;
        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 40;
        gp.obj[i].worldY = gp.tileSize * 16;
        i++;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 33;
        gp.obj[i].worldY = gp.tileSize * 35;
        i++;
        gp.obj[i] = new OBJ_Shield_Blue(gp);
        gp.obj[i].worldX = gp.tileSize * 36;
        gp.obj[i].worldY = gp.tileSize * 13;
        i++;
        gp.obj[i] = new OBJ_Potion_Red(gp);
        gp.obj[i].worldX = gp.tileSize * 35;
        gp.obj[i].worldY = gp.tileSize * 13;

    }
    public void setNPC(){
        int i = 0;
        gp.npc[i] = new NPC_radish(gp);
        gp.npc[i].worldX = gp.tileSize * 20;
        gp.npc[i].worldY = gp.tileSize * 20;
        i++;
    }
    public void setMonster(){
        int i = 0;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 36;
        gp.monster[i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 39;
        gp.monster[i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 27;
        gp.monster[i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 35;
        gp.monster[i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 28;
        gp.monster[i].worldY = gp.tileSize * 15;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 42;
        gp.monster[i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 39;
        gp.monster[i].worldY = gp.tileSize * 10;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 30;
        gp.monster[i].worldY = gp.tileSize * 12;
        i++;
    }
}
