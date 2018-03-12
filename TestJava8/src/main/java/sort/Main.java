package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by james on 2017/11/8.
 */
public class Main {

    @Test
    public void test() {
        SortXJM o1 = new SortXJM("xjm1", new Date());
        SortXJM o3 = new SortXJM("jm3", new Date());
        SortXJM o4 = new SortXJM("xjm4", new Date());
        SortXJM o2 = new SortXJM("xjm2", new Date());
        List<SortXJM> listObj = new ArrayList<>();
        listObj.add(o1);
        listObj.add(o3);
        listObj.add(o4);
        listObj.add(o2);
        List<SortXJM> x = listObj.stream()
                .filter((a) -> a.getName().startsWith("x"))
                .sorted((a, b) -> b.getCreate().compareTo(a.getCreate()))
                .collect(Collectors.toList());
        System.out.println(x);
    }



}
class SortXJM{
    private String name;
    private Date create;

    public SortXJM(String name, Date create) {
        this.name = name;
        this.create = create;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    @Override
    public String toString() {
        return "SortXJM{" +
                "name='" + name + '\'' +
                ", create=" + create +
                '}';
    }
}