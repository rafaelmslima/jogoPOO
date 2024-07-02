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
     public int checkObject(Entity entity, boolean player) {
         /* nesse método, vamos verificar se o player está tocando em algum objeto e se ele estiver
         vamos retornar o index do objeto. Processando a reação correta.
          */
         int index = 999;

         for (int i = 0; i < gp.obj.length; i++) {
             if(gp.obj[i] != null) {
                 //pegar a posição da área solida de Entity
                 entity.solidArea.x = entity.worldX + entity.solidArea.x;
                 entity.solidArea.y = entity.worldY + entity.solidArea.y;
                 // Pegar a posição da área sólida do objeto
                 gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                 gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                 switch (entity.direction) {
                     /* o método intersects da classe rectangle faz uma checagem se 2 retangulos estão
                     se chocando. Para usá-lo, precisamos saber se as duas áreas estão se chocando
                      */
                     case "up":
                         entity.solidArea.y -= entity.speed;
                         if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                             if(gp.obj[i].collision == true) {
                                 entity.collisionOn = true;
                             }
                             if (player == true) {
                                 index = i;
                             }
                         }
                         break;
                     case "down":
                         entity.solidArea.y += entity.speed;
                         if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                             if(gp.obj[i].collision == true) {
                                 entity.collisionOn = true;
                             }
                             if (player == true) {
                                 index = i;
                             }
                         }
                         break;
                     case "left":
                         entity.solidArea.x -= entity.speed;
                         if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                             if (gp.obj[i].collision == true) {
                                 entity.collisionOn = true;
                             }
                             if (player == true) {
                                 index = i;
                             }
                         }
                         break;
                     case "right":
                         entity.solidArea.x += entity.speed;
                         if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                             if(gp.obj[i].collision == true) {
                                 entity.collisionOn = true;
                             }
                             if (player == true) {
                                 index = i;
                             }
                         }
                         break;
                 }
                 entity.solidArea.x = entity.solidAreaDefaultX;
                 entity.solidArea.y = entity.solidAreaDefaultY;
                 gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                 gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
             }
         }

         return index;
     }


     //checando NPC ou monster collision
     public int checkEntity(Entity entity, Entity[] target) {
         /* nesse método, vamos verificar se o player está tocando em algum objeto e se ele estiver
         vamos retornar o index do objeto. Processando a reação correta.
          */
         int index = 999;

         for (int i = 0; i < target.length; i++) {
             if(target[i] != null) {
                 //pegar a posição da área solida de Entity
                 entity.solidArea.x = entity.worldX + entity.solidArea.x;
                 entity.solidArea.y = entity.worldY + entity.solidArea.y;
                 // Pegar a posição da área sólida do objeto
                 target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                 target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                 switch (entity.direction) {
                     /* o método intersects da classe rectangle faz uma checagem se 2 retangulos estão
                     se chocando. Para usá-lo, precisamos saber se as duas áreas estão se chocando
                      */
                     case "up":
                         entity.solidArea.y -= entity.speed;
                         if(entity.solidArea.intersects(target[i].solidArea)) {
                                 entity.collisionOn = true;index = i;}break;
                     case "down":
                         entity.solidArea.y += entity.speed;
                         if(entity.solidArea.intersects(target[i].solidArea)) {
                                 entity.collisionOn = true;index = i;}break;
                     case "left":
                         entity.solidArea.x -= entity.speed;
                         if(entity.solidArea.intersects(target[i].solidArea)) {
                                 entity.collisionOn = true; index = i;}break;
                     case "right":
                         entity.solidArea.x += entity.speed;
                         if(entity.solidArea.intersects(target[i].solidArea)) {
                                 entity.collisionOn = true;index = i;}break;
                 }
                 entity.solidArea.x = entity.solidAreaDefaultX;
                 entity.solidArea.y = entity.solidAreaDefaultY;
                 target[i].solidArea.x = target[i].solidAreaDefaultX;
                 target[i].solidArea.y = target[i].solidAreaDefaultY;
             }
         }

         return index;
     }
     public void checkPlayer(Entity entity) {

         //pegar a posição da área solida de Entity
         entity.solidArea.x = entity.worldX + entity.solidArea.x;
         entity.solidArea.y = entity.worldY + entity.solidArea.y;
         // Pegar a posição da área sólida do objeto
         gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
         gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

         switch (entity.direction) {
                     /* o método intersects da classe rectangle faz uma checagem se 2 retangulos estão
                     se chocando. Para usá-lo, precisamos saber se as duas áreas estão se chocando
                      */
             case "up":
                 entity.solidArea.y -= entity.speed;
                 if(entity.solidArea.intersects(gp.player.solidArea)) {
                     entity.collisionOn = true;}break;
             case "down":
                 entity.solidArea.y += entity.speed;
                 if(entity.solidArea.intersects(gp.player.solidArea)) {
                     entity.collisionOn = true;}break;
             case "left":
                 entity.solidArea.x -= entity.speed;
                 if(entity.solidArea.intersects(gp.player.solidArea)) {
                     entity.collisionOn = true;}break;
             case "right":
                 entity.solidArea.x += entity.speed;
                 if(entity.solidArea.intersects(gp.player.solidArea)) {
                     entity.collisionOn = true;}break;
         }
         entity.solidArea.x = entity.solidAreaDefaultX;
         entity.solidArea.y = entity.solidAreaDefaultY;
         gp.player.solidArea.x = gp.player.solidAreaDefaultX;
         gp.player.solidArea.y = gp.player.solidAreaDefaultY;
     }
}

