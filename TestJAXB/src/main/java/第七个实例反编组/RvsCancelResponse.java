package 第七个实例反编组;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by james on 2017/5/26.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RvsCancelResponse")
public class RvsCancelResponse {

    @XmlAttribute(name = "res_status")
    private String resStatus;

    @XmlAttribute(name = "orderid")
    private String orderid;

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Override
    public String toString() {
        return "RvsCancelResponse{" +
                "resStatus='" + resStatus + '\'' +
                ", orderid='" + orderid + '\'' +
                '}';
    }
}
