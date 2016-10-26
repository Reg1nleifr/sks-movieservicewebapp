package controller;

import entity.Studio;
import services.StudioFacade;

import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Stefan on 26.10.2016.
 */
@WebServlet("/studios")
public class StudioServlet extends HttpServlet {
    @Inject
    private StudioFacade studioFacade;

    public StudioServlet(){
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        for(Studio studio: studioFacade.getAll()){
            out.print("<h1>"+studio.getName()+"</h1>");
            out.print("<p>"+studio.getCountrycode()+"</p>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
