package 第六个实例反编组;

import 第五个实例反编组.SFOrderCancelResponseERRModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by james on 2017/5/25.
 */
public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("SF_ERR_RVS.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance( SFOrderRvsCancelResponseERRModel.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SFOrderRvsCancelResponseERRModel obj = (SFOrderRvsCancelResponseERRModel)jaxbUnmarshaller.unmarshal( file );
            System.out.println( obj );

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
