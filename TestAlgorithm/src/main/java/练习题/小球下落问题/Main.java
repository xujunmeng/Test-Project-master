package 练习题.小球下落问题;

import org.junit.Test;

/**
 * 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在 第10次落地时，共经过多少米？第10次反弹多高？
 * Created by jgsoft on 2019/2/13.
 */
public class Main {

    @Test
    public void cal() {
        float total = 0;
        float height = 100;

        for (int i = 0; i < 10; i++) {
            System.out.println("当前是第 : " + (i + 1) + " 次落下, 高度是 : " + height);

            total = total + height;

            height = height / 2;

            System.out.println("当前是第 : " + (i + 1) + " 次落下, 下落距离是 : " + total);

        }
    }

}
