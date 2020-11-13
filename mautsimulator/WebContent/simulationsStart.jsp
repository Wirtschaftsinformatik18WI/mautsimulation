<!DOCTYPE html>
<%@page import="controller.SendenTask"%>
<%@page import="servlets.startServlet"%>
<%@page import="java.util.TimerTask"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="model.TransmitterData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="controller.Senden"%>
<%@page import="controller.Database"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="controller.Simulationszeit"%>
<%@page import="java.util.Timer"%>

<html>
<head>
<link rel="stylesheet" href="styles/styleMautsimulator.css"
	type="text/css">
<title>Simulation Start</title>
<meta charset="utf-8">
<meta http-equiv="refresh" content="1">

</head>

<body>
	<div id="wrapper">

		<header>
			<div class="home">
				<h1>Mautsimulator 2020</h1>
			</div>



		</header>

		<div class="navigation">

			<h>Testfall: ${param.Testfälle}</h>
	

			
			<%
			Simulationszeit simulationszeit = (Simulationszeit)session.getAttribute("Simulzeit");
			 %>

			<a><%out.println("Simulationzeit: " + simulationszeit.getSimulTime());%></a>

		</div>

		<section>

			<b>Generierte Standortdaten:</b> 
			<br></br>

			<div class="scroll1">

				<table class="table1" border="1">
					<thead>
						<tr>
							<th>Point</th>
							<th>Zeit</th>
							<th>Datum</th>
							<th>Herkunft</th>
							<th>Kennzeichen</th>
						</tr>
					</thead>

					<%	  	 
          	  
			          	 //Alle Daten mit dem ausgwählten Testfall
			          	 ArrayList<TransmitterData> datas = (ArrayList<TransmitterData>)session.getAttribute("Liste");

			          	 //Generierte Daten in Tabelle anzeigen
			          	 out.print( "<tbody>");
			          	 for(TransmitterData d : datas){
			          		out.println( "<tr>" );
			          		out.println( "<td>" + d.getPoint() + "</td>" );
			          		out.println( "<td>" + d.getTime() + "</td>" );
			          		out.println( "<td>" + d.getDate() + "</td>" );
			          		out.println( "<td>" + d.getOrigin() + "</td>" );
			          		out.println( "<td>" + d.getRegistrationNumber() + "</td>" );
			          		out.println( "</tr>" );
			          	 }
			          	 out.println( "</tbody>" );

			         %>

				</table>

			</div>

		</section>


		<aside>

			<b>Gesendete Standortdaten:</b> 
			<br></br>

			<div class="scroll1">
			
				<table class="table1" border="1">
					<thead>
						<tr>
							<th>Point</th>
							<th>Zeit</th>
							<th>Datum</th>
							<th>Herkunft</th>
							<th>Kennzeichen</th>
						</tr>
					</thead>

					<%	 
			         
			          	 Senden senden = new Senden();			         

			          	 //Angefallene Daten die in der Simulzeit liegen
			          	 ArrayList<TransmitterData> data = new ArrayList();
			          	 data = senden.selectActualTransmitterData(datas, simulationszeit.getSimulTime());
			          	 
			          	 SendenTask sendenTask = (SendenTask)session.getAttribute("SendenTask");
			          	 sendenTask.setList(data);
			          	 
			          	 
			          	 //Generierte Daten in Tabelle anzeigen
			          	 out.print( "<tbody>");
			          	 for(TransmitterData d : data){
			          		out.println( "<tr>" );
			          		out.println( "<td>" + d.getPoint() + "</td>" );
			          		out.println( "<td>" + d.getTime() + "</td>" );
			          		out.println( "<td>" + d.getDate() + "</td>" );
			          		out.println( "<td>" + d.getOrigin() + "</td>" );
			          		out.println( "<td>" + d.getRegistrationNumber() + "</td>" );
			          		out.println( "</tr>" );
			          	 }
			          	 out.println( "</tbody>" );

			         %>

				</table>
			
			</div>

		</aside>

	</div>
</body>
</html>