package Entity;

import DannyGermanSimulator.GamePanel;
import DannyGermanSimulator.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    public GamePanel gp;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage idleleft, idleright, idleup, idledown, idle;
    public BufferedImage up1, up2, up3, up4;
    public BufferedImage down1, down2, down3, down4;
    public BufferedImage left1, left2, left3, left4;
    public BufferedImage right1, right2, right3, right4;
    public BufferedImage image, image2, image3;
    String dialogues[] = new String[20];
    public boolean collision = false;
    // to fix hit box values
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX = 34, solidAreaDefaultY = 38;

    //STATE
    public int worldX ,worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;

    //COUNTER
    public int spriteCounter = 0;
    public int spriteSpeedMultiplier = 9;
    public int actionLockCounter=0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    //CHARACTER ATTRIBUTES
    public int speed;
    public String name;
    public int maxMana;
    public int mana;
    public int maxLife;
    public int life;
    public int level, strength, dexterity, attack, defence, exp, nextLevelExp, coins;
    public Entity currentWeapon, currentShield;
    public Projectile projectile;

    //ITEM ATTRIBUTES
    public int value;
    public int attackValue, defenseValue;
    public String description = "";
    public int useCost;

    //TYPE
    public int type; //0 = player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;


    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){
    }
    public void damageReaction(){
    }
    public void speak(){
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void use(Entity entity){
    }
    public void checkDrop(){

    }
    public void dropItem(Entity droppedItem){
        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                //dead monsters coordinates
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    public int getParticleSize(){
        int size = 0;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);

    }
    public void update(){
        setAction();
        collisionOn = false;
        gp.colCheck.checkTile(this);
        gp.colCheck.checkObject(this,false);
        gp.colCheck.checkEntity(this, gp.npc);
        gp.colCheck.checkEntity(this, gp.monster);
        gp.colCheck.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.colCheck.checkPlayer(this);

        if(this.type == type_monster && contactPlayer){
            damagePlayer(attack);
        }
        if(!collisionOn){
            switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }
    public void damagePlayer(int attack){
        if(!gp.player.invincible){
            //we give damage
            gp.playSE(6);

            int damage = attack - gp.player.defence;
            if(damage < 0) {
                damage = 0;
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//        BufferedImage[] upSprites = {up1, up2, up3, up4};
//        BufferedImage[] downSprites = {down1, down2, down3, down4};
//        BufferedImage[] leftSprites = {left1, left2, left3, left4};
//        BufferedImage[] rightSprites = {right1, right2, right3, right4};

        // Only draw the tiles that are visible on the screen
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                //sprites 3-4 aren't used I don't know why yet, so only 1 and 2 sprites are used
                case "up":
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                    if(spriteNum == 3) image = up3;
                    if(spriteNum == 4) image = up4;
                    break;

                case "down":
                    if(spriteNum == 1) image = down1;
                    if(spriteNum == 2) image = down2;
                    if(spriteNum == 3) image = down3;
                    if(spriteNum == 4) image = down4;
                    break;

                case "left":
                    if(spriteNum == 1) image = left1;
                    if(spriteNum == 2) image = left2;
                    if(spriteNum == 3) image = left3;
                    if(spriteNum == 4) image = left4;
                    break;

                case "right":
                    if(spriteNum == 1) image = right1;
                    if(spriteNum == 2) image = right2;
                    if(spriteNum == 3) image = right3;
                    if(spriteNum == 4) image = right4;
                    break;
            }

            //MONSTER HP BAR
            if(type == 2 && hpBarOn){
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale*life;
                //outline
                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1,screenY-16,gp.tileSize+2,12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX,screenY-15,(int) hpBarValue, 10);

                hpBarCounter++;

                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if(invincible){
                hpBarOn = true;
                hpBarCounter=0;
                changeAlpha(g2,0.4F);
            }
            if(dying){
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY,null);
            changeAlpha(g2,1F);

            //to see collision hit box
            g2.setColor(Color.green);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int  i = 5;

        if(dyingCounter <= i) changeAlpha(g2,0F);
        if(dyingCounter > i && dyingCounter <= i*2) changeAlpha(g2,1F);
        if(dyingCounter > i*2 && dyingCounter <= i*3) changeAlpha(g2,0F);
        if(dyingCounter > i*3 && dyingCounter <= i*4) changeAlpha(g2,1F);
        if(dyingCounter > i*4 && dyingCounter <= i*5) changeAlpha(g2,0F);
        if(dyingCounter > i*5 && dyingCounter <= i*6) changeAlpha(g2,1F);
        if(dyingCounter > i*6 && dyingCounter <= i*7) changeAlpha(g2,0F);
        if(dyingCounter > i*7 && dyingCounter <= i*8) changeAlpha(g2,1F);
        if(dyingCounter > i*8){
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setUp(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
//            image = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/"+ imageName + ".png"));
            image = ImageIO.read(getClass().getResourceAsStream( imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}