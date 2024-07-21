package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    // DEBUG
    boolean checkDrawTime = false;
    GamePanel gp;

    public KeyHandler (GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //retorna o numero da tecla que foi pressionada

        //TitleState
        /*
        Continuar [0]
        novo jogo [1]
        configuracoes [2]
         */
        if(gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 1) {
                    // add later
                }
                if(gp.ui.commandNum == 2) {
                    // add later
                }
                if(gp.ui.commandNum == 3) {
                    System.exit(0);
                }
            }
        }

        // PlayState
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_SPACE) {
                enterPressed = true;
            }

            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }


            // ativar o DEBUG metod
            if (code == KeyEvent.VK_T) {
                if(checkDrawTime == false) {
                    checkDrawTime = true;
                } else {
                    checkDrawTime = false;
                }
        }
    }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNumPause--;
                    if(gp.ui.commandNumPause < 0) {
                        gp.ui.commandNumPause = 2;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNumPause++;
                    if(gp.ui.commandNumPause > 2) {
                        gp.ui.commandNumPause = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNumPause == 0) {
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNumPause == 1) {
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNumPause == 2) {
                        System.exit(0);
                    }
                }
        }

        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
