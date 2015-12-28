package server;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileReader;

/**
 * Created by user on 22.12.15.
 */
public class jetty {

    public static void main(String[] args) throws Exception {

        Server server = new Server(5000);

        ServletContextHandler root = new ServletContextHandler(server, "/" , ServletContextHandler.SESSIONS);
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "rest");//Set the package where the services reside
        sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        root.addServlet(sh, "/api/*");

        server.start();
        server.join();

    }

    public static void printLogs(HttpServletRequest httpRequest)
    {

//        File file = new File("logs.txt");
//
//        try {
//            //проверяем, что если файл не существует то создаем его
//            if(!file.exists()){
//                file.createNewFile();
//            }
//
//            //PrintWriter обеспечит возможности записи в файл
//            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
//
//            try {+




//                //Записываем текст у файл
//                out.print(text);
//            } finally {
//                //После чего мы должны закрыть файл
//                //Иначе файл не запишется
//                out.close();
//            }
//        } catch(IOException e) {
//            throw new RuntimeException(e);
//        }
        System.out.print(httpRequest.toString());
    }
}
