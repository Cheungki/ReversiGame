package Game;

import javax.swing.*;
import java.awt.*;

public class infoPanel extends JPanel {
    public player Player;
    public JLabel infoLabel;

    public infoPanel(player p) {
        Player = p;
        Font font = new Font("宋体", Font.PLAIN, 13);
        infoLabel = new JLabel();
        infoLabel.setSize(208, 25);
        infoLabel.setFont(font);
        this.add(infoLabel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(Player.Client.opponentID != null) {
            int color = Player.MainMenu.GamePanel.getColor();
            if(color == 1) {
                infoLabel.setText("您执白子");
            }
            else if(color == -1) {
                infoLabel.setText("您执黑子");
            }
            else infoLabel.setText("");
        }
    }
}
