package bio.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true){
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(() -> {
                    InputStream is = null;
                    try {
                        is = socket.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String msg;
                        while (( msg = br.readLine()) != null ){
                            System.out.println(Thread.currentThread().getName() + "：" + msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
