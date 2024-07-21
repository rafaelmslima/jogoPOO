package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    /* renderiza todas as informações que aparecerão por cima da tela, poderemos usar mensagens de texto
    icones dos itens, etc.
     */
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_50B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int commandNumPause = 0;




    public UI (GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN, 40);
        arial_50B = new Font("Arial",Font.BOLD, 45);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // Title State
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // Play State
        if(gp.gameState == gp.playState) {
            // fazer o playstate depois
        }
        // Pause State
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // Dialogue State
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

    }

    public void drawTitleScreen() {
        // trocando a cor background do title screen
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        //Tittle Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        String text = "Guardians' Odyssey";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        // Sombra do texto
        g2.setColor(new Color(100, 100, 100));
        g2.drawString(text, x + 5, y+ 5);
        // Cor do texto
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        // Imagem de um personagem
        x = gp.screenWidth / 2 - (gp.tileSize);
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        text = "Novo Jogo";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-(gp.tileSize * 1/2), y);
        }

        text = "Continuar";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3/5;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-(gp.tileSize * 1/2), y);
        }

        text = "Configurações";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3/5;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-(gp.tileSize * 1/2), y);
        }

        text = "Sair";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3/5;
        g2.drawString(text, x, y);
        if(commandNum == 3) {
            g2.drawString(">", x-(gp.tileSize * 1/2), y);
        }

        // Créditos Menu
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 10F));
        g2.setColor(new Color(117, 117, 117));
        text = "Este jogo foi feito por:\n Marina e Rafael para a disciplina de LPOO";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
    }
    public void drawPauseScreen() {
        //trocando o background
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        //Tela de Pause
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        g2.drawString(text, x, y);

        // Continuar jogo
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 20F));
        text = "Continuar";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
            if(commandNumPause == 0) {
                g2.drawString(">", x-(gp.tileSize * 1/2), y);
        }

        //Configuracoes
        text = "Configurações";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3/5;
        g2.drawString(text, x, y);
        if(commandNumPause == 1) {
            g2.drawString(">", x-(gp.tileSize * 1/2), y);
        }
        // Sair do jogo
        text = "Sair do jogo";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3/5;
        g2.drawString(text, x, y);
        if(commandNumPause == 2) {
            g2.drawString(">", x-(gp.tileSize * 1/2), y);
        }

        g2.drawString(text,x, y);
    }

    public void drawDialogueScreen() {
        // criando a janela de dialogo
        int x = gp.tileSize*2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y,width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        // criando uma forma de quebrar a linha, pois o Graphics 2D não reconhece a quebra de linhas.
        for(String line : currentDialogue.split("/n")) {
            g2.drawString(line, x, y);
            y+= 40;

        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width,height, 35, 35);

        c = new Color(255, 255, 255); //RGB white
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25,25);

    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
