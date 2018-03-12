package 第五个实例反编组;

import javax.xml.bind.annotation.*;

/**
 * Created by james on 2017/5/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ERROR")
public class ErrorBody {

    @XmlValue
    private String error;

    @XmlAttribute(name = "code")
    private String code;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorBody{" +
                "error='" + error + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
