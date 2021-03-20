package Server;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class serverThread extends Thread {
    private Socket socket;

    serverThread(Socket s) {
        socket = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while((message = br.readLine()) != null) {
                System.out.println(message);
                String[] messages = message.split(" ");
                if (messages[0].equals("0")) {
                    server.add(messages[1], socket);
                }
                else if (messages[0].equals("1")) {
                    server.insert(messages[1], socket);
                }
                else if (messages[0].equals("2")) {
                    server.remove(messages[1]);
                }
                else if (messages[0].equals("3") || messages[0].equals("4")) {
                    Socket des = server.get(messages[2]);
                    if(des != null) sendMessage(message, des);
                }
                else {
                    System.out.println("[Fail to resolve message: " + message + "]");
                }
                Vector<String> vec = server.match();
                for (int i = 0; i < vec.size(); i += 2) {
                    String player1 = vec.get(i);
                    System.out.println("Player 1: " + player1);
                    String player2 = vec.get(i + 1);
                    System.out.println("Player 2: " + player2);
                    Socket des1 = server.get(player1);
                    Socket des2 = server.get(player2);
                    if(des1 != null && des2 != null) {
                        String feedback1 = "* 2 " + player1;
                        String feedback2 = "* 1 " + player2;
                        sendMessage(feedback1, des2);
                        sendMessage(feedback2, des1);
                        System.out.println("success to connect");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg, Socket dst) throws IOException {
        PrintWriter pw = new PrintWriter(dst.getOutputStream(), true);
        pw.println(msg);
        pw.flush();
    }
}
