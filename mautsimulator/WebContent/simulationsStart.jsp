<!DOCTYPE html>
<%@page import="controller.Simulationszeit"%>
<%@page import="controller.Start"%>
<%@page import="java.util.Timer"%>
<html>
    <head>
        <link rel="stylesheet" href="styles/styleMautsimulator.css" type="text/css">
        <title>
            Simulation Konfiguration
        </title>
        <meta charset ="utf-8">
    </head>

    <body>
        <div id="wrapper">

          	<header>
               <div class="home">
                    <h1>Mautsimulator 2020</h1>    
                </div>
            </header>

            <section>    
           		          		     
   				<div class="navigation">
   				
   					Testfall: ${param.Testfälle}
   				
   						
   					<%
   						Simulationszeit sim = new Simulationszeit();
   						int i = 0;
   						String time = sim.getSimulTime();	
   						while(i == 0){
   							sim.getSimulTime();	
   						}
	                    out.println(time);
	                %>
   					
   				
					<input class ="button" type="submit" value="Stoppen" name="Stoppen" />                 
                </div>                          
          	 	
          	 	Gesendete Standortdaten:
          	 	
          	 	<div class="ausgabe">
          	 	
                    <table border="1" >
                        <thead>
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                             <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            
                          <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                            <tr>
                                <td><%out.println("M");%> </td>
                                <td><%out.println("Durchfahrt");%></td>
                                <td><%out.println("B-KM-456");%></td>
                                <td><%out.println("30.11.2020");%></td>
                                <td><%out.println("14:05:30");%></td>
                            </tr>
                        </tbody>
                    </table>
          	 	
          	 	
          	 	</div>
          
           	 		           
            </section> 
        </div>  
    </body>
</html>