package 适配器模式.缺省适配模式.实例一;

/**
 * Created by james on 2017/7/12.
 */
public class 鲁智深 extends 天星 {

    @Override
    public void 习武() {
        System.out.println("拳打镇关西；大闹五台山；大闹桃花村；火烧瓦官寺；倒拔垂杨柳；");
    }

    @Override
    public String getName() {
        return "鲁智深";
    }
}
