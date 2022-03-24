package java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Lambda {
    /**
     * 方法引用 ::
     * 将对象的方法作为一个函数式接口返回，使用语法为目标引用类型在前，方法在后
     * 要求：目标类型是一个函数式接口。
     */
    @Test
    public void test(){
        Person person = new Person("张哥");
        Person person2 = new Person("万哥");
        //得到Person类中 getName，将不同的Person实例传入getName中得到不同的实例Name值
        Function<Person, String> getName = Person::getName;
        System.out.println(getName.apply(person2));
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        list.forEach(System.out::println);
    }

    @Test
    public void test2(){
        //Lambda写法
        Comparator<Integer> comparator = (o1,o2) -> {
            return o1.compareTo(o2);
        };
        System.out.println(comparator.compare(12,3));

        //方法引用写法
        Comparator<Integer> com2 = Integer::compareTo;
        System.out.println(com2.compare(1,2));
    }

    @Test
    public void test3(){
        Consumer<Integer> con = n -> System.out.println("输入为:" + n);
        con.accept(23);

        System.out.println("==========================");

        Supplier<String> sup = () -> "12";
        System.out.println(sup.get());

        System.out.println("============================");

        Predicate<String> pre = t -> t.contains("a");

        System.out.println(pre.test("asd"));
        System.out.println(pre.test("sd"));
        System.out.println(pre.test("asad"));
    }



}
