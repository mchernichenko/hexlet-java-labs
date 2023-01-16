package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private static List<User> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String jsonUserArray = Files.readString(
                Paths.get(Objects.requireNonNull(UsersServlet.class.getClassLoader().
                                    getResource("users.json")) // поиск ресурса по имени от корня jar-файла
                         .getFile()));
        return MAPPER.readValue(jsonUserArray, new TypeReference<List<User>>(){});
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(createContent(getUsers()));
        out.close();
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        User user = getUsers().stream()
                .filter(x-> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(createContent(user));
        out.close();
        // END
    }

    // htlm для отображения списка пользователей
    private static String createContent(List<User> list) throws IOException {
        String contentHead = Files.readString(
                Paths.get(Objects.requireNonNull(UsersServlet.class.getClassLoader()
                        .getResource("head.html")).getFile()));

        StringBuilder content = new StringBuilder("<!DOCTYPE><html>");
        content.append(contentHead);
        content.append("""
                       <body>
                         <table id=\"alter\">
                         <caption>User Records</caption>
                           <tr><th>id</th><th>fullName</th></tr>
                       """);
        for (User user : list) { // <tr><td>{id}</td><td><a href="/users/{id}">{fullName}</a></td></tr>
            content.append("<tr><td>").
                    append(user.getId()).
                    append("</td><td><a href=\"/users/").append(user.getId()).append("\">").
                    append(user.getFirstName()).append(" ").append(user.getLastName()).
                    append("</a></td></tr>");
        }
        content.append("<body><table id=\"alter\">");
        content.append("</table></body></html>");
        return content.toString();
    }

    // htlm для отображения данный по пользователю
    private static String createContent(User user) throws IOException {
        String contentHead = Files.readString(
                Paths.get(Objects.requireNonNull(UsersServlet.class.getClassLoader()
                        .getResource("head.html")).getFile()));

        StringBuilder content = new StringBuilder("<!DOCTYPE><html>");
        content.append(contentHead);
        content.append("""
                       <body>
                         <table id=\"alter\">
                         <caption>User Record</caption>
                           <tr><th>id</th><th>firstName</th><th>lastName</th><th>email</th></tr>
                       """);

        // <tr><td>{id}</td><td><{firstName}</td><td><{lastName}</td><td><{email}</td></tr>
        content.append("<tr><td>").append(user.getId()).append("</td>").
        append("<td>").append(user.getFirstName()).append("</td>").
        append("<td>").append(user.getLastName()).append("</td>").
        append("<td>").append(user.getEmail()).append("</td></td>");

        content.append("<body><table id=\"alter\">");
        content.append("</table></body></html>");
        return content.toString();
    }
}
