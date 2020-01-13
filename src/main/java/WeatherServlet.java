import dao.DAOService;
import strategies.StrategyStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "WeatherServlet")
public class WeatherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        String resource = request.getParameter("resource");
        WeatherParser parser = new WeatherParser(StrategyStorage.getInstance(), DAOService.getInstance());

        PrintWriter writer = response.getWriter();
        writer.println(parser.getForecast(city, resource));
    }
}
