package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){
        name = "Mana";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/manacrystal_full.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
