package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyInputs;

public class Panel extends JPanel{
    private int i = 0, timer = 0;
    public BufferedImage walkImg;
    private BufferedImage[] images;
    private KeyInputs inputs;

    private int deltaX, deltaY;
    private boolean[] movement;  // UP, DOWN, LEFT, RIGHT;

    public Panel(){
        inputs = new KeyInputs(this);
        
        addKeyListener(inputs);
        setPreferredSize(new Dimension(1000, 600));

        deltaX = 0; deltaY = 0;
        
        movement = new boolean[4];
        for (int i = 0; i < movement.length; i++) movement[i] = false;
        
        // load images
        walkImg = loadImg("Soldier/Walk.png");
    }


    public void navigate(int value){
        if (movement[0]) deltaY -= value;
        if (movement[1]) deltaY += value;
        if (movement[2]) deltaX -= value;
        if (movement[3]) deltaX += value;
    }

    public void toggleMovement(int motionIndex, boolean state){ movement[motionIndex] = state; }

    
    private BufferedImage loadImg(String filename) {
        InputStream IS = getClass().getResourceAsStream("/assets/sprites2D/"+filename);
        try {
            BufferedImage img = ImageIO.read(IS);
            return img;
        } catch (Exception ex) {
            System.out.println("Error::loadImg::["+ex+"]");
            return null;
        } 
    }
   
        
    public void updateGame(Object object) {
        int res = 64;
        
        // BufferedImage walkSubImg = walkImg.getSubimage(0,0,res,res);
        // // image.drawImage(walkSubImg, 0, 0,size, size, null);
        // image.drawImage(walkImg.getSubimage(0,0,res,res), res, 0,size, size, null);
        // image.drawImage(walkImg.getSubimage(64,0,res,res), 0+inputs.getPos()[0], 0+inputs.getPos()[1],size, size, null);
        
        //animation
        images = new BufferedImage[8];
        for (int i = 0; i < images.length; i++) {
            images[i] = walkImg.getSubimage(res*i,0,res,res);
        }
                
        timer++;
        if (timer > 15) { // 120 fps / 8 num of animations per second
            timer = 0;
            i++;
        }
        if (i>=images.length) i = 0;

        repaint();
        
    }
    
    public void paintComponent(Graphics image) { // draw on window
        super.paintComponent(image);

        int size = 64 * 3;
        
        navigate(2);
        image.drawImage(images[i], deltaX, deltaY,size, size, null);

    }


}
