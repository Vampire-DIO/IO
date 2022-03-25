package nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {
    private Selector selector;
    private ServerSocketChannel ssChannel;
    private static final int PORT = 8888;
    public Server(){
        try {
            selector = Selector.open();
            ssChannel = ServerSocketChannel.open();
            ssChannel.bind(new InetSocketAddress(PORT));
            ssChannel.configureBlocking(false);
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.listen(); //开始监听
    }

    private void listen() {
        try {
            while (selector.select() > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey sk = iterator.next();

                    if (sk.isAcceptable()){
                        SocketChannel socketChannel = ssChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,  SelectionKey.OP_READ);
                    }else if (sk.isReadable()){
                        readClientData(sk);
                    }
                }
                iterator.remove();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void readClientData(SelectionKey sk) {
        SocketChannel sChannel = null;
        try {
            sChannel = (SocketChannel) sk.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = sChannel.read(buffer);
            if (count > 0){
                buffer.flip();
                String msg = new String(buffer.array(), 0 ,buffer.remaining());
                System.out.println("接收到客户端消息：" + msg);
                //
                sendMsgToAllClient(msg, sChannel);
            }
        }catch (Exception e){

            sk.cancel();
            try {
                System.out.println("---有人离线---" + sChannel.getRemoteAddress());
                sChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private void sendMsgToAllClient(String msg, SocketChannel sChannel) throws Exception{
        System.out.println("服务端开始转发消息，当前处理线程：" + Thread.currentThread().getName());
        for (SelectionKey sk : selector.keys()){
            Channel channel = sk.channel();
            if (channel instanceof SocketChannel && channel !=sChannel){
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                ((SocketChannel) channel).write(buffer);
            }
        }

    }
}
