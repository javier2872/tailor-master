package endtoend.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import endtoend.stepsDefinitions.Hook;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.DataOutputStream;
import java.io.BufferedReader;

public class Utils {

	public String createATailor() {
		JSONObject  json;
	    URL url;
	    InputStream is = null;
		try {
			json = (JSONObject ) new JSONParser().parse(new FileReader("src/test/resources/TailorPrueba.json"));			
			url = new URI("http://localhost:8762/sastreria-fitting/tailor").toURL();
			Hook.setCon((HttpURLConnection) url.openConnection());
	        Hook.getCon().setRequestProperty("Content-Type", "application/json");
	        Hook.getCon().setRequestProperty("Accept", "application/json");
	        Hook.getCon().setUseCaches(false);
	        Hook.getCon().setDoOutput(true);
	        Hook.getCon().setRequestMethod("POST");        
	        String requestJSON = json.toString();
	        //Send request
            System.out.println(requestJSON);
            DataOutputStream wr = new DataOutputStream ( Hook.getCon().getOutputStream());
            wr.writeBytes(requestJSON);
            wr.close();
            //Get Response  
            try {
                is = Hook.getCon().getInputStream();
            } catch (IOException ioe) {
                if (Hook.getCon() instanceof HttpURLConnection) {
                    HttpURLConnection httpConn = (HttpURLConnection) Hook.getCon();
                    int statusCode = httpConn.getResponseCode();
                    if (statusCode != 200) {
                        is = httpConn.getErrorStream();
                    }
                }
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder(); 
            String line;
            while ((line = rd.readLine()) != null) {
            	sb.append(line);
            	sb.append('\r');
            }
            rd.close();
            return sb.toString();
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}  finally {         
		    if (Hook.getCon() != null) {
		    	Hook.getCon().disconnect();
		    }
		}
		return null;
	}
	
	public String getAJob(String idTailor) {
	    URL url;
	    InputStream is = null;
		try {			
			url = new URI("http://localhost:8762/trabajo-fitting/job/tailor/"+idTailor).toURL();
			Hook.setCon((HttpURLConnection) url.openConnection());
	        Hook.getCon().setRequestProperty("Content-Type", "application/json");
	        Hook.getCon().setRequestProperty("Accept", "application/json");
	        Hook.getCon().setUseCaches(false);
	        Hook.getCon().setDoOutput(true);
	        Hook.getCon().setRequestMethod("GET");        
            //Get Response  
            try {
                is = Hook.getCon().getInputStream();
            } catch (IOException ioe) {
                if (Hook.getCon() instanceof HttpURLConnection) {
                    HttpURLConnection httpConn = (HttpURLConnection) Hook.getCon();
                    int statusCode = httpConn.getResponseCode();
                    if (statusCode != 200) {
                        is = httpConn.getErrorStream();
                    }
                }
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder(); 
            String line;
            while ((line = rd.readLine()) != null) {
            	sb.append(line);
            	sb.append('\r');
            }
            rd.close();
            return sb.toString();
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}   finally {         
		    if (Hook.getCon() != null) {
		    	Hook.getCon().disconnect();
		    }
		}
		return null;
	}
	
	public void deleteTailor(String idTailor) {
	    URL url;
		try {			
			url = new URI("http://localhost:8762/sastreria-fitting/tailor/"+idTailor).toURL();
			Hook.setCon((HttpURLConnection) url.openConnection());
	        Hook.getCon().setRequestProperty("Content-Type", "application/json");
	        Hook.getCon().setRequestProperty("Accept", "application/json");
	        Hook.getCon().setUseCaches(false);
	        Hook.getCon().setDoOutput(true);
	        Hook.getCon().setRequestMethod("DELETE");        
	        HttpURLConnection httpConn = (HttpURLConnection) Hook.getCon();
            httpConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {         
		    if (Hook.getCon() != null) {
		    	Hook.getCon().disconnect();
		    }
		}
	}
	
	public void deleteJob(String idJob) {
	    URL url;
		try {			
			url = new URI("http://localhost:8762/trabajo-fitting/job/"+idJob).toURL();
			Hook.setCon((HttpURLConnection) url.openConnection());
	        Hook.getCon().setRequestProperty("Content-Type", "application/json");
	        Hook.getCon().setRequestProperty("Accept", "application/json");
	        Hook.getCon().setUseCaches(false);
	        Hook.getCon().setDoOutput(true);
	        Hook.getCon().setRequestMethod("DELETE");        
	        HttpURLConnection httpConn = (HttpURLConnection) Hook.getCon();
            httpConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}  finally {         
		    if (Hook.getCon() != null) {
		    	Hook.getCon().disconnect();
		    }
		}
	}
}
