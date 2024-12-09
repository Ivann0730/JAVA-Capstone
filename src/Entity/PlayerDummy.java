package Entity;

import DannyGermanSimulator.GamePanel;

public class PlayerDummy extends  Entity{
    public static final String npcName = "Dummy";
    public PlayerDummy(GamePanel gp){
        super(gp);

        name = npcName;
        getImage();
    }
    public void getImage() {
        idle = setUp("/player/heil",gp.tileSize,gp.tileSize);
        idleleft = setUp("/maxLevelArmor/Left1",gp.tileSize,gp.tileSize);
        idleright = setUp("/maxLevelArmor/Right1",gp.tileSize,gp.tileSize);
        idledown = setUp("/maxLevelArmor/Back1",gp.tileSize,gp.tileSize);
        idleup = setUp("/maxLevelArmor/Front1",gp.tileSize,gp.tileSize);
        up1 = setUp("/maxLevelArmor/Front2",gp.tileSize,gp.tileSize);
        up2 = setUp("/maxLevelArmor/Front3",gp.tileSize,gp.tileSize);
        up3 = setUp("/maxLevelArmor/Front2",gp.tileSize,gp.tileSize);
        up4 = setUp("/maxLevelArmor/Front4",gp.tileSize,gp.tileSize);
        down1 = setUp("/maxLevelArmor/Back1",gp.tileSize,gp.tileSize);
        down2 = setUp("/maxLevelArmor/Back2",gp.tileSize,gp.tileSize);
        down3 = setUp("/maxLevelArmor/Back3",gp.tileSize,gp.tileSize);
        down4 = setUp("/maxLevelArmor/Back4",gp.tileSize,gp.tileSize);
        left1 = setUp("/maxLevelArmor/Left1",gp.tileSize,gp.tileSize);
        left2 = setUp("/maxLevelArmor/Left2",gp.tileSize,gp.tileSize);
        left3 = setUp("/maxLevelArmor/Left3",gp.tileSize,gp.tileSize);
        left4 = setUp("/maxLevelArmor/Left4",gp.tileSize,gp.tileSize);
        right1 = setUp("/maxLevelArmor/Right1",gp.tileSize,gp.tileSize);
        right2 = setUp("/maxLevelArmor/Right2",gp.tileSize,gp.tileSize);
        right3 = setUp("/maxLevelArmor/Right3",gp.tileSize,gp.tileSize);
        right4 = setUp("/maxLevelArmor/Right4",gp.tileSize,gp.tileSize);
    }
}
