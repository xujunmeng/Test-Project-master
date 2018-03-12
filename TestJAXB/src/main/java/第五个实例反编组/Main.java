package 第五个实例反编组;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by james on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("ERR.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance( SFOrderCancelResponseERRModel.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SFOrderCancelResponseERRModel obj = (SFOrderCancelResponseERRModel)jaxbUnmarshaller.unmarshal( file );
            System.out.println( obj );

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
