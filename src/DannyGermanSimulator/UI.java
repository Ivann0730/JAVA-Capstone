package DannyGermanSimulator;

import Entity.Entity;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Mana;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    public Font oldSchool;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean playerDead = false;
    public String currentDialogue = "";
    double playTime;
    public int commandNum = 0;
    public int titleScreenState = 0;
    DecimalFormat dFormat = new DecimalFormat("0.00");
    public int playerSlotCol = 0, playerSlotRow = 0;
    public int npcSlotCol = 0, npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/Font/OldSchoolAdventures-42j9.ttf");
            oldSchool = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        //CREATE HUD OBJECTS
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity crystal = new OBJ_Mana(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
        Entity bronzeCoin = new OBJ_Coin(gp);
        coin = bronzeCoin.down1;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
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
            drawPlayerLife();
            drawMessage();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPausedScreen();
            drawPlayerLife();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        //OPTION STATE
        if(gp.gameState == gp.optionState){
            drawOptionScreen();
        }
        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        //TRANSITION STATE
        if(gp.gameState == gp.transitionState){
            drawTransition();
        }
        //TRANSITION STATE EXPANSION
        if(gp.gameState == gp.transitionStateExpansion){
            drawTransitionExpansion();
        }
        //TRADE STATE
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
        //SLEEP STATE
        if(gp.gameState == gp.sleepsState){
            drawSleepScreen();
        }
    }
    public void drawPlayerLife(){
//        gp.player.life = 3;
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //draw max heart
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        //draw current heart
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

        //draw Max Mana
        x = gp.tileSize/2;
        y = gp.tileSize+64;
        i = 0;
        while(i < gp.player.maxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += gp.tileSize-32;
        }

        //draw mana
        x = gp.tileSize/2;
        y = gp.tileSize+64;
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += gp.tileSize-32;
        }
    }
    public void drawMessage(){

        int messageX = gp.tileSize, messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32));

        for(int i = 0; i < message.size(); i++){
            if(message.get(i)!=null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i),messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i),messageX, messageY);
                //this is just counter++  but since arraylist we cant do it like that
                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i,counter);
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
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
            g2.drawImage(gp.player.idle, x, y, gp.tileSize*2, gp.tileSize*2, null);
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
            g2.setColor(new Color(0,0,0));
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

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
        int x = gp.tileSize * 3;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x-32,y-32);
            y+=48;
        }
    }
    public void drawCharacterScreen(){
        //CREATE A FRAME
        final int frameX = gp.tileSize *2, frameY = gp.tileSize, frameWidth = gp.tileSize * 5, frameHeight = gp.tileSize * 7;
        drawSubWindow(frameX, frameY, frameWidth,frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        int textX = frameX + 32;
        int textY = frameY + gp.tileSize/2;
        final int lineHeight = 48;

        //NAMES
        g2.drawString("Level",textX, textY);
        textY += lineHeight;
        g2.drawString("Life",textX,textY);
        textY += lineHeight;
        g2.drawString("Mana",textX,textY);
        textY += lineHeight;
        g2.drawString("Strength",textX,textY);
        textY += lineHeight;
        g2.drawString("Dexterity",textX,textY);
        textY += lineHeight;
        g2.drawString("Attack",textX,textY);
        textY += lineHeight;
        g2.drawString("Defense",textX,textY);
        textY += lineHeight;
        g2.drawString("EXP",textX,textY);
        textY += lineHeight;
        g2.drawString("To Next Level",textX,textY);
        textY += lineHeight;
        g2.drawString("Coin",textX,textY);
        textY += lineHeight + 40;
        g2.drawString("Weapon",textX,textY);
        textY += lineHeight + 70;
        g2.drawString("Shield",textX,textY);
        textY += lineHeight;

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        // RESET textY
        textY = frameY + gp.tileSize/2;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getAlignToRightText(value,tailX) - 64;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getAlignToRightText(value,tailX) - 24;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getAlignToRightText(value,tailX) - 24;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getAlignToRightText(value,tailX) - 64;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getAlignToRightText(value,tailX) - 64;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getAlignToRightText(value,tailX) - 64;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defence);
        textX = getAlignToRightText(value,tailX) - 64;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getAlignToRightText(value,tailX) - 64;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getAlignToRightText(value,tailX) - 64;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coins);
        textX = getAlignToRightText(value,tailX) - 64;
        g2.drawString(value,textX,textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize-32, textY - 30, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize-32, textY - 30, null);
    }
    public void drawInventory(Entity entity, boolean cursor){
        int frameX = 0, frameY = 0, frameWidth = 0, frameHeight = 0, slotCol = 0, slotRow = 0;
        if(entity == gp.player){
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        //FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOTS
        final int slotXStart = frameX + 20, slotYStart = frameY + 20;
        int slotX = slotXStart + 32, slotY = slotYStart + 32, slotSize = gp.tileSize+3;

        //DRAW PLAYER ITEMS
        for(int i = 0; i < entity.inventory.size(); i++){
            //EQUIP CURSOR
            if(entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).down1,slotX,slotY,null);

            //DISPLAY AMOUNT
            if(entity == gp.player && entity.inventory.get(i).amount > 1){
                g2.setFont(g2.getFont().deriveFont(32F));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getAlignToRightText(s, slotX + 44);
                amountY = slotY - 8 + gp.tileSize;

                //SHADOW FOR NUMBER
                g2.setColor(new Color(60,60,60));
                g2.drawString(s,amountX,amountY);

                //NUMBER
                g2.setColor(Color.white);
                g2.drawString(s,amountX-3,amountY-3);

            }
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 11){
                slotX = slotXStart + 32;
                slotY += slotSize;
            }
        }

        //CURSOR
        if(cursor){
            int cursorX = slotXStart + (slotSize * slotCol) + 32, cursorY = slotYStart + (slotSize * slotRow) + 32, cursorWidth = gp.tileSize, cursorHeight = gp.tileSize;
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight, 10,10);

            //DESCRIPTION FRAME
            int dFrameX = frameX, dFrameY = frameY + frameHeight, dFrameWidth = frameWidth, dFrameHeight = gp.tileSize*2 ;


            //draw description text
            int textX = dFrameX + 32, textY = dFrameY + 64;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
            if(itemIndex < entity.inventory.size()){
                drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
                for(String line : entity.inventory.get(itemIndex).description.split("\n")){
                    g2.drawString(line,textX,textY);
                    textY += 64;
                }
            }
        }
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
        text = "You Died";
        //SHADOW
        g2.setColor(Color.black);
        x = getXForCenteredText(text);
        y = gp.tileSize * 6;
        g2.drawString(text,x,y);
        //MAIN
        g2.setColor(Color.red);
        g2.drawString(text,x-4, y-4);
        //RETRY
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x-96,y);
        }
        //EXIT
        text = "Quit";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">",x-96,y);
        }
    }
    public void drawOptionScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX = gp.tileSize *6, frameY = gp.tileSize, frameWidth = gp.tileSize * 8, frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY,frameWidth,frameHeight);

        switch(subState){
            case 0: options_top(frameX,frameY);break;
            case 1: options_fullScreenNotification(frameX,frameY); break;
            case 2: options_control(frameX,frameY);break;
            case 3: options_endGameConfirmation(frameX,frameY);break;
        }

        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY){
        int textX, textY;

        //TITLE
        String text = "Options";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX,textY);

        //FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY +=gp.tileSize*2;
        g2.drawString("Full Screen",textX,textY);
        if(commandNum == 0){
            g2.drawString(">", textX-50,textY);
            if(gp.keyH.enterPressed){
                if(!gp.fullScreenOn){
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music",textX,textY);
        if(commandNum == 1){
            g2.drawString(">", textX-50,textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("SE",textX,textY);
        if(commandNum == 2){
            g2.drawString(">", textX-50,textY);
        }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control",textX,textY);
        if(commandNum == 3){
            g2.drawString(">", textX-50,textY);
            if(gp.keyH.enterPressed){
                subState = 2;
                commandNum = 0;
            }
        }

        //END GAME
        textY += gp.tileSize;
        g2.drawString("End Game",textX,textY);
        if(commandNum == 4){
            g2.drawString(">", textX-50,textY);
            if(gp.keyH.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }

        //BACK
        textY += gp.tileSize*2;
        g2.drawString("Back",textX,textY);
        if(commandNum == 5){
            g2.drawString(">", textX-50,textY);
            if(gp.keyH.enterPressed){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //FULL SCREEN CHECK BOX
        textX = (int) (frameX + gp.tileSize*4.5);
        textY = frameY + gp.tileSize*3 - 48;
        g2.setStroke(new BasicStroke(6));
        g2.drawRect(textX,textY,48,48);
        if(gp.fullScreenOn){
            g2.fillRect(textX,textY,48,48);
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawRect(textX,textY,320,48);
        int volumeWidth = 64 * gp.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth, 48);

        //SE
        textY += gp.tileSize;
        g2.drawRect(textX,textY,320,48);
        volumeWidth = 64 * gp.SE.volumeScale;
        g2.fillRect(textX,textY,volumeWidth, 48);

        gp.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will take effect \nafter restarting the game.";
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //BACK
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX-48, textY);
            if(gp.keyH.enterPressed){
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY){
        int textX, textY;

        //TITLE
        String text = "Control";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move",textX,textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack",textX,textY); textY += gp.tileSize;
        g2.drawString("Interact",textX,textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast",textX,textY); textY += gp.tileSize;
        g2.drawString("Inventory",textX,textY); textY += gp.tileSize;
        g2.drawString("Pause",textX,textY); textY += gp.tileSize;
        g2.drawString("Options",textX,textY);

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD",textX,textY); textY += gp.tileSize;
        g2.drawString("ENTER",textX,textY); textY += gp.tileSize;
        g2.drawString("E",textX,textY); textY += gp.tileSize;
        g2.drawString("F",textX,textY); textY += gp.tileSize;
        g2.drawString("I",textX,textY); textY += gp.tileSize;
        g2.drawString("P",textX,textY); textY += gp.tileSize;
        g2.drawString("ESC",textX,textY);

        //BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back",textX,textY);
        if(commandNum == 0){
            g2.drawString(">", textX-48, textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;

        currentDialogue = "Quit the game and return to the \ntitle screen";

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //YES
        String text = "Yes";
        textX = getXForCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 48, textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                gp.gameState = gp.titleState;
            }
        }
        //NO
        text = "No";
        textX = getXForCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 48, textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                commandNum = 4;
            }
        }
    }
    public void drawTransition(){
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        if(counter == 50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }
    }
    public void drawTransitionExpansion(){
        gp.ui.currentDialogue = "Domain Expansion\nMalevolent Farm";
        counter++;
        g2.setColor(new Color(0,0,0,counter*3));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        gp.ui.drawDialogueScreen();

        if(counter == 80

            ){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }
    }
    public void drawTradeScreen(){
        switch(subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void trade_select(){

        drawDialogueScreen();

        //Draw Window
        int x = gp.tileSize * 14;
        int y = gp.tileSize * 5;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);

        //Draw Texts
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x,y);
        if(commandNum == 0){
            g2.drawString(">", x-64, y);
            if(gp.keyH.enterPressed){
                subState = 1;
            }
        }
        y += gp.tileSize;
        g2.drawString("Sell", x,y);
        if(commandNum == 1){
            g2.drawString(">", x-64, y);
            if(gp.keyH.enterPressed){
                subState = 2;
            }
        }
        y += gp.tileSize;
        g2.drawString("Leave", x,y);
        if(commandNum == 2){
            g2.drawString(">", x-64, y);
            if(gp.keyH.enterPressed){
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Come again hehe";
            }
        }
    }
    public void trade_buy(){

        //DRAW PLAYER INVENTORY
        drawInventory(gp.player,false);
        //DRAW NPC INVENTORY
        drawInventory(npc,true);
        //DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+48, y+60);
        //DRAW PLAYER COIN WINDOW
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your Coins: "+gp.player.coins, x+48, y+60);
        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            x = (int) (gp.tileSize*5.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+10,100,100,null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x =getAlignToRightText(text,gp.tileSize*7+32);
            g2.drawString(text, x , y+80);

            //BUY AN ITEM
            if(gp.keyH.enterPressed){
                if(npc.inventory.get(itemIndex).price > gp.player.coins){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You need more coins to buy this item.";
                    drawDialogueScreen();
                }
                else {
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex))){
                        gp.player.coins -= npc.inventory.get(itemIndex).price;
                    }
                    else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "Your Inventory is Full!";
                    }
                }
            }
        }
    }
    public void trade_sell(){
        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+48, y+60);
        //DRAW PLAYER COIN WINDOW
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your Coins: "+gp.player.coins, x+48, y+60);
        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){
            x = (int) (gp.tileSize*15.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+10,100,100,null);

            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = "" + price;
            x =getAlignToRightText(text,gp.tileSize*17+32);
            g2.drawString(text, x , y+80);

            //SElL AN ITEM
            if(gp.keyH.enterPressed){
                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You cannot sell an equipped item!";
                    drawDialogueScreen();
                } else {
                    if(gp.player.inventory.get(itemIndex).amount > 1){
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coins += price;
                }
            }
        }
    }
    public void drawSleepScreen(){
        counter++;
        if(counter < 120){
            gp.eManager.lighting.filterAlpha += 0.01f;
            if(gp.eManager.lighting.filterAlpha > 1f){
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if(counter >= 120){
            gp.eManager.lighting.filterAlpha -= 0.01f;
            if(gp.eManager.lighting.filterAlpha <= 0){
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter = 0;
                gp.gameState = gp.playState;
                gp.player.getImage();
            }
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        return slotCol + (slotRow * 5);
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
        return gp.screenWidth/2 - length/2;
    }
    public int getAlignToRightText(String text, int tailX){
        int length =  (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = tailX - length;
        return x;
    }
}