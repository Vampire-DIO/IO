package bio.file;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(8888);
            Socket socket = ss.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String suffix = dis.readUTF();
            OutputStream os = new FileOutputStream("C:\\Users\\16329\\Pictures\\Saved Pictures\\server\\" +
                    UUID.randomUUID().toString() + suffix);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = dis.read(buffer)) > 0){
                os.write(buffer, 0, len);
            }
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
