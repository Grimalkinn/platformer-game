package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;

import Main.Panel;


public class KeyInputs implements KeyListener {

    private HashMap<String, String> map;
    public Panel panel;
    private String[] keyBindings = {"UP","DOWN","LEFT","RIGHT"};
    
    public KeyInputs(Panel panel) {
        map = new HashMap<>();
        this.panel = panel;
        readKeybinds("Keybindings.txt");
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
            if (action.equals(map.get(keyBindings[i]))){  panel.toggleMovement(i, state); }
        }
    }
               

    public HashMap<String, String> getKeybindings() { return map; }

    
}

//