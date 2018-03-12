package 数据网格的例子;

import java.io.Serializable;

import javax.cache.Cache.Entry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
@author junmeng.xu
@date  2016年6月14日上午10:40:27
 */
public class Main2 {

	public static void main(String[] args) {
		
		Ignite ignite = Ignition
				.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml");
		
		IgniteCache<Long, HqPrice> cache = ignite.cache("cache_bts_hq_price_hist");
		
		String sql = "select * from HqPrice where tick = '000001'";
		
		QueryCursor<Entry<Long,HqPrice>> cursor = cache.query(new SqlQuery<Long, HqPrice>(HqPrice.class, sql));
		
		for (Entry<Long, HqPrice> entry : cursor) {
			
			Long key = entry.getKey();
			HqPrice value = entry.getValue();
			System.out.println("key : " + key);
			System.out.println("value : " + value);
			
		}
		
		
	}
	
}
class HqPrice implements Serializable{

    @QuerySqlField(index = true, descending = true)
    private String dt;
    @QuerySqlField(index = true, descending = true)
    private String tick;
    @QuerySqlField
    private Double open;
    @QuerySqlField
    private Double close;
    @QuerySqlField
    private Double inc;
    @QuerySqlField
    private Double high;
    @QuerySqlField
    private Double low;
    @QuerySqlField
    private Double vol;

    public HqPrice(String dt, String tick, Double open, Double close, Double high, Double low, Double inc, Double vol) {
        this.dt = dt;
        this.tick = tick;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.inc = inc;
        this.vol = vol;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getTick() {
        return tick;
    }

    public void setTick(String tick) {
        this.tick = tick;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getInc() {
        return inc;
    }

    public void setInc(Double inc) {
        this.inc = inc;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }
}
