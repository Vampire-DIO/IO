package nio.channel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

    @Test
    public  void test(){
        try {
            FileOutputStream os = new FileOutputStream("E:\\code\\learn-demo\\src\\main\\java\\nio\\data02.txt");
            FileChannel channel = os.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello 王哥".getBytes());
            buffer.flip();
            channel.write(buffer);
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read(){
        try {
            FileInputStream fis = new FileInputStream("E:\\code\\learn-demo\\src\\main\\java\\nio\\data02.txt");

            FileChannel channel = fis.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            channel.read(buffer);
            buffer.flip();
            String rs = new String(buffer.array(),0 ,buffer.remaining());
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void copy(){
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\16329\\Pictures\\Saved Pictures\\test.png");

            FileOutputStream fos = new FileOutputStream("C:\\Users\\16329\\Pictures\\Saved Pictures\\copy.png");

            FileChannel isChannel = fis.getChannel();
            FileChannel osChannel = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true){
                buffer.clear();  //保证每次读取源文件时，buffer缓冲区是干净的
                int flag = isChannel.read(buffer);
                if (flag < 0 ){
                    break;
                }
                buffer.flip();   //将限制设置为当前position位置，并将position指向起始处
                osChannel.write(buffer);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
