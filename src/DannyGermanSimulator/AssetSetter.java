package DannyGermanSimulator;

import Entity.NPC_radish;
import monster.MON_Bat;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
//        gp.obj[0] = new OBJ_Key(gp);
//        gp.obj[0].worldX = gp.tileSize * 26;
//        gp.obj[0].worldY = gp.tileSize * 36;
    }
    public void setNPC(){
        gp.npc[0] = new NPC_radish(gp);
        gp.npc[0].worldX = gp.tileSize * 20;
        gp.npc[0].worldY = gp.tileSize * 20;
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
    }
}
