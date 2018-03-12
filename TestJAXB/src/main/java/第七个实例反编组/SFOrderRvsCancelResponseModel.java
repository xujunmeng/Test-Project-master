package 第七个实例反编组;

import javax.xml.bind.annotation.*;

/**
 * Created by james on 2017/5/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class SFOrderRvsCancelResponseModel {

    @XmlAttribute(name = "service")
    private String service;

    @XmlElement(name = "Head")
    private String head;

    @XmlElement(name = "Body")
    private RvsCancelResponseBody body;

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

    public RvsCancelResponseBody getBody() {
        return body;
    }

    public void setBody(RvsCancelResponseBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "SFOrderRvsCancelResponseModel{" +
                "service='" + service + '\'' +
                ", head='" + head + '\'' +
                ", body=" + body +
                '}';
    }
}
