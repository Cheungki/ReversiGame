package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

public class server {
    private ServerSocket listener;
    private static HashMap<String, Socket> clientHashMap = new HashMap<String, Socket>();
    private static HashMap<String, Socket> waitingHashMap = new HashMap<String, Socket>();
    private final int PORT = 3162;

    public server() {
        try {
            listener = new ServerSocket(PORT);
            System.out.println("SERVER SOCKET CREATED SUCCESSFULLY.");
            Socket socket = null;
            while(true) {
                socket = listener.accept();
                System.out.println("Receive a new socket package.");
                serverThread myThread = new serverThread(socket);
                myThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void add(String ID, Socket socket) {
        clientHashMap.put(ID, socket);
    }

    public static void insert(String ID, Socket socket) {
        waitingHashMap.put(ID, socket);
    }

    public static void remove(String ID) {
        clientHashMap.remove(ID);
        waitingHashMap.remove(ID);
    }

    public static Socket get(String ID) {
        return clientHashMap.get(ID);
    }

    public static Vector<String> match() {
        Vector<String> matches = new Vector<String>();
        if (waitingHashMap.size() > 1) {
            matches.addAll(waitingHashMap.keySet());
            if(matches.size() % 2 == 1) {
                String last = matches.get(matches.size() - 1);
                Socket lastSocket = waitingHashMap.get(last);
                matches.removeElementAt(matches.size() - 1);
                waitingHashMap.clear();
                server.insert(last, lastSocket);
            }
            else waitingHashMap.clear();
        }
        return matches;
    }
}
