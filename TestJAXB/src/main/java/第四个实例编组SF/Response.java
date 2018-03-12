package 第四个实例编组SF;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by james on 2017/5/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class Response {

    @XmlAttribute(name = "service")
    private String service;

    @XmlElement(name = "Head")
    private String head;

    @XmlElementWrapper(name = "Body")
    @XmlElement(name = "RouteResponse")
    private List<RouteResponse> body;

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

    public List<RouteResponse> getBody() {
        return body;
    }

    public void setBody(List<RouteResponse> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Response{" +
                "service='" + service + '\'' +
                ", head='" + head + '\'' +
                ", body=" + body +
                '}';
    }
}
