<!DOCTYPE html>
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
           		
           		<form action="startServlet">
           	 		<div>
	           	 		Testfall
	           	 		<select class="dropdownTestf‰lle" name="Testf‰lle" >
	                        <option value="1">Auto auf der Strecke verschollen</option>
	                        <option value="2">Performance Test - Ganz viele Autos</option>
	                        <option value="3">Rettungsgasse nicht beachtet</option>
	                        <option value="4">Geschwindigkeitsverstoﬂ</option>
	                        <option value="5">Abrechnungstest</option>
	                        <option value="6">Fehlende Standortdaten</option>                    
	                    </select>
               
                 
                 
	                 	Geschwindigkeit
	           	 		<select class="dropdownGeschwindigkeit" name="Geschwindigkeit" >
	                        <option value="2">2-fach</option>
	                        <option value="4">4-fach</option>
	                        <option value="8">8-fach</option>
	                        <option value="16">16-fach</option> 
	                    </select>
	                 
	                 	<input type="submit" value="Start" name="Starten" />                 
                 	</div>                          
          	 	</form>
          	 	
          	 	
          	 	<div class = "ausgabe">
          	 	
          	 	
          	 	</div>
          
           	 		           
            </section> 
        </div>  
    </body>
</html>
