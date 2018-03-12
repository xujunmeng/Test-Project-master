package junmeng;

import com.sun.org.apache.xalan.internal.xsltc.compiler.SourceLoader;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by james on 2017/9/5.
 */
public class DataSourceFactory {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        Properties p=new Properties();

        InputStream is=SourceLoader.class.getClassLoader().getSystemResourceAsStream("jdbc.properties");
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataSource.setDriverClassName(p.getProperty("driverClassName"));
        dataSource.setUsername(p.getProperty("userName"));
        dataSource.setPassword(p.getProperty("password"));
        dataSource.setUrl(p.getProperty("url"));
    }

    public static DataSource createDataSource() {
        return dataSource;
    }

}
