package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;

import entities.Player;


public class Keyboard implements KeyListener { // rating /10

    private HashMap<String, String> map;
    public Player player;
    private String[] keyBindings = {"UP","DOWN","LEFT","RIGHT"};
    
    public Keyboard(Player player) {
        map = new HashMap<>();
        this.player = player;
        readKeybinds("user_key_bindings.txt");
        System.out.println(map);
    }

    
    @Override
    public void keyPressed(KeyEvent e) { this.toggleKeyPress(e, true); }
    
    @Override
    public void keyReleased(KeyEvent e) { this.toggleKeyPress(e, false); }
    
    @Override
    public void keyTyped(KeyEvent e) { 
        //System.out.println("["+e.getKeyChar()+"]"); 
    }


    private void readKeybinds(String filename) {
        try(FileReader reader = new FileReader(filename)) {
            Properties properties = new Properties();
            properties.load(reader);

            for (int i = 0; i < keyBindings.length; i++) 
                map.put(keyBindings[i], properties.getProperty(keyBindings[i]));
            
        } catch (Exception ex) {
            System.out.println("Err::mapConfig::["+ex+"]");
        }

    }

    public void toggleKeyPress(KeyEvent e, boolean state){
        String action = (String.valueOf(e.getKeyChar())).toUpperCase();
        for (int i = 0; i < keyBindings.length; i++) {
            if (action.equals(map.get(keyBindings[i]))){  player.toggleMovement(i, state); break; }
        }
    }
               

    public HashMap<String, String> getKeybindings() { return map; }

    
}

//