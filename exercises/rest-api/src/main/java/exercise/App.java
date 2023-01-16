package exercise;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.crud;
import io.javalin.core.validation.ValidationException;

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

    private static void addRoutes(Javalin app) {

        app.get("/", ctx -> ctx.result("REST API"));

        // BEGIN
        //
        app.routes(() -> {
            crud("api/v1/users/{id}", new UserController());
        });
        // END
    }

    public static Javalin getApp() {

        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
        });

        // Устанавливаем, что при возникновении ошибок валидации
        // будет отправлен JSON с ошибками валидации
        // и установлен код ответа 422
        app.exception(ValidationException.class, (e, ctx) -> {
            ctx.json(e.getErrors()).status(422);
        });

        addRoutes(app);

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
