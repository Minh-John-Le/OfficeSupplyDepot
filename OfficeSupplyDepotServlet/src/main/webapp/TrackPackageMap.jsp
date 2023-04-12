<%@page import = "java.io.IOException" %>
<%@page import = "java.io.InputStream" %>
<%@page import = "java.util.Properties" %>
<%@page import = "javax.servlet.ServletContext" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Display navigation directions</title>
<meta name="viewport" content="initial-scale=1,maximum-scale=1,user-scalable=no">
<link href="https://api.mapbox.com/mapbox-gl-js/v2.13.0/mapbox-gl.css" rel="stylesheet">
<script src="https://api.mapbox.com/mapbox-gl-js/v2.13.0/mapbox-gl.js"></script>
<style>
body { margin: 0; padding: 0; }
#map { position: absolute; top: 0; bottom: 0; width: 100%; }
</style>
</head>
<body>
<script src="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-directions/v4.1.1/mapbox-gl-directions.js"></script>
<link rel="stylesheet" href="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-directions/v4.1.1/mapbox-gl-directions.css" type="text/css">
<div id="map"></div>

<script>
	// TO MAKE THE MAP APPEAR YOU MUST
	// ADD YOUR ACCESS TOKEN FROM
	<%
	InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/config.properties");
	Properties props = new Properties();
	
	try {
		props.load(inputStream);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	String accessToken = props.getProperty("mapbox.accessToken");
	//System.out.println(accessToken);
	%>
	
	mapboxgl.accessToken = '<%=accessToken%>';
	console.log(mapboxgl.accessToken);

    const map = new mapboxgl.Map({
        container: 'map',
        // Choose from Mapbox's core styles, or make your own style with Mapbox Studio
        style: 'mapbox://styles/mapbox/streets-v12',
        center: [-121.893028, 37.335480],
        zoom: 13
    });

		var directions = new MapboxDirections({
        accessToken: mapboxgl.accessToken
    });




	map.addControl(directions,'top-left');

	//==================================

	// Define the origin and destination points
	<%
	String destination  = (String) session.getAttribute("destination");
	%>
	const origin = "SJSU";
	const destination =  "<%=destination%>";


	map.on("load", function() {
	  // Get the first route
	  directions.setOrigin(origin);
	  directions.setDestination(destination);
	//directions.setWaypoint(1,[-121.762764619, 37.3013225812]);
	});

	//===================================


</script>

</body>
</html>
