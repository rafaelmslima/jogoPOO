package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
     public CollisionChecker (GamePanel gp) {
         this.gp = gp;
     }

     public void checkTile(Entity entity) {
         //precisamos checar se o left x, right x, topY e bottomY estão em contato com alguma área sólida
         int entityLeftWorldX = entity.worldX + entity.solidArea.x;
         int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
         int entityTopWorldY = entity.worldY + entity.solidArea.y;
         int entityBottomWorldy = entity.worldY + entity.solidArea.y + entity.solidArea.height;

         int entityLeftCol = entityLeftWorldX / gp.tileSize;
         int entityRightCol = entityRightWorldX / gp.tileSize;
         int entityTopRow = entityTopWorldY / gp.tileSize;
         int entityBottomRow = entityBottomWorldy / gp.tileSize;
         int tileNum1, tileNum2;

         switch (entity.direction) {
             case "up":
                 entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                 tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                 tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                 if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                     entity.collisionOn = true;
                 }
                 break;
             case "down":
                 entityBottomRow = (entityBottomWorldy + entity.speed) / gp.tileSize;
                 tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                 tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                 if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                     entity.collisionOn = true;
                 }
                 break;
             case "left":
                 entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                 tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                 tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                 if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                     entity.collisionOn = true;
                 }
                 break;
             case "right":
                 entityRightCol= (entityRightWorldX + entity.speed) / gp.tileSize;
                 tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                 tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                 if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                     entity.collisionOn = true;
                 }
                 break;
         }
     }
}
