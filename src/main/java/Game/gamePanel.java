package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class gamePanel extends JPanel {
    public static final int ONLINE = 0;
    public static final int PLAYING_OFFLINE_1 = 1;
    public static final int PLAYING_OFFLINE_2 = 2;
    public static final int PLAYING_ONLINE = 3;

    private player Player;
    public game Game;
    private int myColor;
    public int state;
    private ImageIcon img = new ImageIcon("Image/chessboard.png");

    public gamePanel(player p) {
        Player = p;
        Game = new game();
        myColor = 0;
        state = ONLINE;
        this.addMouseEvent(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img.getImage(), 0, 0, img.getIconWidth(), img.getIconHeight(), null);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int x = 44 * i + 5;
                int y = 44 * j + 5;
                if (Game.chessboard[i][j] == game.BLACK) {
                    g2.setColor(Color.black);
                    g2.fillOval(x, y, 34, 34);
                }
                else if (Game.chessboard[i][j] == game.WHITE) {
                    g2.setColor(Color.white);
                    g2.fillOval(x, y, 34, 34);
                }
            }
        }
    }

    public void addMouseEvent(JPanel jpanel) {
        MouseAdapter onPress = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (state == PLAYING_ONLINE && (myColor == Game.color)) {
                    int NomarlizedX = e.getX() / 44;
                    int NomarlizedY = e.getY() / 44;
                    if (Game.CheckAll(NomarlizedX, NomarlizedY, Game.color)) {
                        Game.reverseAll(NomarlizedX, NomarlizedY);
                        Player.Client.sendMessage(NomarlizedX + "," + NomarlizedY, 3);
                        jpanel.repaint();
                        if (Game.IsGameOver()) {
                            if (Game.FindWinner() == myColor) {
                                try {
                                    database.updateScore(Player.Client.userID, true);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(null, "恭喜您获胜了！");
                            }
                            else {
                                try {
                                    database.updateScore(Player.Client.userID, false);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(null, "黑方获胜！");
                            }
                            Game.reset();
                            jpanel.repaint();
                        }
                    }
                }
            }
        };
        jpanel.addMouseListener(onPress);
    }

    public int getColor() {
        return myColor;
    }

    public void setColor(String color) {
        if(color.equals("1")) myColor = 1;
        else myColor = -1;
    }
}