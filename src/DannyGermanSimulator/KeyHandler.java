package DannyGermanSimulator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;

public class KeyHandler implements KeyListener{
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, mountPressed, fPressed = false, enterPressed=false, shotKeyPressed;
    public boolean isMountPressed = false;
    boolean showDebugText = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        //PLAY STATE
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
    }
    public void titleState(int code){
        if(gp.ui.titleScreenState == 0){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                //NEW GAME
                if(gp.ui.commandNum == 0){
                    gp.ui.titleScreenState = 1;
                }
                //LOAD GAME
                if(gp.ui.commandNum == 1){
                    //add later
                }
                //QUIT
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }
        //For Class Selection
        else if(gp.ui.titleScreenState == 1){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 3;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    System.out.println("Warrior");
                    gp.gameState = gp.playState;
//                        gp.playMusic(1);
                }
                if(gp.ui.commandNum == 1){
                    System.out.println("Mage");
                    gp.gameState = gp.playState;
                    gp.playMusic(1);
                }
                if(gp.ui.commandNum == 2){
                    System.out.println("Tank");
                    gp.gameState = gp.playState;
                    gp.playMusic(1);
                }
                if(gp.ui.commandNum == 3){
                    gp.ui.titleScreenState = 0;
                }
            }
        }
    }
    public void playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_X){
            mountPressed = !mountPressed;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed=true;
        }
        if(code == KeyEvent.VK_F){
            fPressed=true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_F){
           shotKeyPressed = true;
        }
        //toggle debug
        if(code == KeyEvent.VK_T){
            if(!showDebugText){
                showDebugText = true;
            } else if(showDebugText){
                showDebugText = false;
            }
        }
        if(code == KeyEvent.VK_M){
            gp.tileM.loadMap("/maps/testmap.txt");
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            System.exit(0);
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
                gp.playSE(1);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
                gp.playSE(1);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
                gp.playSE(1);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
                gp.playSE(1);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
    }
}
