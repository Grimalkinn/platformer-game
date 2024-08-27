package Main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import entities.Player;

public class Panel extends JPanel{

    private Player player;
    
    public Panel(){
        setPreferredSize(new Dimension(1000, 600));
        player = new Player(this, 0,0);
    }

    public void paintComponent(Graphics graphics) { // draw on window
        super.paintComponent(graphics);
        player.drawPlayer(graphics);
        updateGame(null);
    }

    public void updateGame(Object object) {
        requestFocus(true);
    }
        

}

//