package first;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

;

/**
 * Created by junmeng.xu on 2016/10/21.
 */
public class HelloJetty {

    public static void main(String[] args) {

        Server server = new Server(8080);

        ServletHolder servletHolder = new ServletHolder();
        servletHolder.setServlet(new MyServlet());

        Context context = new Context(server, null);
        context.addServlet(servletHolder, "/");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
