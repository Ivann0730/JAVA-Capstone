package object;

import DannyGermanSimulator.GamePanel;
import Entity.Projectile;

import java.awt.*;

public class OBJ_Rock extends Projectile {
    public static final String objName = "Rock";
    GamePanel gp;
    public OBJ_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        speed = 10;
        maxLife = 120;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;

        getImage();
    }
    public void getImage(){
        up1 = setUp("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        up2 = setUp("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        down1 = setUp("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        down2 = setUp("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        right1 = setUp("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        right2 = setUp("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        left1 = setUp("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
        left2 = setUp("/projectile/rock_down_1",gp.tileSize,gp.tileSize);
    }
    public Color getParticleColor(){
        Color color = new Color(40,50,0);
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
