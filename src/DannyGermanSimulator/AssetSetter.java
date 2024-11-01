package DannyGermanSimulator;

import Entity.NPC_radish;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
//        gp.obj[0] = new OBJ_Key(gp);
//        gp.obj[0].worldX = 36 * gp.tileSize;
//        gp.obj[0].worldY = 5 * gp.tileSize;
    }
    public void setNPC(){
        gp.npc[0] = new NPC_radish(gp);
        gp.npc[0].worldX = gp.tileSize * 20;
        gp.npc[0].worldY = gp.tileSize * 20;

    }

}
