package DannyGermanSimulator;

import Entity.NPC_BigRock;
import Entity.NPC_Merchant;
import Entity.NPC_radish;
import data.Progress;
import monster.*;
import object.*;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        int mapNum = 0;
        int i = 0;

        mapNum = 2;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 79;
        gp.obj[mapNum][i].worldY = gp.tileSize * 70;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Axe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 97;
        gp.obj[mapNum][i].worldY = gp.tileSize * 117;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 74;
        gp.obj[mapNum][i].worldY = gp.tileSize * 113;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 103;
        i++;

        mapNum = 3;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 148;
        gp.obj[mapNum][i].worldY = gp.tileSize * 170;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 39;
        gp.obj[mapNum][i].worldY = gp.tileSize * 162;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 48;
        gp.obj[mapNum][i].worldY = gp.tileSize * 191;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 56;
        gp.obj[mapNum][i].worldY = gp.tileSize * 191;
        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 148;
        gp.obj[mapNum][i].worldY = gp.tileSize * 170;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 48;
        gp.obj[mapNum][i].worldY = gp.tileSize * 194;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 56;
        gp.obj[mapNum][i].worldY = gp.tileSize * 194;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 77;
        gp.obj[mapNum][i].worldY = gp.tileSize * 219;
        i++;

        mapNum = 4;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 131;
        gp.obj[mapNum][i].worldY = gp.tileSize * 96;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 112;
        gp.obj[mapNum][i].worldY = gp.tileSize * 143;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Shield_Blue(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 143;
        gp.obj[mapNum][i].worldY = gp.tileSize * 120;
        i++;
    }
    public void setNPC(){
        int mapNum = 0;
        int i = 0;


        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_radish(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 27;
        gp.npc[mapNum][i].worldY = gp.tileSize * 117;
        i++;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 153;
        gp.npc[mapNum][i].worldY = gp.tileSize * 58;
        i++;

        mapNum = 3;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 48;
        gp.npc[mapNum][i].worldY = gp.tileSize * 227;
        i++;

        mapNum = 4;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 131;
        gp.npc[mapNum][i].worldY = gp.tileSize * 62;
        i++;
    }
    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        mapNum = 2;
        i = 0;
        gp.monster[mapNum][i] = new MON_cock(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 137;
        gp.monster[mapNum][i].worldY = gp.tileSize * 60;
        i++;
        gp.monster[mapNum][i] = new MON_cock(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 132;
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;
        i++;
        gp.monster[mapNum][i] = new MON_cock(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 73;
        gp.monster[mapNum][i].worldY = gp.tileSize * 118;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 67;
        gp.monster[mapNum][i].worldY = gp.tileSize * 96;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 51;
        gp.monster[mapNum][i].worldY = gp.tileSize * 64;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 52;
        gp.monster[mapNum][i].worldY = gp.tileSize * 64;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 99;
        gp.monster[mapNum][i].worldY = gp.tileSize * 61;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 135;
        gp.monster[mapNum][i].worldY = gp.tileSize * 23;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 132;
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;

        mapNum = 4;
        i = 0;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 145;
        gp.monster[mapNum][i].worldY = gp.tileSize * 43;
        i++;
        gp.monster[mapNum][i] = new MON_Slime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 147;
        gp.monster[mapNum][i].worldY = gp.tileSize * 49;
        i++;

        mapNum = 3;
        i = 0;
        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 26;
        gp.monster[mapNum][i].worldY = gp.tileSize * 230;
        i++;
        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 40;
        gp.monster[mapNum][i].worldY = gp.tileSize * 230;
        i++;
        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 57;
        gp.monster[mapNum][i].worldY = gp.tileSize * 218;
        i++;
        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 61;
        gp.monster[mapNum][i].worldY = gp.tileSize * 218;
        i++;
        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 56;
        gp.monster[mapNum][i].worldY = gp.tileSize * 189;
        i++;


        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 82;
        gp.monster[mapNum][i].worldY = gp.tileSize * 119;
        i++;
        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 85;
        gp.monster[mapNum][i].worldY = gp.tileSize * 129;
        i++;


        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 158;
        gp.monster[mapNum][i].worldY = gp.tileSize * 76;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 178;
        gp.monster[mapNum][i].worldY = gp.tileSize * 99;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 158;
        gp.monster[mapNum][i].worldY = gp.tileSize * 76;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 184;
        gp.monster[mapNum][i].worldY = gp.tileSize * 103;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 190;
        gp.monster[mapNum][i].worldY = gp.tileSize * 97;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 214;
        gp.monster[mapNum][i].worldY = gp.tileSize * 77;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 217;
        gp.monster[mapNum][i].worldY = gp.tileSize * 74;
        i++;

        if(!Progress.skeletonLordDefeated){
            gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize * 148;
            gp.monster[mapNum][i].worldY = gp.tileSize * 192;
            i++;
        }
    }
    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        mapNum = 2;
        i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,136,42); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,137,42); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,50,108); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,73,34); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,77,34); i++;

        mapNum = 3;
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,44,229); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,45,229); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,44,228); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,45,228); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,72,216); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,72,217); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,72,218); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,82,216); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,82,218); i++;

        mapNum = 4;
        i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,125,220); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,126,220); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,127,220); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,128,220); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,129,220); i++;
    }
}
