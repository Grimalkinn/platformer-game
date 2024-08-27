package assets;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Assets {

    // public BufferedImage[] walkAnim, idleAnim;
    // public BufferedImage walk, idle;

    public Assets(){

    }

    public BufferedImage getImage(String filename) {
        InputStream IS = getClass().getResourceAsStream("/assets/sprites2D/"+filename);
        try {
            BufferedImage img = ImageIO.read(IS);
            return img;
        } catch (Exception ex) {
            System.out.println("Error::getImg::["+ex+"]");
            return null;
        } 
    }


    public BufferedImage[] animate(BufferedImage image, int framez) {
        int res = 64;
        BufferedImage[] animation = new BufferedImage[framez];
        for (int i = 0; i < animation.length; i++) {
            animation[i] = image.getSubimage(res*i,0,res,res);
        }
  
        return animation;
    }

    
}

//