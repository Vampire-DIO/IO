package bio.two;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("请输入: ");
                ps.println(sc.nextLine());
                ps.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
