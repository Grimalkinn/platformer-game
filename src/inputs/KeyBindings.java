package inputs;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import Main.Panel;


public class KeyBindings {

    private Panel panel;
    private int step;
    @SuppressWarnings("unused")
    private Action UP, DOWN, LEFT, RIGHT;

    public KeyBindings(Panel panel){
        this.panel = panel;

        step = 8;

        UP = new UpAction();
        DOWN = new DownAction();
        LEFT = new LeftAction();
        RIGHT = new RightAction();

        panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "UP");
        panel.getActionMap().put("UP", UP);
    }

    public class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    public class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.updateLocation(0, step);
        }
    }
    public class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.updateLocation(-step, 0);
        }
    }
    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.updateLocation(step, 0);
        }
    }
    public class noAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            // panel.updateLocation(0, 0);
            // panel.toggleMovement();
        }
    }

    
}

//