package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; // quantidade de imagens para o mapa
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/res/maps/map01.txt"); // passando o caminho do mapa como parâmetro
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water00.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            System.out.println("Tentando carregar o mapa: " + filePath);
            InputStream is = getClass().getResourceAsStream(filePath); // importando o arquivo de txt
            if (is == null) {
                throw new IOException("Arquivo de mapa não encontrado: " + filePath);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // lendo o arquivo txt

            for (int row = 0; row < gp.maxScreenRow; row++) {
                String line = br.readLine(); // vai ler uma linha do arquivo txt e colocar na string linha

                if (line == null) {
                    throw new IOException("Linha do mapa está nula, verifique o formato do arquivo: " + filePath);
                }

                String[] numbers = line.split(" "); // ler os números do txt separados por um espaço

                for (int col = 0; col < gp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]); // o readline só lê strings, então é preciso converter para int
                    mapTileNum[col][row] = num;
                }
            }
            br.close();
            System.out.println("Mapa carregado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar o mapa: " + filePath);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Erro ao converter número no mapa: " + filePath);
        }
    }

    public void draw(Graphics2D g2) {
        // desenhando o mapa através de um loop
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
