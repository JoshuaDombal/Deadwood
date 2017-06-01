package controller;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class Role extends JPanel {

    private model.Role role;

    public Role(int x, int y, int h, int w, model.Role r){
        setBounds(x,y,h,w);
        setOpaque(false);
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                clicked();
            }
        });
        role = r;
    }

    private void clicked(){
        if(role.checkForPlayer()){
            role.abandon();
        }else{
            role.take();
        }
    }

}
