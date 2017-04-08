package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable {

    private static final int PORT = 2772;
    private final ServerSocket serverSocket = new ServerSocket(PORT);

    private BlockingQueue queue = new LinkedBlockingQueue();
    private ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 100, 1000, TimeUnit.MILLISECONDS, queue);

    Set<ClientHandler> handlers = new HashSet<>();

    public Server() throws IOException {

    }

    @Override
    public void run() {

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("NEW ACCEPT");
                    ClientHandler handler = new ClientHandler(clientSocket, this);
                    handlers.add(handler);
                    pool.execute(handler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Thread.interrupted()) {
                    break;
                }
            }


    }


    public static void main(String[] args) throws IOException {
        new Server().run();
    }


}
