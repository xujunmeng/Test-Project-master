package list;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by james on 2017/12/20.
 */
public class Main {

    public static void main(String[] args) {

        List<Integer> list1 = Lists.newArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        List<Integer> list2 = Lists.newArrayList();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        List<Integer> list3 = Lists.newArrayList();
        list3.add(4);
        list3.add(5);
        list3.add(6);

        list2.removeAll(list1);

        list3.removeAll(list1);

        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);




    }

}
