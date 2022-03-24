package bio.three;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(9999);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardOldestPolicy());
        while (true) {
            Socket socket = null;
            socket = ss.accept();
            poolExecutor.execute(new SocketInputReader(socket));
        }
    }
}
