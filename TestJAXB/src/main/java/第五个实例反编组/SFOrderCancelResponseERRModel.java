package 第五个实例反编组;

import javax.xml.bind.annotation.*;

/**
 * Created by james on 2017/5/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class SFOrderCancelResponseERRModel {

    @XmlAttribute(name = "service")
    private String service;

    @XmlElement(name = "Head")
    private String head;

    @XmlElement(name = "ERROR")
    private ErrorBody error;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public ErrorBody getError() {
        return error;
    }

    public void setError(ErrorBody error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SFOrderCancelResponseERRModel{" +
                "service='" + service + '\'' +
                ", head='" + head + '\'' +
                ", error=" + error +
                '}';
    }
}
