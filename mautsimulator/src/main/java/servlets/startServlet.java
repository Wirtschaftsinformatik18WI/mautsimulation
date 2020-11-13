package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Senden;
import controller.SendenTask;
import controller.Simulationszeit;
import model.TransmitterData;


@WebServlet("/startServlet")
public class startServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public Simulationszeit simulationszeit;
	public ResultSet resultSet;
	public ArrayList<TransmitterData> list;
	
	public SendenTask sendenTask;
	
   
    public startServlet() {
        super();
       
    }
    
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Simulationszeit Timer
		if(simulationszeit == null) {
			simulationszeit = new Simulationszeit();
			
			//Daten nach Testfall aus DB lesen
			Senden senden = new Senden();
			resultSet = senden.readTransmitterData(request.getParameter("Testfälle"));
			try {
				list = senden.saveTransmitterData(resultSet);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//Zeit des ersten Elements 
			String ersteZeit = list.get(0).getTime();
			
			int h = Integer.parseInt(ersteZeit.substring(0, 2));
			int m = Integer.parseInt(ersteZeit.substring(3, 5));
		
			//Setzen der Startzeit anhand des 1. Elements
			simulationszeit.setHour(h);
			simulationszeit.setMinute(m);
			simulationszeit.setSecond(0);
			
			//Geschwindigkeit setzen
			simulationszeit.setTimeSpeed(request.getParameter("Geschwindigkeit"));

			//Simulationszeit starten
			Timer simulTimer = new Timer();
			simulTimer.schedule(simulationszeit, 3000, simulationszeit.getTimeSpeed());
			
			
		}
		
		//Sendezeit Timer
		if(sendenTask == null) {
			sendenTask = new SendenTask();

			Timer sendIntervall = new Timer();
			sendIntervall.schedule(sendenTask, 0, simulationszeit.getSendSpeed());
		}
		
		
		
		HttpSession session = request.getSession();
		session.setAttribute("Simulzeit", simulationszeit);
		session.setAttribute("Liste", list);
		
		session.setAttribute("SendenTask", sendenTask);
		
		
		getServletContext().getRequestDispatcher("/simulationsStart.jsp").forward(request, response);
	}
}
