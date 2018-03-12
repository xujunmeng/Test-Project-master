package 检查mongo记录与s3上文件是否匹配;

import com.aug3.storage.mongoclient.MongoUtils;
import com.google.common.collect.Lists;
import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by junmeng.xu on 2016/12/27.
 */
public class Main2 {

    private static Mongo mg = new Mongo("122.144.134.95", 27017);

    @Test
    public void test22() throws Exception{
        DB db = mg.getDB("news");
        DBCollection coll = db.getCollection("announcement_fund");
        List<DBObject> dbObjects = coll.find(new BasicDBObject("pub", new BasicDBObject("$gt", "2010-01-01").append("$lt", "2017-01-01"))).toArray();
        List<String> list = Lists.newArrayList();
        for (DBObject dto : dbObjects) {
            String sid = MongoUtils.getStringByFieldNameChain(dto, "sid");
            if (StringUtils.isBlank(sid)) continue;
            String url = MongoUtils.getStringByFieldNameChain(dto, "file.url");
            if (StringUtils.isBlank(url)) continue;
            String fn = MongoUtils.getStringByFieldNameChain(dto, "file.fn");
            if (StringUtils.isBlank(fn)) continue;
            String filePathNotExt = url + fn;
            list.add(sid + "&&" + filePathNotExt);
        }

        for (String filePath : list) {
            File file = new File("/testAnnounceFund/needConfirmationFile/paper.log");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter wrFA = new FileWriter(file, true);
            wrFA.write(filePath + "\r\n");
            if (wrFA != null) {
                wrFA.close();
            }
        }
    }

}
