package Game;

public class playerThread extends Thread {
    public player currentPlayer;

    @Override
    public void run() {
        currentPlayer = new player();
    }

    public void createThread() {
        System.out.println("创建一个线程");
        playerThread thread = new playerThread();
        thread.start();
        System.out.println("线程运行结束");
    }
}
