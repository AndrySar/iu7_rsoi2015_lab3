package server;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by user on 22.12.15.
 */
public class jetty {

    public static void main(String[] args) throws Exception {

        Server server = new Server(5001);

        ServletContextHandler root = new ServletContextHandler(server, "/" , ServletContextHandler.SESSIONS);
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "rest");//Set the package where the services reside
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        root.addServlet(sh, "/api/*");

        server.start();
        server.join();
    }
}
