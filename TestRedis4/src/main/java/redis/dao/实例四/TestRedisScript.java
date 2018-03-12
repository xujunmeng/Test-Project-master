package redis.dao.实例四;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.script.RedisScript;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Created by james on 2018/2/24.
 */
public class TestRedisScript implements RedisScript<String> {

    private String fileContent;

    private String sha1;

    private String luaFileName;

    @PostConstruct
    public void init() throws Exception{
        InputStream is = TestRedisScript.class.getClassLoader().getResourceAsStream("lua/"+luaFileName);
        byte[] filecontent = new byte[is.available()];
        is.read(filecontent);
        is.close();
        fileContent = new String(filecontent, "UTF-8");
        sha1 = DigestUtils.sha1Hex(fileContent);
    }

    @Override
    public String getSha1() {
        return sha1;
    }

    @Override
    public Class<String> getResultType() {
        return String.class;
    }

    @Override
    public String getScriptAsString() {
        return fileContent;
    }

    public String getLuaFileName() {
        return luaFileName;
    }

    public void setLuaFileName(String luaFileName) {
        this.luaFileName = luaFileName;
    }
}

