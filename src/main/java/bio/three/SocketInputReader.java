package bio.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketInputReader implements Runnable{
    private Socket socket;

    public SocketInputReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println(Thread.currentThread().getName() + "：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
