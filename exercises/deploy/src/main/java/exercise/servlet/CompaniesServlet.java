package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        // получение query параметра http://localhost:5000/companies?search=ov
        String search = request.getParameter("search");
        List<String> companies = getCompanies();
        PrintWriter out = response.getWriter();
        if (search == null || search.equals("")) {
            companies.forEach(x -> out.write(x + "\n"));
        } else {
            List<String> resultList = companies.stream().filter(x -> x.contains(search)).collect(Collectors.toList());
            if (resultList.size() > 0) {
                resultList.forEach(x -> out.write(x + "\n"));
            } else {
                out.write("Companies not found");
            }
        }
        out.close();
        // END
    }
}
