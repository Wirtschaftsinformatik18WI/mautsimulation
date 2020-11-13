<!DOCTYPE html>
<%@page import="controller.Simulationszeit"%>
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
            
          <form action="startServlet">
           	 		<div class="navigation">
	           	 		<h>Testfall</h>
	           	 		<select class="dropdownTestf‰lle" name="Testf‰lle">
	                        <option value="1">Auto auf der Strecke verschollen</option>
	                        <option value="2">Performance Test - Ganz viele Autos</option>
	                        <option value="3">Rettungsgasse nicht beachtet</option>
	                        <option value="4">Geschwindigkeitsverstoﬂ</option>
	                        <option value="5">Abrechnungstest</option>
	                        <option value="6">Fehlende Standortdaten</option>                    
	                    </select>
               
                 
                 
	                 	<h>Geschwindigkeit</h>
	           	 		<select class="dropdownGeschwindigkeit" name="Geschwindigkeit" >
	                        <option value="2">2-fach</option>
	                        <option value="4">4-fach</option>
	                        <option value="8">8-fach</option>
	                        <option value="16">16-fach</option> 
	                    </select>
	                    
	                    
	                 
	                 	<input class ="button" type="submit" value="Start" name="Starten" />                 
                 	</div>                          
          	 	</form>

            <section>    
           		
           		
          	 	
          	 	
          	 	<b>Generierte Standortdaten:</b> 
           		<br></br>	
           		
          	 	<div class = "scroll1">
          	 	<table border="1" >
                        <thead>
                            <tr>
                                <th>Point</th>
			                    <th>Zeit</th>
			                    <th>Datum</th>
		                        <th>Herkunft</th>
	                            <th>Kennzeichen</th>
                            </tr>
                        </thead>
                        <tbody>
                           
                        </tbody>
                    </table>
          	 	
          	 	</div>
          
           	 		           
            </section> 
            
            <aside>
            
            <b>Gesendete Standortdaten:</b> 
           	<br></br>
           	
            <div class="scroll1"> 
			
			<table class="table1" border="1" >
	          	 			
			
				<thead>
			                            <tr>
			                                <th>Point</th>
			                                <th>Zeit</th>
			                                <th>Datum</th>
			                                <th>Herkunft</th>
			                                <th>Kennzeichen</th>
			                            </tr>
			                        </thead>			          	 	
			          	 	
			          	 
							
			</table>
			
			</div>
			
			</aside>
        </div>  
    </body>
</html>
