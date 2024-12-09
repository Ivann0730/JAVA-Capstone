package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

public class OBJ_Potion_Red extends Entity {
    GamePanel gp;
    public static final String objName = "Health Potion";
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = objName;
        value = 5;
        down1 = setUp("/objects/potion_red",gp.tileSize,gp.tileSize);
        description = "[" + name + "]\nA potion that restores " + value + " health\nusing codechum juice.";
        price = 25;
        stackable = true;
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "You drank the " + name + "!\n" + "Your life has been restored by " + value + ".";
    }
    public boolean use(Entity entity){
        startDialogue(this,0);
        entity.life += value;
        gp.playSE(4);
        return true;
    }

}