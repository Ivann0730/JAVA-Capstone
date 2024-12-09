package DannyGermanSimulator;

import Entity.NPC_Merchant;
import Entity.NPC_radish;
import monster.*;
import object.*;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 36;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 7;
        gp.obj[mapNum][i].worldY = gp.tileSize * 28;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 7;
        gp.obj[mapNum][i].worldY = gp.tileSize * 41;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 39;
        gp.obj[mapNum][i].worldY = gp.tileSize * 5;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 40;
        gp.obj[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 36;
        gp.obj[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 35;
        gp.obj[mapNum][i].worldY = gp.tileSize * 13;


        mapNum = 3;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 39;
        gp.obj[mapNum][i].worldY = gp.tileSize * 162;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 48;
        gp.obj[mapNum][i].worldY = gp.tileSize * 194;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 23;
        gp.obj[mapNum][i].worldY = gp.tileSize * 230;
        i++;


        gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 230;
        i++;
    }
    public void setNPC(){
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_radish(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 20;
        gp.npc[mapNum][i].worldY = gp.tileSize * 20;
        i++;


        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;
        i++;

        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_radish(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 27;
        gp.npc[mapNum][i].worldY = gp.tileSize * 117;
        i++;
        gp.npc[mapNum][i] = new NPC_radish(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 186;
        gp.npc[mapNum][i].worldY = gp.tileSize * 182;
        i++;

        mapNum = 3;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 22;
        gp.npc[mapNum][i].worldY = gp.tileSize * 231;
        i++;
    }
    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        //first area
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 15;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 42;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        mapNum = 2;
        i = 0;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 42;
        gp.monster[mapNum][i].worldY = gp.tileSize * 95;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 46;
        gp.monster[mapNum][i].worldY = gp.tileSize * 91;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 103;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 44;
        gp.monster[mapNum][i].worldY = gp.tileSize * 105;
        i++;

        //second area
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 84;
        gp.monster[mapNum][i].worldY = gp.tileSize * 103;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 89;
        gp.monster[mapNum][i].worldY = gp.tileSize * 108;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 77;
        gp.monster[mapNum][i].worldY = gp.tileSize * 108;
        i++;
        //third area orc Area
        gp.monster[mapNum][i] = new MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 78;
        gp.monster[mapNum][i].worldY = gp.tileSize * 75;
        i++;
        gp.monster[mapNum][i] = new MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 72;
        gp.monster[mapNum][i].worldY = gp.tileSize * 78;
        i++;
        gp.monster[mapNum][i] = new MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 66;
        gp.monster[mapNum][i].worldY = gp.tileSize * 74;
        i++;
        //fourth area
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 114;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;
        i++;
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 108;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;
        i++;
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 100;
        gp.monster[mapNum][i].worldY = gp.tileSize * 41;
        i++;

        //fifth place
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 31;
        gp.monster[mapNum][i].worldY = gp.tileSize * 82;
        i++;
        gp.monster[mapNum][i] = new MON_BlueSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 74;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 77;
        i++;
        //free space1
        gp.monster[mapNum][i] = new MON_Cock(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 155;
        gp.monster[mapNum][i].worldY = gp.tileSize * 60;
        i++;

        gp.monster[mapNum][i] = new MON_Cock(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;
        gp.monster[mapNum][i] = new MON_Cock(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 133;
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;
        i++;



        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 186;
        gp.monster[mapNum][i].worldY = gp.tileSize * 189;
        i++;

        mapNum = 3;
        i = 0;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 230;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 230;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 50;
        gp.monster[mapNum][i].worldY = gp.tileSize * 229;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 51;
        gp.monster[mapNum][i].worldY = gp.tileSize * 229;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 44;
        gp.monster[mapNum][i].worldY = gp.tileSize * 218;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 61;
        gp.monster[mapNum][i].worldY = gp.tileSize * 202;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 77;
        gp.monster[mapNum][i].worldY = gp.tileSize * 209;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 75;
        gp.monster[mapNum][i].worldY = gp.tileSize * 212;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 71;
        gp.monster[mapNum][i].worldY = gp.tileSize * 189;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 50;
        gp.monster[mapNum][i].worldY = gp.tileSize * 188;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 50;
        gp.monster[mapNum][i].worldY = gp.tileSize * 189;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 50;
        gp.monster[mapNum][i].worldY = gp.tileSize * 190;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        gp.monster[mapNum][i].worldY = gp.tileSize * 171;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 43;
        gp.monster[mapNum][i].worldY = gp.tileSize * 171;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 174;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 171;
        i++;
    }
    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,18,17); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,19,17); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,20,17); i++;


        gp.iTile[mapNum][i] = new IT_DryTree(gp,5,26); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,6,26); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,7,26); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,8,26); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,5,29); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,6,29); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,7,29); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,8,29); i++;
    }
}
