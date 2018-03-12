package com.aihuishou.service.inventory.model;

import java.io.Serializable;

public class InventoryExtension implements Serializable {

    private Long inventoryId;

    private String inventorySerialNumber;

    private Integer inventoryQuoterPrice;

    private String imei;

    private String inventoryRemark;
    
    private String documentSerialNumber;

    private static final long serialVersionUID = 1L;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getInventoryQuoterPrice() {
        return inventoryQuoterPrice;
    }

    public void setInventoryQuoterPrice(Integer inventoryQuoterPrice) {
        this.inventoryQuoterPrice = inventoryQuoterPrice;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }


    public String getInventoryRemark() {
        return inventoryRemark;
    }

    public void setInventoryRemark(String inventoryRemark) {
        this.inventoryRemark = inventoryRemark;
    }

	public String getInventorySerialNumber() {
		return inventorySerialNumber;
	}

	public void setInventorySerialNumber(String inventorySerialNumber) {
		this.inventorySerialNumber = inventorySerialNumber;
	}
	
	public String getDocumentSerialNumber() {
		return documentSerialNumber;
	}

	public void setDocumentSerialNumber(String documentSerialNumber) {
		this.documentSerialNumber = documentSerialNumber;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryExtension that = (InventoryExtension) o;

        if (inventoryId != null ? !inventoryId.equals(that.inventoryId) : that.inventoryId != null) return false;
        if (inventorySerialNumber != null ? !inventorySerialNumber.equals(that.inventorySerialNumber) : that.inventorySerialNumber != null)
            return false;
        if (inventoryQuoterPrice != null ? !inventoryQuoterPrice.equals(that.inventoryQuoterPrice) : that.inventoryQuoterPrice != null)
            return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        if (inventoryRemark != null ? !inventoryRemark.equals(that.inventoryRemark) : that.inventoryRemark != null)
            return false;
        return documentSerialNumber != null ? documentSerialNumber.equals(that.documentSerialNumber) : that.documentSerialNumber == null;

    }

    @Override
    public int hashCode() {
        int result = inventoryId != null ? inventoryId.hashCode() : 0;
        result = 31 * result + (inventorySerialNumber != null ? inventorySerialNumber.hashCode() : 0);
        result = 31 * result + (inventoryQuoterPrice != null ? inventoryQuoterPrice.hashCode() : 0);
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (inventoryRemark != null ? inventoryRemark.hashCode() : 0);
        result = 31 * result + (documentSerialNumber != null ? documentSerialNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InventoryExtension{" +
                "inventoryId=" + inventoryId +
                ", inventorySerialNumber='" + inventorySerialNumber + '\'' +
                ", inventoryQuoterPrice=" + inventoryQuoterPrice +
                ", imei='" + imei + '\'' +
                ", inventoryRemark='" + inventoryRemark + '\'' +
                ", documentSerialNumber='" + documentSerialNumber + '\'' +
                '}';
    }
}