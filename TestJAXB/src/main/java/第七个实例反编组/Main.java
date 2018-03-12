package 第七个实例反编组;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by james on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("SF_RVS.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance( SFOrderRvsCancelResponseModel.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SFOrderRvsCancelResponseModel obj = (SFOrderRvsCancelResponseModel)jaxbUnmarshaller.unmarshal( file );
            System.out.println( obj );

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
