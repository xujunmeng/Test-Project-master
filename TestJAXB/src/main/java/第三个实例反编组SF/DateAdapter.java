package 第三个实例反编组SF;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by james on 2017/5/25.
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    private String pattern = "yyyy-MM-dd HH:mm:ss";

    SimpleDateFormat fmt = new SimpleDateFormat(pattern);

    @Override
    public Date unmarshal(String dateStr) throws Exception {
        return fmt.parse(dateStr);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return fmt.format(date);
    }
}
