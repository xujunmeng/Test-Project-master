package 第三个实例反编组SF;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by james on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {

        try {
            File file = new File("SF.xml");
            //File file = new File("SF_ERR.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance( Response.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Response response = (Response)jaxbUnmarshaller.unmarshal( file );
            System.out.println( response );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
