package entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.Panel;
import assets.Assets;
import entities.Player;
import inputs.Keyboard;

public class Player extends Entity{
    
    private Assets assets;
    private Keyboard inputs;
    
    private int[] framez = {8,2};
    private BufferedImage[] walkAnimation;
    private int nextFrame = 0, timer = 0;

    
    private boolean flip = false;    
    private int playerDeltaX, playerDeltaY;
    private boolean[] movement;  // UP, DOWN, LEFT, RIGHT;

    public Player(Panel panel,int xPos, int yPos ) {
        super(xPos, yPos);
        playerDeltaX = 0; playerDeltaY = 0;
        movement = new boolean[4];
        for (int i = 0; i < movement.length; i++) movement[i] = false;

        assets = new Assets();
        // player = 
        inputs = new Keyboard(this);
        panel.addKeyListener(inputs);

        loadAnimations();
    }

    public void loadAnimations() {
        walkAnimation = assets.animate(assets.getImage("Soldier/Walk.png"), framez[0]);
        
    }


    public void update() {
        
    }

    public void render() {
        
    }

    public void drawPlayer(Graphics image){
        // walkAnimation = player.getWalkAnimation();
        int size = 64 * 3;
        int value = 3;
        if (movement[0]) {
            playerDeltaY -= value;
            image.drawImage(flipImage(walkAnimation[animFramez(getFramez()[0])], flip), playerDeltaX, playerDeltaY, size, size, null);
        } else if (movement[1]) {
            playerDeltaY += value;
            image.drawImage(flipImage(walkAnimation[animFramez(getFramez()[0])], flip), playerDeltaX, playerDeltaY, size, size, null);
        } else if (movement[2]) {
            playerDeltaX -= value;
            flip = true;
            image.drawImage(flipImage(walkAnimation[animFramez(getFramez()[0])], flip), playerDeltaX, playerDeltaY, size, size, null);
        } else if (movement[3]) {
            flip = false;
            playerDeltaX += value;
            image.drawImage(flipImage(walkAnimation[animFramez(getFramez()[0])], flip), playerDeltaX, playerDeltaY, size, size, null);
        } else { image.drawImage(flipImage(walkAnimation[0], flip), playerDeltaX, playerDeltaY, size, size, null); }

    }

    private BufferedImage flipImage(BufferedImage image, boolean state) {
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

    public int animFramez(int framez) {
        timer++;
        if (timer > (120/framez)) { // 120 fps / 8 num of animations per second
            timer = 0;
            nextFrame++;
        }
        if (nextFrame>=framez) nextFrame = 0;
        return nextFrame;
    }

    public void toggleMovement(int motionIndex, boolean state){ 
        movement[motionIndex] = state; 
    }

    

    public int[] getFramez() { return framez; }
    public BufferedImage[] getWalkAnimation() { return walkAnimation; }
    
}

//