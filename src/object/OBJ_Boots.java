package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Boots extends Entity {
    public static final String objName = "Footmop";
    GamePanel gp;
    public OBJ_Boots(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = objName;
        price = 999999999;
        value = 1;
        down1 = setUp("/objects/boots",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nSir Serato's Footmop,\n it's cool and normal.";

        setDialogues();
    }
    public void setDialogues(){
        dialogues[0][0] = "You have obtained Sir Serato's Foot mop!";
        dialogues[0][1] = "CodingSkills++ when wearing this.";
        dialogues[0][2] = "Cool and Normal";
    }
    public boolean use(Entity entity){
        gp.gameState = gp.cutSceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
}
