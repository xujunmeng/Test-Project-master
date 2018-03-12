package com.test;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 使用一个String类型的data变量，取代5个String类型的dt/price/volume/amount/ratio变量
 * 目的在于压缩内存数据
 * 在64位提供中，缓存一天的A股行情数据，前者占用大约800MB内存，后者占用4G内存
 * 
 * 因暂无需求，set方法尚未实现
 * 
 * 该entity仅仅用于行情数据的缓存。扩展请慎重。
 * 
 * @author jimmy.zhou
 *
 */
public class TimeShare implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String data;		// dt|price|volume|amount|ratio  说明：时间(HH:mm:ss)|股价|成交量|成交额|涨跌率
	
	private TimeShare(){
	}
	
	public TimeShare(String dt, String price, String volume, String amount, String ratio) {
		if(dt == null) dt = "";
		if(price == null) price = "";
		if(volume == null) volume = "";
		if(amount == null) amount = "";
		if(ratio == null) ratio = "";
		this.data = String.format("%s|%s|%s|%s|%s|", new Object[]{dt, price, volume, amount, ratio});
	}
	
	public TimeShare(String data) {
		this.data = data;
	}
	
	private String splitData(int index) {
		if(data == null) {
			return null;
		}
		String[] split = StringUtils.split(data, "|");
		if(split.length <= index) {
			return null;
		}
		return split[index];
	}
	
	public String getDt() {
		return splitData(0);
	}
	
	public String getPrice() {
		return splitData(1);
	}
	
	public String getVolume() {
		return splitData(2);
	}
	
	public String getAmount() {
		return splitData(3);
	}
	
	public String getRatio() {
		return splitData(4);
	}
	
	
	// 后期，如果业务需求需要，可扩展实现
//	public void setDt(String dt) {
//		this.dt = dt;
//	}
//	public void setPrice(String price) {
//		this.price = price;
//	}
//	public void setVolume(String volume) {
//		this.volume = volume;
//	}
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}
//	public void setRatio(String ratio) {
//		this.ratio = ratio;
//	}
	
	
	@Override
	public String toString() {
		return "TimeShare [dt=" + getDt() + ", price=" + getPrice() + ", volume="
				+ getVolume() + ", amount=" + getAmount() + ", ratio=" + getRatio() + "]";
	}
	
}
