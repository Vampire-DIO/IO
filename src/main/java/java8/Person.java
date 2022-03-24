package java8;

/**
 * @author 16329
 */
public class Person<T> {
    private String name;

    public int comparator(Integer o1, Integer o2){
        return Integer.compare(o1,o2);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }
}
