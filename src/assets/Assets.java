package assets;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Assets {

    public BufferedImage[] walkAnim, idleAnim;
    public BufferedImage walk, idle;

    public Assets(){
        loadImg();
        loadAnim();
    }

    private void loadImg(){
        walk = getImg("Soldier/Walk.png");
        idle = getImg("Soldier/Idle.png");
    }
    private void loadAnim(){
        walkAnim = prepAnimation(walk, 8);
        idleAnim = prepAnimation(idle, 9);
    }

    private BufferedImage getImg(String filename) {
        InputStream IS = getClass().getResourceAsStream("/assets/sprites2D/"+filename);
        try {
            BufferedImage img = ImageIO.read(IS);
            return img;
        } catch (Exception ex) {
            System.out.println("Error::getImg::["+ex+"]");
            return null;
        } 
    }


    public BufferedImage[] prepAnimation(BufferedImage image, int framez) {
        int res = 64;
        BufferedImage[] animation = new BufferedImage[framez];
        for (int i = 0; i < animation.length; i++) {
            animation[i] = image.getSubimage(res*i,0,res,res);
        }
  
        return animation;
    }

    
}

//