package exercise;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import java.io.File;
import java.io.IOException;

import exercise.servlet.WelcomeServlet;
import exercise.servlet.ArticlesServlet;

import org.thymeleaf.TemplateEngine;
import javax.servlet.ServletContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;


public class App {
    private static final String CONTEXT_PATH = "";  // my-app/

    private static final String JDBC_URL_H2 = "jdbc:h2:./hexlet";
    private static final String JDBC_URL_H2_1 = "jdbc:h2:./h2db/hexlet";
    // временный URL для запуска из IDEA
    private static final String JDBC_URL_H2_2 = "jdbc:h2:./java/exercises/jdbc/h2db/hexlet2;MV_STORE=false;AUTO_SERVER=true";

    //
    private static final String JDBC_URL_PG = "jdbc:postgresql://172.25.120.42:5432/hexlet_db";
    private static final String LOGIN = "postgres";
    private static final String PASS = "postgres";

    private static final String SQL_SCRIPT = "init.sql";
    private static final String SQL_SCRIPT1 = "java/exercises/jdbc/init.sql";

    private static final String DOC_BASE = "src/main/webapp";
    private static final String DOC_BASE1 = "java/exercises/jdbc/src/main/webapp";

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.valueOf(port);
        }
        return 5000;
    }

    private static String getFileContent(String fileName) throws IOException {
        Path pathToSolution = Paths.get(fileName).toAbsolutePath();
        return Files.readString(pathToSolution).trim();
    }

    public static Tomcat getApp(int port, Connection dbConnection) {

        Tomcat tomcat = new Tomcat();

        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.setPort(port);

        Context ctx = tomcat.addContext(CONTEXT_PATH, new File(DOC_BASE).getAbsolutePath());

        // ************************************
        // Подключаем template engine Thymeleaf
        TemplateEngine templateEngine = new TemplateEngine();

        ServletContext servletContext = ctx.getServletContext();
        servletContext.setAttribute("dbConnection", dbConnection);
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContext);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);

        templateEngine.setTemplateResolver(resolver);
        templateEngine.addDialect(new LayoutDialect());

        TemplateEngineUtil.storeTemplateEngine(servletContext, templateEngine);
        // ************************************

        tomcat.addServlet(ctx, WelcomeServlet.class.getSimpleName(), new WelcomeServlet());
        ctx.addServletMappingDecoded("", WelcomeServlet.class.getSimpleName());

        tomcat.addServlet(ctx, ArticlesServlet.class.getSimpleName(), new ArticlesServlet());
        ctx.addServletMappingDecoded("/articles/*", ArticlesServlet.class.getSimpleName());

        return tomcat;
    }

    public static void main(String[] args) throws LifecycleException, SQLException, IOException {
        // BEGIN
       // Connection connection = DriverManager.getConnection(JDBC_URL_PG, LOGIN, PASS);
        Connection connection = DriverManager.getConnection(JDBC_URL_H2);
        String sqlScript = getFileContent(SQL_SCRIPT);
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlScript);
        // END
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80)); // получить внешний ip-адрес
        String InetIP = socket.getLocalAddress().toString();

        int port = getPort();
        String LocalIP = InetAddress.getLocalHost().getHostAddress();
        String baseUrl = "Приложение доступно по адресу ---> http:/" + InetIP + ":" + port + CONTEXT_PATH;
        System.out.println(baseUrl);

        Tomcat app = getApp(port, connection);
        app.start();
        app.getServer().await();
    }
}
