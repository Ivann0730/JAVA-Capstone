package object;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import Entity.Projectile;
import java.awt.*;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;
    public static final String objName = "Fireball";
    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.solidAreaDefaultX = 34;
        this.solidAreaDefaultY = 48;
        this.solidArea.width = 48;
        this.solidArea.height = 48;

        name = objName;
        speed = 20;
        maxLife = 100;
        life = maxLife;
        attack = 1;
        useCost = 1;
        alive = false;

        getImage();
    }
    public void getImage(){
        up1 = setUp("/projectile/fireball_up_1",gp.tileSize,gp.tileSize);
        up2 = setUp("/projectile/fireball_up_2",gp.tileSize,gp.tileSize);
        down1 = setUp("/projectile/fireball_down_1",gp.tileSize,gp.tileSize);
        down2 = setUp("/projectile/fireball_down_2",gp.tileSize,gp.tileSize);
        right1 = setUp("/projectile/fireball_right_1",gp.tileSize,gp.tileSize);
        right2 = setUp("/projectile/fireball_right_2",gp.tileSize,gp.tileSize);
        left1 = setUp("/projectile/fireball_left_1",gp.tileSize,gp.tileSize);
        left2 = setUp("/projectile/fireball_left_2",gp.tileSize,gp.tileSize);
    }
    public boolean haveResource(Entity user){
        return user.mana >= useCost;
    }
    public void subtractResource(Entity user){
        user.mana -= useCost;
    }
    public Color getParticleColor(){
        Color color = new Color(240,50,0);
        return color;
    }
    public int getParticleSize(){
        int size = 20;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 2;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
