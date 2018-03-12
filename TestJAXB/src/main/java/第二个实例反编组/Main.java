package 第二个实例反编组;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by james on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("persons.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance( Persons.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Persons persons = (Persons)jaxbUnmarshaller.unmarshal( file );
            System.out.println( persons );

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
