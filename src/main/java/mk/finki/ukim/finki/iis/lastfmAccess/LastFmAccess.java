package mk.finki.ukim.finki.iis.lastfmAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mk.ukim.finki.iis.model.Track;
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

	private static String getContentFromUrl(String url) throws Exception {
		InputStream is = getStreamFromUrl(url);
		String content;
		if (is != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			content = sb.toString();
			return content;
		}
		return null;
	}

	private static InputStream getStreamFromUrl(String url) throws Exception {
		HttpResponse httpResponse = null;
		InputStream is = null;

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();
		is = httpEntity.getContent();

		if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
			return is;
		}
		return null;
	}
	public static List<Track> parseTopTracks(String url) throws JSONException {

	    String content = null;
	    try {
			content=getContentFromUrl(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Track> tracks = new ArrayList<Track>();
		System.out.println("content");
		System.out.println(content);
		JSONObject json = new JSONObject(content);
		JSONObject jsonTracks=json.getJSONObject("toptracks");
		JSONArray jsonItems=jsonTracks.getJSONArray("track");
		for (int i = 0; i < jsonItems.length(); i++) {
			JSONObject jObj = (JSONObject) jsonItems.get(i);
			Track track=new Track();
			track.setMbid(jObj.getString("mbid"));
			track.setName(jObj.getString("name"));
			track.setUrl(jObj.getString("url"));
			tracks.add(track);
		}
		return tracks;
	}
}


