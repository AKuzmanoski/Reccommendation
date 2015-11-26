package mk.finki.ukim.finki.iis.lastfmAccess;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import mk.ukim.finki.iis.model.User;

public class LastFmAccess {
	
	public static final String API_KEY="9736e6b23d5ab4ee1066ce12949ef679";
	public static final String API_SECRET="69e7bd280d426aa0bb1aa8b807e28f0a";
	
	
	public static String getQueryStringForTrack(String method,String track,String apikey) throws UnsupportedEncodingException{
		String query="http://ws.audioscrobbler.com/2.0/?method="+method;
	    query+="&track=" + URLEncoder.encode(track,"utf-8");
	    query+="&api_key="+API_KEY+"&format=json";
	    return query;
	}
	public static String getQueryStringForUser(String method,String user) throws UnsupportedEncodingException{
		String query="http://ws.audioscrobbler.com/2.0/?method="+method;
	    query+="&user=" + URLEncoder.encode(user,"utf-8");
	    query+="&api_key="+API_KEY+"&format=json";
	    return query;
		
	}
	public static InputStream getInputStream(String query) throws ClientProtocolException, IOException{
		
		HttpClient client = new DefaultHttpClient();
		HttpGet getMethod = new HttpGet(query);
		HttpResponse response = client.execute(getMethod);
		
		if(response.getStatusLine().getStatusCode() != 200) return null;		
		InputStream is = response.getEntity().getContent();	
		return is;
		
		
	}
	
}


