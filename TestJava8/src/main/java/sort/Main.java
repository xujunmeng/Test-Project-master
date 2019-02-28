package sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by james on 2017/11/8.
 */
public class Main {

    @Test
    public void test() {
        SortXJM o1 = new SortXJM(1, "xjm1", new Date());
        SortXJM o3 = new SortXJM(1, "jm3", new Date());
        SortXJM o4 = new SortXJM(1, "xjm4", new Date());
        SortXJM o2 = new SortXJM(1, "xjm2", new Date());
        List<SortXJM> listObj = new ArrayList<>();
        listObj.add(o1);
        listObj.add(o3);
        listObj.add(o4);
        listObj.add(o2);
        List<SortXJM> x = listObj.stream()
                .filter((a) -> a.getName().startsWith("i"))
                .sorted((a, b) -> b.getCreate().compareTo(a.getCreate()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(x)) {
            System.out.println("sdf");
            return;
        }
        System.out.println(x);
    }

    @Test
    public void test23() {
        SortXJM o1 = new SortXJM(1, "xjm1", new Date());
        SortXJM o3 = new SortXJM(2, "jm3", new Date());
        SortXJM o4 = new SortXJM(1, "xjm4", new Date());
        SortXJM o2 = new SortXJM(1, "xjm2", new Date());
        List<SortXJM> listObj = new ArrayList<>();
        listObj.add(o1);
        listObj.add(o3);
        listObj.add(o4);
        listObj.add(o2);

        Map<Integer, List<SortXJM>> map = new HashMap<>();

        for (SortXJM sortXJM : listObj) {
            Integer id = sortXJM.getId();
            if (map.containsKey(id)) {
                map.get(id).add(sortXJM);
            } else {
                map.put(id, Lists.newArrayList(sortXJM));
            }
        }
        System.out.println(map);

        Map<Integer, List<SortXJM>> collect = listObj.stream().collect(Collectors.groupingBy(a -> a.getId()));
        System.out.println(collect);


    }

}
class SortXJM{
    private Integer id;
    private String name;
    private Date create;

    public SortXJM(Integer id, String name, Date create) {
        this.id = id;
        this.name = name;
        this.create = create;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", create=" + create +
                '}';
    }
}