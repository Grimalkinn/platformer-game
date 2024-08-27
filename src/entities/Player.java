package entities;

import java.awt.image.BufferedImage;

import assets.Assets;

public class Player extends Entity{
    private int[] framez = {8,2};
    private Assets assets;
    private BufferedImage[] walkAnimation;

    public Player(int xPos, int yPos) {
        super(xPos, yPos);
        assets = new Assets();
        loadAnimations();
    }

    public void loadAnimations() {
        walkAnimation = assets.animate(assets.getImage("Soldier/Walk.png"), framez[0]);
        
    }


    public void update() {
        
    }

    public void render() {
        
    }

    

    public int[] getFramez() { return framez; }
    public BufferedImage[] getWalkAnimation() { return walkAnimation; }
    
}

//