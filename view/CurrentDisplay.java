package view;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.*;

public class CurrentDisplay extends JLayeredPane {
    private JLabel currentPlayerInfoLabel;

    public CurrentDisplay() throws Exception {

        ImageIcon icon;

        currentPlayerInfoLabel = new JLabel();

        Class cls = getClass();
        icon = new ImageIcon(ImageIO.read(cls.getResourceAsStream("b1.png")));

        currentPlayerInfoLabel.setIcon(icon);
        add(currentPlayerInfoLabel, new Integer(1));
        currentPlayerInfoLabel.setBounds(1300 ,200, icon.getIconWidth(), icon.getIconHeight());
        setBounds(currentPlayerInfoLabel.getBounds());

    }

}
