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
        gp.npc[1] = new NPC_radish(gp);
        gp.npc[1].worldX = gp.tileSize * 21;
        gp.npc[1].worldY = gp.tileSize * 21;
        gp.npc[2] = new NPC_radish(gp);
        gp.npc[2].worldX = gp.tileSize * 22;
        gp.npc[2].worldY = gp.tileSize * 22;
        gp.npc[3] = new NPC_radish(gp);
        gp.npc[3].worldX = gp.tileSize * 23;
        gp.npc[3].worldY = gp.tileSize * 23;

    }

}
