package main;

import entity.Entity;
import entity.NPC_OldMan;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //funciona como a janela do jogo

    // CONFIGURAÇÕES DE TELA
    final int originalTileSize = 16; //16 x 16 tile - Corresponde ao tamanho do player character, NPCs e mapas;
    final int scale = 3; // Escalar o tamanho dos bonecos para que eles pareçam maiores hoje em dia

    public final int tileSize = originalTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 16; //tamanho final do comprimento da janela no jogo
    public final int maxScreenRow = 12; //tamanho final da altura da janela no jogo
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // Configurações do MAPA
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //Sistema
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound sEffects = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread; // Faz com que um processo fique ocorrendo n vezes por segundo, atualizando a tela;

    // Entity e Object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    // Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    int FPS = 60;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // melhora o desempenho da renderização gráfica em aplicações que envolvem animações ou renderizações frequentes de tela
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject(); // metodo para adicionar outros objetos do jogo
        aSetter.setNPC();
        playMusic(0);
        gameState = playState;
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // aqui que fica o loop do jogo

        double drawInterval = 1000000000/FPS; //0.01666666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) { //enquanto o gameThread existir, continue o jogo

            // 1 UPDATE: atualizar informação sobre a posição do personagem
            update();
            // 2 DRAW: desenhar na tela com a informação atualizada
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/100000; //transformando em milisegundos

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() {
        if(gameState == playState) {
            //PLAYER
            player.update();
            //NPC
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nada agora
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // DEBUG, verificando a performance para o desenho dos tiles na tela
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        //Tile
        tileM.draw(g2);

        //Objetos
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //NPC
        for(int i = 0; i < npc.length; i++) {
            if(npc[i] != null) {
                npc[i].draw(g2);
            }
        }

        //Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        //Debug
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Tempo de desenho: " + passed, 10, 400);
            System.out.println("Tempo de desenho: " + passed);
        }

        g2.dispose(); // metodo essencial para a gestão eficiente dos recursos, usado para liberar qualquer recurso do sistema que o objeto estiver usando;

    }
    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE (int i) {
        //efeitos rapidos de som não precisam de loop
        sEffects.setFile(i);
        sEffects.play();
    }
}
