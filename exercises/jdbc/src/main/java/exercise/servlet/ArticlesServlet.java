package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response); // список статей по 10 на страницу: host:port/articles?page=1
                break;
            default:
                showArticle(request, response); // инфа по выбранной статье: host:port/articles/{id}
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        // атрибут dbConnection засетан при подключении template engine Thymeleaf
        Connection connection = (Connection) context.getAttribute("dbConnection");

        // BEGIN
        int countRecordPage = 10;
        String paramPage = request.getParameter("page");
        int numPage = (paramPage == null ? 1 : Integer.parseInt(paramPage));

        int offset = numPage * countRecordPage - countRecordPage;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id, title, body FROM articles ORDER BY id LIMIT ? OFFSET ?");
            ps.setInt(1, countRecordPage);
            ps.setInt(2, offset );
            ResultSet rs = ps.executeQuery();

            List<Map<String,String>> articles = new ArrayList<>();
            while (rs.next()) {
                articles.add(Map.of(
                        "id", rs.getString("id"),
                        "title", rs.getString("title"),
                        "body", rs.getString("body")));
            }
            request.setAttribute("articles", articles);
            request.setAttribute("numPage", numPage);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String paramPage = request.getParameter("page");
        Map<String, String> article = new HashMap<>();
        String query = "SELECT id, title, body FROM articles WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Objects.requireNonNull(getId(request))));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                article.put("id", rs.getString("id"));
                article.put("title", rs.getString("title"));
                article.put("body", rs.getString("body")
                );
            }
            if (article.isEmpty()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            request.setAttribute("article", article);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
