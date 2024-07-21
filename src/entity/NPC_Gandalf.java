package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Gandalf extends Entity {
    public NPC_Gandalf(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/gandalf_up_1");
        up2 = setup("/npc/gandalf_up_2");
        down1 = setup("/npc/gandalf_down_1");
        down2 = setup("/npc/gandalf_down_2");
        left1 = setup("/npc/gandalf_left_1");
        left2 = setup("/npc/gandalf_left_2");
        right1 = setup("/npc/gandalf_right_1");
        right2 = setup("/npc/gandalf_right_2");
    }

    public void setDialogue() {
        // Guardando textos de dialogos
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island/nto find the treasure?";
        dialogues[2] = "I used to be a great wizard but now.../nI'm a bit too old for taking an adventure.";
        dialogues[3] = "Well, good luck on you";

    }

    public void setAction() {

        actionLockCounter++;

        // damos o override aqui para poder criar diferentes caracteristicas para cada NPC
        Random random = new Random();
        int i = random.nextInt(100) + 1; // pegamos um valor aleatório entre 1 e 100
        /* aqui tenho um modelo simples de IA em que 25% do tempo ele vai para cada direção:
        direita, cima, esquerda, baixo
         */
        if (actionLockCounter == 120) {

            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void speak() {
        // Posso fazer uma sobrescrita e usar uma fala especifica para o player qualquer
        super.speak();
    }
}
