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
//        gp.npc[0] = new NPC_radish(gp);
//        gp.npc[0].worldX = gp.tileSize * 20;
//        gp.npc[0].worldY = gp.tileSize * 20;
    }
    public void setMonster(){
        gp.monster[0] = new MON_Bat(gp);
        gp.monster[0].worldX = gp.tileSize * 36;
        gp.monster[0].worldY = gp.tileSize * 13;
    }

}
