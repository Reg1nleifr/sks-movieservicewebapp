package controller;

import entity.Movie;
import services.MovieFacade;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Stefan on 26.10.2016.
 */
@WebServlet("/movies")
public class MovieServlet extends HttpServlet{
    @Inject
    private MovieFacade movieFacade;

    public MovieServlet(){
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        for (Movie movie: movieFacade.getAll()){
            out.print("<h1>"+movie.getTitle()+"</h1>");
            out.print("<p>"+movie.getDescription()+"</p>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
