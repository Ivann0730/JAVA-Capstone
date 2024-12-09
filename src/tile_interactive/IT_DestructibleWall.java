package tile_interactive;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;
import object.OBJ_Coin;
import object.OBJ_Gem;
import object.OBJ_Heart;
import object.OBJ_Mana;

import java.awt.*;
import java.util.Random;

public class IT_DestructibleWall extends InteractiveTile{
    GamePanel gp;
    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        this.solidArea.width = 120;
        this.solidArea.height = 120;
        this.solidAreaDefaultX = 0;
        this.solidAreaDefaultY = 0;
        down1 = setUp("/tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 3;
    }
    public boolean isCorrectItem(Entity entity){
        return entity.currentWeapon.type == type_pickaxe;
    }
    public void playSE(){
        gp.playSE(8);
    }
    public InteractiveTile getDestroyForm(){
        InteractiveTile tile = null;
        return tile;
    }
    public Color getParticleColor(){
        Color color = new Color(65,65,65);
        return color;
    }
    public int getParticleSize(){
        int size = 12;
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
    public void checkDrop(){
        //CAST A DIE
        int i = new Random().nextInt(100)+1;

        //SET THE MONSTER DROP
        if(i < 5){
            dropItem(new OBJ_Gem(gp));
        }
        if(i > 5 && i < 45){
            dropItem(new OBJ_Coin(gp));
        }
    }
}
