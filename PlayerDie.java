package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class PlayerDie extends JLayeredPane {
    private JLabel dieLabel;

    public PlayerDie(char color, int rank) throws Exception {

        dieLabel = new JLabel();

        Resources r = new Resources.getInstance();

        ImageIcon icon = new ImageIcon(r.getPlayerDie(color, (rank - 1)));

        dieLabel.setIcon(icon);
        add(dieLabel, new Integer(1));
        dieLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
    }
}
