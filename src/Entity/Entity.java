package Entity;

import DannyGermanSimulator.GamePanel;
import DannyGermanSimulator.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gp;
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage idleleft, idleright, idleup, idledown;
    public BufferedImage up1, up2, up3, up4;
    public BufferedImage down1, down2, down3, down4;
    public BufferedImage left1, left2, left3, left4;
    public BufferedImage right1, right2, right3, right4;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteSpeedMultiplier = 9;
    // to fix hit box values
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter=0;

    String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){
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
    public void update(){
        setAction();
        collisionOn = false;
        gp.colCheck.checkTile(this);
        gp.colCheck.checkObject(this,false);
        gp.colCheck.checkPlayer(this);
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
                //sprites 3-4 arent used idk why yet, so only 1 and 2 sprites are used
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                    if(spriteNum == 3){
                        image = up3;
                    }
                    if(spriteNum == 4){
                        image = up4;
                    }
                    break;

                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    if(spriteNum == 3){
                        image = down3;
                    }
                    if(spriteNum == 4){
                        image = down4;
                    }
                    break;

                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    if(spriteNum == 3){
                        image = left3;
                    }
                    if(spriteNum == 4){
                        image = left4;
                    }
                    break;

                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                    if(spriteNum == 3){
                        image = right3;
                    }
                    if(spriteNum == 4){
                        image = right4;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            //to see collision hit box
            g2.setColor(Color.red);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }

    public BufferedImage setUp(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
//            image = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/"+ imageName + ".png"));
            image = ImageIO.read(getClass().getResourceAsStream( imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
}