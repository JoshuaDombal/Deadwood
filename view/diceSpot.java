/*
package view;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import java.io.File;

public class diceSpot
    extends JLayeredPane
    implements model.diceSpot.Listener
{

    private JLabel dice[] = new dice[8];
    private String names[] = new name[8];

    public diceSpot(int x, int y, int h, model.Room r){
        names = {"Player1","Player2","Player3","Player4","Player5","Player6","Player7","Player8"};

        if(r.getName().equals("trailer")){
            setBounds(1000,265,40,160);
            int z = 0;
            for(int i = 0; i < 7; i++){
                dice = new JLabel();
                dice.setVisible(false);
                add(dice, new Integer(0));
                dice.setBounds(z,0,40,40);
                z = z + 20;
            }
        }else{
            setBounds(x,y+h,40,160);
            int z = 0;
            for(int i = 0; i < 7; i++){
                dice = new JLabel();
                dice.setVisible(false);
                add(dice, new Integer(0));
                dice.setBounds(z,0,40,40);
                z = z + 20;
            }
        }


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

    public void changed (model.Room r){

        for(int i = 0; i < 7; i++){
            if(r.checkPlayer(names[i])){
                dice[i].setIcon(getIcon(r.getPlayer()));
                dice[i].setVisible(true);
            }else{
                dice[i].setVisible(false);
            }
            i++;
        }
    }

}
