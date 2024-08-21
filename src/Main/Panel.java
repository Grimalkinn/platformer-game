package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import inputs.KeyInputs;
import assets.Assets;

public class Panel extends JPanel{
    public BufferedImage walkImg;

    private KeyInputs inputs;
    Assets assets;

    private boolean[] movement;  // UP, DOWN, LEFT, RIGHT;
    private int deltaX, deltaY;
    private int nextFrame = 0, timer = 0;

    public Panel(){
        inputs = new KeyInputs(this);

        assets = new Assets();
        
        addKeyListener(inputs);
        setPreferredSize(new Dimension(1000, 600));


        deltaX = 0; deltaY = 0;
        
        movement = new boolean[4];
        for (int i = 0; i < movement.length; i++) movement[i] = false;
        
    }


    public void navigate(int value){
        if (movement[0]) deltaY -= value;
        else if (movement[1]) deltaY += value;
        else if (movement[2]) deltaX -= value;
        else if (movement[3]) deltaX += value;
        // else deltaX = deltaY = 0;
    }

    public void toggleMovement(int motionIndex, boolean state){ 
        movement[motionIndex] = state; 
    }

    public void updateLocation(int xval, int yval) {
        deltaX += xval;
        deltaY += yval;
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
        if (nextFrame>=assets.walkAnim.length) nextFrame = 0;
        return nextFrame;
    }
    
    public void paintComponent(Graphics image) { // draw on window
        requestFocus(true);
        super.paintComponent(image);

        int size = 64 * 3;
        
        navigate(2);
        image.drawImage(assets.walkAnim[animFramez(8)], 100+deltaX, 100+deltaY,size, size, null);

    }


}
