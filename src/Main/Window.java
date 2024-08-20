package Main;

import javax.swing.JFrame;

public class Window implements Runnable{
    private final int FPS_CAP = 120;
    private final int UPS_CAP = 120;
    private Panel panel;

    public Window(){ // consists of the frame and the frame contains the panel and the panel contains components
        panel = new Panel();
        new Frame(panel);
        panel.requestFocus();
        new Thread(this).start();
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_CAP;
        double timePerUpdate = 1_000_000_000.0 / UPS_CAP;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        long previousTime = System.nanoTime();

        // @SuppressWarnings("unused")
        int frames = 0;
        // @SuppressWarnings("unused")
        int updates = 0;
        
        long lastCheck = System.currentTimeMillis();
        double deltsU = 0;

        while (true) {
            now = System.nanoTime();
            long currentTime = System.nanoTime();

            deltsU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;

            if (deltsU >= 1){
                panel.updateGame(null); 
                updates++;
                deltsU--;
            } 

            if (now - lastFrame >= timePerFrame){
                panel.repaint();
                lastFrame = now;
                frames++;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("[FPS: "+frames+"]|[UPS: "+updates+"]");
                frames = 0;
                // System.out.print("\033[H\033[2J"); System.out.flush();
        }
         
        }
        
    }
    
}


class Frame {
    private JFrame frame;

    public Frame(Panel panel){
        frame = new JFrame("Most bad ass game!");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}




//