package 第七个实例反编组;

import javax.xml.bind.annotation.*;

/**
 * Created by james on 2017/5/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Body")
public class RvsCancelResponseBody {

    @XmlElement(name = "RvsCancelResponse")
    private RvsCancelResponse rvsCancelResponse;

    public RvsCancelResponse getRvsCancelResponse() {
        return rvsCancelResponse;
    }

    public void setRvsCancelResponse(RvsCancelResponse rvsCancelResponse) {
        this.rvsCancelResponse = rvsCancelResponse;
    }

    @Override
    public String toString() {
        return "RvsCancelResponseBody{" +
                "rvsCancelResponse=" + rvsCancelResponse +
                '}';
    }
}
