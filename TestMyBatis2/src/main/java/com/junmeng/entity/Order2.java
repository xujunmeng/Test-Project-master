package com.junmeng.entity;

/**
 * Created by junmeng.xu on 2016/11/21.
 */
public class Order2 {
    private int order_id;
    private String order_no;
    private float order_price;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public float getOrder_price() {
        return order_price;
    }

    public void setOrder_price(float order_price) {
        this.order_price = order_price;
    }

    @Override
    public String toString() {
        return "Order2{" +
                "order_id=" + order_id +
                ", order_no='" + order_no + '\'' +
                ", order_price=" + order_price +
                '}';
    }
}
