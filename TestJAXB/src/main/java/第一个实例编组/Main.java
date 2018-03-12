package 第一个实例编组;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.time.LocalDate;

/**
 * Created by james on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {

        try {

            //初始化Java对象
            Person person = new Person();
            person.setFirstName("net");
            person.setLastName("blogways");
            person.setCity("NanJing");
            person.setPostalCode(210000);
            person.setBirthday(LocalDate.of(2013, 10, 11));

            //初始化 jaxb marshaler
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            /* 设置为格式化输出 */
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

            /* 将java对象 编组 为xml (输出到文件或标准输出) */
            jaxbMarshaller.marshal( person, new File( "person.xml" ) );
            jaxbMarshaller.marshal( person, System.out );

        } catch (Exception e) {
             e.printStackTrace();
        }

    }

}
