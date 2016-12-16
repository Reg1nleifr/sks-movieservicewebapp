package at.technikumwien.controller;

import at.technikumwien.entity.Movie;
import at.technikumwien.services.facades.MovieService;

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
    private MovieService movieService;

    public MovieServlet(){
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.print("<h1>Movies</h1>");
        for (Movie movie: movieService.getAll()){
            out.print("<h2>"+movie.getTitle()+"</h2>");
            out.print("<p>"+movie.getDescription()+"</p>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
