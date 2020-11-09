package servlets;

import java.io.IOException;
import java.util.Timer;

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
		//Simulationgeschwindigkeit setzen
		simulationszeit.setTimeSpeed(request.getParameter("Geschwindigkeit"));
		
		Testcase testcase = new Testcase();
		testcase.setTestNumber(request.getParameter("Testfälle"));
		
		
		Timer simulTimer = new Timer();
		//Startzeit der Simulation festlegen
		simulationszeit.setHour(12);
		simulationszeit.setMinute(00);
		simulationszeit.setSecond(00);
		//Startdatum der Simulation festlegen
		simulationszeit.setDay(30);
		simulationszeit.setMonth(11);
		simulationszeit.setYear(2020);
		//Geschwindigkeit der Simulationszeit festlegen
		
		simulTimer.schedule(simulationszeit, 0, simulationszeit.getTimeSpeed());
		
		
		getServletContext().getRequestDispatcher("/simulationsStart.jsp").forward(request, response);
	}
}
