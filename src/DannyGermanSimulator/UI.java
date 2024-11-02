package DannyGermanSimulator;

import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font oldSchool;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean playerDead = false;
    public String currentDialogue = "";
    double playTime;
    public int commandNum = 0;
    public int titleScreenState = 0;
    DecimalFormat dFormat = new DecimalFormat("0.00");

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/Font/OldSchoolAdventures-42j9.ttf");
            oldSchool = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
//        OBJ_Heart hp = new OBJ_Heart(gp);
//        hpImage = hp.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(oldSchool);
        //for anti-alias font
//        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
            //do pLay state stuff

        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPausedScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
    }
    public void drawTitleScreen(){
        if(titleScreenState == 0){
            g2.setColor(new Color(165,42,42));
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));
            String text = "DANNYGERMAN SIMULATOR";
            int x = getXForCenteredText(text);
            int y = gp.tileSize*2;
            //SHADOW FOR TEXT
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            //MAIN COLOR TEXT
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            //IMAGE
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize;
            g2.drawImage(gp.player.idledown, x, y, gp.tileSize*2, gp.tileSize*2, null);
            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));

            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "QUIT GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if(titleScreenState == 1){
            //Class Selection Screen
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(32F));

            String text = "Select Your Class";
            int x = getXForCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Assasin";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x- gp.tileSize,y);
            }
            text = "Mage";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x- gp.tileSize,y);
            }
            text = "Tank";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x- gp.tileSize,y);
            }
            text = "Cancel";
            x = getXForCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x- gp.tileSize,y);
            }
        }
    }
    public void drawPausedScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25, 25);
    }
    public int getXForCenteredText(String text){
        int length =  (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
