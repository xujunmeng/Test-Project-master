package 第二个实例反编组;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by james on 2017/5/26.
 */
public class DateAdapter extends XmlAdapter<String, LocalDate>
{

    public LocalDate unmarshal( String date ) throws Exception
    {
        return LocalDate.parse( date );
    }

    public String marshal( LocalDate date ) throws Exception
    {
        return date.toString();
    }

}
