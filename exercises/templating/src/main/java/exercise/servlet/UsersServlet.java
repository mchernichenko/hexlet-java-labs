package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import static exercise.Data.getUsers;

public class UsersServlet extends HttpServlet {

    private List<Map<String, String>> users = getUsers();

    private String getId(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, "");
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // т.к. сервлет замаплен на /users/* , то получает кусок URL подпадающий под wildcard
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) { // URL http://localhost:5000/users , т.е. под wildcard ничего не попало
            showUsers(request, response); // раз так, выводим всех пользователей
            return;
        }

        // URL: http://localhost:5000/users/{action}?id=4
        // URL может быть и длиннее, но берём первый pathParam, в котором ожидаем - action
        String action = getAction(request);

        switch (action) {
            case "show":
                showUser(request, response); // показать пользователя с переданным queryParam - id
                break;
            case "delete":
                showDeletePage(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "delete":
                deleteUser(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        // сетаем в request новый параметр, значение которого получено уже на сервере, а не передано клиентом
        // и вызываем, по сути другой системный сервлет, отвечающий за обработку *.jsp,
        // с передачей стандартных параметров response и обогащенного request.
        request.setAttribute("users", users);
        request.getRequestDispatcher("/users.jsp").forward(request, response);
        //  getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {
        // Получаем id пользователя из строки запроса
        String id = getId(request);

        // Получаем пользователя по его id
        Map<String, String> user = getUserById(id);

        // Если пользователь не найден, нужно вернуть код ответа 404
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // BEGIN
        request.setAttribute("user", user);
        request.getRequestDispatcher("/show.jsp").forward(request, response);
        // END
    }

    // вывод странички с предупреждением перед удалением пользователя
    private void showDeletePage(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // BEGIN
        request.setAttribute("user", user); // id нужен для отображения удаляемого user`а на странице
        request.getRequestDispatcher("/delete.jsp").forward(request, response);
        // END
    }

    private void deleteUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // BEGIN
        users.remove(user);
        // простой редирект с клиента на другую страницу со списком user`ов
        // в отличие от forward через диспетчер - это по сути редирект внутри web-сервера
        response.sendRedirect("/users");
        // END

    }

    private Map<String, String> getUserById(String id) {
        Map<String, String> user = users
            .stream()
            .filter(u -> u.get("id").equals(id))
            .findAny()
            .orElse(null);

        return user;
    }
}
