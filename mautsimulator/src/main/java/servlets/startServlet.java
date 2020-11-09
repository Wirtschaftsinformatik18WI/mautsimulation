package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Simulationszeit;
import model.Testcase;


@WebServlet("/startServlet")
public class startServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public startServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Simulationszeit simulationszeit = new Simulationszeit();
		simulationszeit.setTimeSpeed(request.getParameter("Geschwindigkeit"));
		
		Testcase testcase = new Testcase();
		testcase.setTestNumber(request.getParameter("Testfälle"));
		
		
		
		getServletContext().getRequestDispatcher("/simulationsStart.jsp").forward(request, response);
	}
}
