package com.junmeng.entity;
/**
@author junmeng.xu
@date  2016年8月19日下午3:34:23
 */
public class Order {

	//Order实体类中属性名喝orders表中得字段名是不一样的
	private int id;   //id ==>  order_id
	private String orderNo;    //orderNo ==> order_no
	private float price;       //price ==> order_price
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNo=" + orderNo + ", price=" + price
				+ "]";
	}
	
	
	
}
