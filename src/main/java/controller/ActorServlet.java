package controller;

import entity.Actor;
import services.ActorFacade;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Flo on 06/10/16.
 */
@WebServlet("/actors")
public class ActorServlet extends HttpServlet {


    @Inject
    private ActorFacade actorFacade;

    public ActorServlet() {
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<bod>");
        for (Actor actor: actorFacade.getAll()) {
            out.print("<h1>"+actor.getFirstname()+"</h1>");
            out.print("<p>"+actor.getLastname()+"</p>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
