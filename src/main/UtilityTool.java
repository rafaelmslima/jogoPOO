package main;

import java.awt.*;
import java.awt.image.BufferedImage;
public class UtilityTool {
    // funções que serão usadas de forma a otimizar a performance do jogo devem ser colocadas aqui
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {

        // criando uma lógica para escalar os tiles de forma mais prática e menos trabalhosa
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0,0, width,height, null);
        g2.dispose();
        return scaledImage;
    }

}
