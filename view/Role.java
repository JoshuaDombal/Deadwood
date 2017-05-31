package view;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import java.io.File;

public class Role
    extends JLayeredPane
    implements model.Role.Listener
{

    private JLabel dice;

    public Role(int x, int y, int h, int w, model.Role r){
        setBounds(x,y,h,w);

        dice = new JLabel();
        dice.setVisible(false);
        add(dice, new Integer(0));
        dice.setBounds(0,0,h,w);

        r.subscribe(this);
        changed(r);
    }

    private static ImageIcon getIcon(model.Player player){
        //String file = (player.getColor() + player.getRank() + ".png");

        ImageIcon icon = null;
        try{
            Class cls = Role.class;
            icon = new ImageIcon(ImageIO.read(new File(String.format("%c%d.png",  player.getColor(), player.getRank()))));
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return icon;
    }

    public void changed (model.Role r){
        if(r.checkForPlayer()){
            dice.setIcon(getIcon(r.getPlayer()));
        }

        dice.setVisible(r.checkForPlayer());
    }

}
