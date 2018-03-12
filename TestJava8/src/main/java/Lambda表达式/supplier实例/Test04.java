package Lambda表达式.supplier实例;

import com.google.common.base.Supplier;

/**
 * Created by james on 2017/10/24.
 */
public class Test04 {

    public static Student employeeMaker() {
        return new Student("A", 2);
    }

    public static void main(String[] args) {
        Supplier<Student> employeeMaker = Test04::employeeMaker;

        for (int i = 0; i < 10; i++) {
            System.out.println("#" + i + ": " + employeeMaker.get());
        }
    }

}
class Student {
    public String name;
    public double gpa;

    public Student() {
    }

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + ": " + gpa;
    }
}