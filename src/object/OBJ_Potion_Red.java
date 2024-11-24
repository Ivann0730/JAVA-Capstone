package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Potion_Red extends Entity {
    GamePanel gp;

    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Health Potion";
        value = 5;
        down1 = setUp("/objects/potion_red",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nA potion that restores" + value + " health\nusing codechum juice.";
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drank the " + name + "!\n" + "Your life has been restored by " + value + ".";
        entity.life += value;
        gp.playSE(4);
    }

}
