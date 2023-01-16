package exercise;

// Импортируем зависимости, необходимые для работы приложения
import io.javalin.Javalin;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

import org.thymeleaf.TemplateEngine;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import exercise.controllers.RootController;
import exercise.controllers.UserController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public final class App {

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "5000");
        return Integer.valueOf(port);
    }

    private static TemplateEngine getTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addDialect(new LayoutDialect());
        templateEngine.addDialect(new Java8TimeDialect());
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.addTemplateResolver(templateResolver);

        return templateEngine;
    }

    private static void addRoutes(Javalin app) {
        app.get("/", RootController.welcome);

        app.routes(() -> {
            path("users", () -> {
                get(UserController.listUsers);
                post(UserController.createUser);  // POST /users
                get("new", UserController.newUser);
                get("{id}", UserController.showUser);
            });
        });
    }

    public static Javalin getApp() {

        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
            JavalinThymeleaf.configure(getTemplateEngine());
        });

        addRoutes(app);

        app.before(ctx -> {
            ctx.attribute("ctx", ctx);
        });

        return app;
    }

    private static void showAppURL() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("google.com", 80)); // получить внешний ip-адрес
        String InetIP = socket.getLocalAddress().toString();

        int port = getPort();
        String LocalIP = InetAddress.getLocalHost().getHostAddress();
        String baseUrl = "App listening on ---> http:/" + InetIP + ":" + port;
        System.out.println(baseUrl);
    }

    public static void main(String[] args) throws IOException {
        showAppURL();
        Javalin app = getApp();
        app.start(getPort());
    }
}
