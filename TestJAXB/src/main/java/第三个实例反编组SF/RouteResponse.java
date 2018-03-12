package 第三个实例反编组SF;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by james on 2017/5/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RouteResponse")
public class RouteResponse {

    @XmlAttribute(name = "mailno")
    private String mailno;

    @XmlElement(name = "Route")
    private List<Route> route;

    public String getMailno() {
        return mailno;
    }

    public void setMailno(String mailno) {
        this.mailno = mailno;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "RouteResponse{" +
                "mailno='" + mailno + '\'' +
                ", route=" + route +
                '}';
    }
}
