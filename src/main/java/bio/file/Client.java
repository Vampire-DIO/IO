package bio.file;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(".png");
            byte[] buffer = new byte[1024];
            int len;
            InputStream is = new FileInputStream("C:\\Users\\16329\\Pictures\\Saved Pictures\\test.png");
            while ((len = is.read(buffer)) > 0) {
                dos.write(buffer,0, len);
            }
            dos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
