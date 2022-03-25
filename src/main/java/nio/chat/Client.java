package nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class Client {

    private Selector selector;
    private static final int PORT = 8888;
    private SocketChannel sChannel;

    public Client(){
        try {
            selector = Selector.open();
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",PORT));
            sChannel.configureBlocking(false);
            sChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("客户端：" + Thread.currentThread().getName() + "准备完成");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();

        new Thread(()->{
            try {
                while (true){
                    client.readInfo();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String msg = sc.nextLine();
            client.sendToServer(msg);
        }
    }

    private void sendToServer(String msg) {
        try {
            sChannel.write(ByteBuffer.wrap(("花哥说: " + msg).getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInfo() throws IOException {
        if (selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()){
                    SocketChannel sChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    sChannel.read(buffer);
                    System.out.println(new String(buffer.array()).trim());
                }
                iterator.remove();
            }
        }
    }
}
