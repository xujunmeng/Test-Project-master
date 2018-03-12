package Lambda表达式.supplier实例;

import java.util.function.Supplier;

/**
 * Created by james on 2017/10/24.
 */
public class Test03 {

    private static Employee employee(Supplier<Employee> supplier) {
        return supplier.get();//在没有写构造函数的时候  就需要重写toString方法
    }

    public static void main(String[] args) {
        System.out.println(employee(Employee::new));
    }

}
class Employee {
    @Override
    public String toString() {
        return "A EMPLOYEE";
    }
}