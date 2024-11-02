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
        //for anti-alias fontdddd
//        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
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
//        if(playerDead){
//            g2.setFont(arial_40);
//            g2.setColor(Color.red);
//
//            String text;
//            int textLength,x,y;
//            text = "You DIED";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.tileSize/2 - (gp.tileSize*3);
//            g2.drawString(text, x, y);
//
//        } else {
//            g2.setFont(arial_40);
//            g2.setColor(Color.WHITE);
////            g2.drawImage(hpImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
////        g2.drawString("x " + gp.player.hp, 74 , 65);
//
//            //TIME
//            playTime += (double)1/60;
//            g2.drawString(": " + dFormat.format(playTime), gp.tileSize*15 - 15,gp.tileSize/2);
//
//            //message
//            if (messageOn) {
//                g2.setFont(g2.getFont().deriveFont(30F));
//                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
//
//                messageCounter++;
//                if (messageCounter > 180) {
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
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
