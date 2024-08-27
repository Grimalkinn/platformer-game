package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import inputs.KeyInputs;
import entities.Player;

public class Panel extends JPanel{
    public BufferedImage walkImg;
    private BufferedImage[] walkAnimation;

    private KeyInputs inputs;
    private Player player;

    private boolean[] movement;  // UP, DOWN, LEFT, RIGHT;
    private int playerDeltaX, playerDeltaY;
    private int nextFrame = 0, timer = 0;
    private boolean flip = false;


    public Panel(){
        playerDeltaX = 0; playerDeltaY = 0;

        inputs = new KeyInputs(this);
        addKeyListener(inputs);
        setPreferredSize(new Dimension(1000, 600));
        
        player = new Player(0,0);

        
        movement = new boolean[4];
        for (int i = 0; i < movement.length; i++) movement[i] = false;
        
    }

    public void paintComponent(Graphics image) { // draw on window
        requestFocus(true);
        super.paintComponent(image);
        drawPlayer(image);
    }

    private void drawPlayer(Graphics image){
        walkAnimation = player.getWalkAnimation();
        int size = 64 * 3;
        int value = 3;
        if (movement[0]) {
            playerDeltaY -= value;
            image.drawImage(flipImage(walkAnimation[animFramez(player.getFramez()[0])], flip), playerDeltaX, playerDeltaY, size, size, null);
        } else if (movement[1]) {
            playerDeltaY += value;
            image.drawImage(flipImage(walkAnimation[animFramez(player.getFramez()[0])], flip), playerDeltaX, playerDeltaY, size, size, null);
        } else if (movement[2]) {
            playerDeltaX -= value;
            flip = true;
            image.drawImage(flipImage(walkAnimation[animFramez(player.getFramez()[0])], flip), playerDeltaX, playerDeltaY, size, size, null);
        } else if (movement[3]) {
            flip = false;
            playerDeltaX += value;
            image.drawImage(flipImage(walkAnimation[animFramez(player.getFramez()[0])], flip), playerDeltaX, playerDeltaY, size, size, null);
        } else { image.drawImage(flipImage(walkAnimation[0], flip), playerDeltaX, playerDeltaY, size, size, null); }

    }

    public BufferedImage flipImage(BufferedImage image, boolean state) {
        int x = 0;
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage out = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = out.createGraphics();
        if (state) {
            x = w;
            w *= -1;
        }
        g2d.drawImage(image, x, 0, w, h, null);
        g2d.dispose();

        return out;
    }

    // public BufferedImage flipImage(final BufferedImage image, final boolean horizontal, final boolean vertical) {
    //     int x = 0;
    //     int y = 0;
    //     int w = image.getWidth();
    //     int h = image.getHeight();

    //     BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    //     Graphics2D g2d = out.createGraphics();

    //     if (horizontal) {
    //         x = w;
    //         w *= -1;
    //     }

    //     if (vertical) {
    //         y = h;
    //         h *= -1;
    //     }

    //     g2d.drawImage(image, x, y, w, h, null);
    //     g2d.dispose();

    //     return out;
    // }


    public void toggleMovement(int motionIndex, boolean state){ 
        movement[motionIndex] = state; 
    }

    public void updateLocation(int xval, int yval) {
        playerDeltaX += xval;
        playerDeltaY += yval;
    }

        
    public void updateGame(Object object) {
        
        // for (int i = 0; i < movement.length; i++) movement[i] = false;
        // bindings = new KeyBindings(this);

        
    }

    public int animFramez(int framez) {
        timer++;
        if (timer > (120/framez)) { // 120 fps / 8 num of animations per second
            timer = 0;
            nextFrame++;
        }
        if (nextFrame>=framez) nextFrame = 0;
        return nextFrame;
    }
    
    


}

//