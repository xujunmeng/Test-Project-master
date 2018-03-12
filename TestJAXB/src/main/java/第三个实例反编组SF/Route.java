package 第三个实例反编组SF;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by james on 2017/5/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Route")
public class Route {

    //路由节点具体描述
    @XmlAttribute(name = "remark")
    private String remark;

    //路由节点发生的时间
    @XmlAttribute(name = "accept_time")
    private String accept_time;

    //路由节点发生的地点
    @XmlAttribute(name = "accept_address")
    private String accept_address;

    //路由节点操作码
    @XmlAttribute(name = "opcode")
    private String opcode;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(String accept_time) {
        this.accept_time = accept_time;
    }

    public String getAccept_address() {
        return accept_address;
    }

    public void setAccept_address(String accept_address) {
        this.accept_address = accept_address;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    @Override
    public String toString() {
        return "Route{" +
                "remark='" + remark + '\'' +
                ", accept_time='" + accept_time + '\'' +
                ", accept_address='" + accept_address + '\'' +
                ", opcode='" + opcode + '\'' +
                '}';
    }
}
