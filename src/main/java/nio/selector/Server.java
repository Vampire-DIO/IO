package nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Server {
    public static void main(String[] args) throws Exception{
        //1、获取服务端通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2、切换为非阻塞模式
        ssChannel.configureBlocking(false);
        //3、绑定连接端口
        ssChannel.bind(new InetSocketAddress(9999));
        //4、获取selector
        Selector selector = Selector.open();
        //5、将通道注册到selector上
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6、使用selector轮询就绪事件
        while (selector.select() > 0){
            //7、获取选择器中所有注册的通道中已就绪的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //8、 遍历这些事件
            while (iterator.hasNext()){
                System.out.println("正在处理事件...");
                //9、提取当前事件
                SelectionKey sk = iterator.next();
                //10、判断当前事件类型
                if (sk.isAcceptable()){
                    // 获取当前客户端连接建立的socket通道
                    SocketChannel socketChannel = ssChannel.accept();
                    // 将该通道设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 将该通道注册到selector中，并监听他的读事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if (sk.isReadable()){
                    // 获取当前通道
                    SocketChannel channel = (SocketChannel) sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = channel.read(buffer)) > 0){
                        buffer.flip();
                        System.out.println(new String(buffer.array(),0,buffer.remaining()));
                        buffer.clear();
                    }
                }
                iterator.remove();
            }
        }



    }
}
