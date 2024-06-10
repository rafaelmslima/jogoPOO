package main;

import entity.Player;
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
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    Thread gameThread; // Faz com que um processo fique ocorrendo n vezes por segundo, atualizando a tela;
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // melhora o desempenho da renderização gráfica em aplicações que envolvem animações ou renderizações frequentes de tela
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose(); // metodo essencial para a gestão eficiente dos recursos, usado para liberar qualquer recurso do sistema que o objeto estiver usando;

    }
}
