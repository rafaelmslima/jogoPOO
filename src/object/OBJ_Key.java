package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    public OBJ_Key () {
        name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        //solidArea.x = 5 //Só faço isso se quiser alterar o valor do tamanho da area solidade um único objeto
    }

}
