package com.aihuishou.service.inventory.model;

/**
 * Created by james on 2017/6/29.
 */
public class SaleSkuQuanlity {
    public SaleSkuQuanlity() {
    }

    public static String getSPU(Integer saleSkuId, Integer quanlityId) {
        return saleSkuId + "-" + quanlityId;
    }

    public static Integer getSaleSkuId(String spu) {
        if(spu != null && spu.length() != 0) {
            String[] tmp = spu.split("-");
            return Integer.valueOf(tmp[0]);
        } else {
            return null;
        }
    }

    public static Integer getQuanlityId(String spu) {
        if(spu != null && spu.length() != 0) {
            String[] tmp = spu.split("-");
            return Integer.valueOf(tmp[1]);
        } else {
            return null;
        }
    }
}

