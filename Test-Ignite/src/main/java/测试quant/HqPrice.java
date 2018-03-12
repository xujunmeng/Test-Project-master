package 测试quant;

import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

/**
@author junmeng.xu
@date  2016年9月29日下午5:44:05
 */
public class HqPrice implements Serializable{

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