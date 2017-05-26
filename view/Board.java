
package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.*;

public class Board extends JLayeredPane {
    private JLabel boardLabel;

    public Board(model.Board model) throws Exception {

        ImageIcon icon;

        boardLabel = new JLabel();

        Class cls = getClass();
        icon = new ImageIcon(ImageIO.read(cls.getResourceAsStream("board.jpg")));
        boardLabel.setIcon(icon);
        add(boardLabel, new Integer(0));
        boardLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        setBounds(boardLabel.getBounds());

        ArrayList<model.Set> sets = model.getSets();


        //Role r;

        for(int i = 0; i < sets.size()-1; i++){
            model.Set s = sets.get(i);
            System.out.println("got it");
            model.Role[] R = s.getRoles();
            for(int j = 0; j < R.length-1; j++){
                model.Role rs = R[j];
                if(rs == null){
                    break;
                }
                System.out.println(j);
                Role r = new Role(rs.getX(),rs.getY(),rs.getH(),rs.getW(),rs);
                add(r, new Integer(3));
                System.out.println("ROLE NAME " + rs.getName());
            }
        }




        //r = new Role(r.getX(),rs.getY(),rs.getH(),rs.getW(),rs);
        //add(r, new Integer(3));


        /*

        Role r;
        for(model.Set s : sets){
            model.Role[] R = s.getRoles();

            for(model.Role rs : R){
                r = new Role(rs.getX(),rs.getY(),rs.getH(),rs.getW(),rs);
                add(r, new Integer(3));
            }
        }

        */


    }
}
