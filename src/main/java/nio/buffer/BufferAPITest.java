package nio.buffer;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferAPITest {

    @Test
    public void test1(){
        ByteBuffer bf = ByteBuffer.allocate(10);
        bf.put(new byte[]{1,2,3,4,5});
        System.out.println(bf.position());
        System.out.println(bf.get(9));
        Buffer buffer = bf.position(2);
        System.out.println(bf.position());
        System.out.println(bf.get());
        bf.flip();
        System.out.println(bf.get());
    }

}
