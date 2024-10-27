package object;

import DannyGermanSimulator.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    GamePanel gp;
    public OBJ_Key(GamePanel gp){
        name = "Mana";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/manacrystal_full.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
