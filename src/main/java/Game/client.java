package Game;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class client {
    private player Player;

    public String userID;
    public String opponentID;

    private final String ip = "127.0.0.1";
    private final int PORT = 3162;

    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    client(String uid, player p) {
        Player = p;
        userID = uid;
        try {
            socket = new Socket(ip, PORT);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("0 " + userID);
            pw.flush();
            receiveMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String message = br.readLine();
                        if(message == null) break;
                        System.out.println(message);
                        String[] messages = message.split(" ");
                        if (messages[0].equals("*")) {
                            setOpponent(messages[2]);
                            Player.MainMenu.GamePanel.setColor(messages[1]);
                            Player.MainMenu.GamePanel.state = gamePanel.PLAYING_ONLINE;
                            String color = messages[1].equals("1") ? "白" : "黑";
                            JOptionPane.showMessageDialog(null, "匹配到对手「" + messages[2] + "」，游戏开始！本局游戏中，您执" + color + "子。");
                            Player.MainMenu.OPanel.repaint();
                        }
                        else if (messages[0].equals("3")) {
                            String[] pos = messages[3].split(",");
                            int x = Integer.parseInt(pos[0]);
                            int y = Integer.parseInt(pos[1]);
                            Player.MainMenu.Game.reverseAll(x, y);
                            Player.MainMenu.GamePanel.repaint();
                            if (Player.MainMenu.Game.IsGameOver()) {
                                if (Player.MainMenu.Game.FindWinner() == Player.MainMenu.GamePanel.getColor()) {
                                    database.updateScore(userID, true);
                                    JOptionPane.showMessageDialog(null, "恭喜您获胜了！");
                                }
                                else {
                                    database.updateScore(userID, false);
                                    JOptionPane.showMessageDialog(null, "很遗憾，您输了！");
                                }
                                Player.MainMenu.Game.reset();
                                Player.MainMenu.GamePanel.repaint();
                                reset();
                            }
                        }
                        else if (messages[0].equals("4")) {
                            database.updateScore(userID, true);
                            JOptionPane.showMessageDialog(null, "对方认输，恭喜您获胜！");
                            Player.MainMenu.Game.reset();
                            Player.MainMenu.GamePanel.repaint();
                            reset();
                        }
                        else {
                            System.out.println("[Fail to resolve message: " + message + "]");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    public void sendMessage(String msg, int type) {
        String message = type + " " + userID + " " + opponentID + " " + msg;
        pw.println(message);
        pw.flush();
    }

    public void setOpponent(String oid) {
        opponentID = oid;
        Player.MainMenu.UPanel.repaint();
    }

    public void reset() {
        opponentID = null;
        Player.MainMenu.GamePanel.state = gamePanel.ONLINE;
    }
}
