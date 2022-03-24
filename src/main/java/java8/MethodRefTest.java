package java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodRefTest {

    @Test
    public void test1(){
        Consumer<String> con = str -> System.out.println(str);

        con.accept("ads");
    }
    /**
     * 方法引用的使用总结
     * 1. 对象实例 :: 实例方法
     * 2. 类 :: 静态方法
     * 3. 类 :: 实例方法
     */

    // 情况1 对象实例 :: 实例方法
    @Test
    public void test2(){
        PrintStream out = System.out;
        Consumer<String> con = out :: println;

        con.accept("实例 :: 实例方法");
    }

    // 情况2 类 :: 静态方法
    @Test
    public void test3(){
        Comparator<Integer> com = Integer :: compare;
        System.out.println(com.compare(23, 12));
    }

    // 情况3  类 :: 实例方法
    @Test
    public void test4(){
        Comparator<Integer> com = Integer :: compareTo;
        System.out.println(com.compare(3,12));
    }

    @Test
    public void test5(){
        Function<String,Person> function = new Function<String, Person>() {
            @Override
            public Person apply(String s) {
                return new Person(s);
            }
        } ;
        System.out.println(function.apply("历史").getName());


        Function<String , Person> fun = Person::new;
        Person person = fun.apply("zhangsan");
        System.out.println(person.getName());
    }

}
