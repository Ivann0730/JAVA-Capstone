package DannyGermanSimulator;

import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
//    BufferedImage hpImage;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean playerDead = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
//        OBJ_Heart hp = new OBJ_Heart(gp);
//        hpImage = hp.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            //do pLay state stuff

        }
        if(gp.gameState == gp.pauseState){
            //do pause stuff
            drawPausedScreen();
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
    public int getXForCenteredText(String text){
        int length =  (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
