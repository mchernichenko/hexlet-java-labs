package exercise;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import exercise.servlet.WelcomeServlet;
import exercise.servlet.UsersServlet;
import exercise.servlet.SessionServlet;

public class App {
    private static final String CONTEXT_PATH = "";  // my-app/
    private static final String DOC_BASE = "src/main/webapp";
    private static final String DOC_BASE1 = "java/exercises/session/src/main/webapp";

    private static Users users;

    public static Users getUsers() {
        return users;
    }

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.valueOf(port);
        }
        return 5000;
    }

    public static Tomcat getApp(int port) {
        users = new Users();

        Tomcat tomcat = new Tomcat();

        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.setPort(port);

        Context ctx = tomcat.addWebapp(CONTEXT_PATH, new File(DOC_BASE).getAbsolutePath());

        tomcat.addServlet(ctx, WelcomeServlet.class.getSimpleName(), new WelcomeServlet());
        ctx.addServletMappingDecoded("", WelcomeServlet.class.getSimpleName());

        tomcat.addServlet(ctx, UsersServlet.class.getSimpleName(), new UsersServlet());
        ctx.addServletMappingDecoded("/users/*", UsersServlet.class.getSimpleName());

        // BEGIN
        tomcat.addServlet(ctx, SessionServlet.class.getSimpleName(), new SessionServlet());
        ctx.addServletMappingDecoded("/login", SessionServlet.class.getSimpleName());
        ctx.addServletMappingDecoded("/logout", SessionServlet.class.getSimpleName());
        // END

        return tomcat;
    }

    public static void main(String[] args) throws LifecycleException, IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80)); // получить внешний ip-адрес
        String InetIP = socket.getLocalAddress().toString();

        int port = getPort();
        String LocalIP = InetAddress.getLocalHost().getHostAddress();
        String baseUrl = "Приложение доступно по адресу ---> http:/" + InetIP + ":" + port + CONTEXT_PATH;

        Tomcat app = getApp(getPort());
        app.start();
        System.out.println(baseUrl); // выводим URL в формате 'host:port/my-app' по которому доступно наше приложение
        app.getServer().await();
    }

}
