package Game;

import javax.swing.*;
import java.awt.*;

public class playerPanel extends JPanel {
    public boolean isOpponent;
    public player Player;
    public JLabel pLabel;
    public JLabel scoreLabel;

    public playerPanel(player p, boolean IsOpponent) {
        Player = p;
        isOpponent = IsOpponent;
        Font font = new Font("宋体", Font.PLAIN, 13);
        pLabel = new JLabel();
        pLabel.setSize(208, 25);
        pLabel.setFont(font);
        scoreLabel = new JLabel();
        scoreLabel.setSize(208, 25);
        scoreLabel.setFont(font);

        pLabel.setHorizontalAlignment(SwingConstants.LEFT);
        pLabel.setPreferredSize(new Dimension(208, 25));
        scoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        scoreLabel.setPreferredSize(new Dimension(208, 25));

        this.setOpaque(true);
        this.add(pLabel);
        this.add(scoreLabel);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(isOpponent) {
            if(Player.Client.opponentID != null) {
                int score = 0;
                try {
                    score = database.getScore(Player.Client.opponentID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pLabel.setText("当前对手：" + Player.Client.opponentID);
                scoreLabel.setText("对手分数：" + score);
            }
            else {
                pLabel.setText("当前对手：");
                scoreLabel.setText("对手分数：");
            }
        }
        else {
            int score = 0;
            try {
                score = database.getScore(Player.Client.userID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pLabel.setText("当前用户：" + Player.Client.userID);
            scoreLabel.setText("分数：" + score);
        }
        pLabel.setAlignmentX(SwingConstants.LEFT);
        scoreLabel.setAlignmentX(SwingConstants.LEFT);
    }
}
