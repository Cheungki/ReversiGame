package Game;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainMenu {
    public player Player;

    public gamePanel GamePanel;
    public playerPanel UPanel;
    public playerPanel OPanel;
    public infoPanel InfoPanel;
    public JFrame MainMenu;

    public game Game;

    private JMenuBar bar;
    private JMenu start, config;
    private JMenuItem offline, online, robot, fail, exit;

    public mainMenu(player p) {
        Player = p;
        MainMenu = new JFrame("黑白棋");
        MainMenu.setLayout(null);

        GamePanel = new gamePanel(Player);
        GamePanel.setBounds(10, 10, 352, 352);

        UPanel = new playerPanel(Player, false);
        UPanel.setBounds(372, 10, 218, 136);

        OPanel = new playerPanel(Player, true);
        OPanel.setBounds(372, 200, 218, 136);

        InfoPanel = new infoPanel(Player);
        InfoPanel.setBounds(372, 70, 218, 40);

        Game = GamePanel.Game;

        bar = new JMenuBar();

        start = new JMenu("开始");
        offline = new JMenuItem("离线");
        online = new JMenuItem("在线");
        robot = new JMenuItem("人机");
        start.add(online);
        start.add(offline);
        start.add(robot);

        config = new JMenu("其他");
        fail = new JMenuItem("认输");
        exit = new JMenuItem("退出");
        config.add(exit);

        bar.add(start);
        bar.add(config);

        MainMenu.setJMenuBar(bar);
        MainMenu.add(GamePanel);
        MainMenu.add(UPanel);
        MainMenu.add(OPanel);
        MainMenu.setSize(600, 422);
        MainMenu.setVisible(false);
        MainMenu.setResizable(true);
        MainMenu.setLocationRelativeTo(null);
        MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addButtonEventListener();
    }

    private void addButtonEventListener() {
        ActionListener offlineButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        offline.addActionListener(offlineButtonListener);

        ActionListener onlineButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.Client.sendMessage("", 1);
            }
        };
        online.addActionListener(onlineButtonListener);

        ActionListener failButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    database.updateScore(Player.Client.userID, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Player.Client.sendMessage("", 4);
                MainMenu.dispose();
            }
        };
        fail.addActionListener(failButtonListener);

        ActionListener exitButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.Client.sendMessage("", 2);
                if (GamePanel.state == gamePanel.PLAYING_ONLINE) {
                    Player.Client.sendMessage("", 4);
                    try {
                        database.updateScore(Player.Client.userID, false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                MainMenu.dispose();
            }
        };
        exit.addActionListener(exitButtonListener);
    }
}
