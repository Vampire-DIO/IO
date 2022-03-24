package bio.one;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",9999);
            OutputStream os = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            PrintStream ps = new PrintStream(os);
            while (true){
                System.out.print("输入: ");
                String msg = sc.nextLine();
                ps.println(msg);
                ps.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
