import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.*;

public class CurrentDisplay extends JLayeredPane {
    private JLabel currentPlayerLabel;

    public CurrentDisplay() throws Exception {

        ImageIcon icon;

        currentPlayerLabel = new JLabel();

        Class cls = getClass();
        icon = new ImageIcon(ImageIO.read(cls.getResourceAsStream("b1.jpg")));

        currentPlayerLabel.setIcon(icon);
        add(currentPlayerLabel, new Integer(0));
        currentPlayerLabel.setBounds(1200, 0, 50, 50);
        setBounds(currentPlayerLabel.getBounds());

    }

}
