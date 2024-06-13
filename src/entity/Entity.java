package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

//superclasse para os personagens do jogo
public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    //criando a colisão
    public Rectangle solidArea; //Cria um retangulo abstrato que servirá para armazenar os dados de
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

}
