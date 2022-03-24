package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
    //stream 的四种构造方式
    @Test
    public void test1(){
        // 通过集合
        List<Person> list = new ArrayList<>();
        Stream<Person> stream1 = list.stream();

        // 通过数组
        Person[] peoples = new Person[]{};
        Stream<Person> stream2 = Arrays.stream(peoples);

        // 通过Stream.of
        Stream<Integer> stream3 = Stream.of(1,2,3,4);

        // 通过创建无限流  注：前三种方式都是依赖于数据源创建。第四种是自己创建数据源
        // 迭代 遍历前10个偶数
        Stream.iterate(0,t -> t + 2).limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
