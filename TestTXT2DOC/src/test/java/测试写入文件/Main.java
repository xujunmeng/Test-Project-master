package 测试写入文件;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by junmeng.xu on 2016/12/26.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        List<String> allResult = Lists.newArrayList("xujunmeng1", "xujunmeng2", "xujunmeng3");
        for (String filePath : allResult) {
            File file = new File("/testAnnounceFund/lostFilePath/paper.log");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter wrFA = new FileWriter(file, true);
            wrFA.write(filePath + "\r\n");
            if(wrFA != null){
                wrFA.close();
            }
        }


    }

}
