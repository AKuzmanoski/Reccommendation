package mk.ukim.finki.iis.lastfmAccess;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
	
	
	public static String getQueryStringForTrack(String method,String artist,String track) throws UnsupportedEncodingException{
		String query="http://ws.audioscrobbler.com/2.0/?method="+method;
		query+="&artist="+URLEncoder.encode(artist.toLowerCase(),"utf-8");
	    query+="&track=" + URLEncoder.encode(track.toLowerCase(),"utf-8");
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
	public static List<Track> parseTracks(String type,String url){
		String content = null;
	    try {
			content=getContentFromUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Track> tracks = new ArrayList<Track>();
		JSONObject json = new JSONObject(content);
		JSONObject jsonTracks=json.getJSONObject(type);
		JSONArray jsonItems=jsonTracks.getJSONArray("track");
		for (int i = 0; i < jsonItems.length(); i++) {
			JSONObject jObj = (JSONObject) jsonItems.get(i);
			Track track=new Track();
			if(jObj.has("mbid") && jObj.getString("mbid")!=""){
				track.setMbid(jObj.getString("mbid"));
			}
			if(jObj.has("name")){
				track.setName(jObj.getString("name"));
			}
			if(jObj.has("url")){
				track.setUrl(jObj.getString("url"));
			}
			if(jObj.has("playcount")){
				track.setPlaycount(jObj.getLong("playcount"));
			}
			JSONObject artist=jObj.getJSONObject("artist");
			track.setArtist(artist.getString("name"));
			tracks.add(track);
		}
		return tracks;
	}
	
	public static User parseUserInfo(String url){
		String content = null;
	    try {
			content=getContentFromUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    User user=new User();
		JSONObject json = new JSONObject(content);
		JSONObject jsonUser=json.getJSONObject("user");
		if(jsonUser.has("id")){
			user.setLastFMId(jsonUser.getLong("id"));
		}

		if(jsonUser.has("gender")){
			user.setGender(jsonUser.getString("gender"));
		}
		user.setName(jsonUser.getString("name"));
		user.setUrl(jsonUser.getString("url"));
		String countryName = jsonUser.getString("country");
		user.setCountry(countryName);
		return user;
	}
	public static List<User> parseUserFriends(String url) throws JSONException {

	    String content = null;
	    try {
			content=getContentFromUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<User> friends = new ArrayList<User>();
		JSONObject json = new JSONObject(content);
		JSONObject jsonTracks=json.getJSONObject("friends");
		JSONArray jsonItems=jsonTracks.getJSONArray("user");
		for (int i = 0; i < jsonItems.length(); i++) {
			JSONObject jObj = (JSONObject) jsonItems.get(i);
			User user=new User();
			user.setName(jObj.getString("name"));
			user.setUrl(jObj.getString("url"));
			String countryName = jObj.getString("country");
			user.setCountry(countryName);
			friends.add(user);
		}
		return friends;
	}
	public static List<String> parseTopfansNames(String url) throws JSONException {

		List<String> topFansNames=new ArrayList<String>();
	    String content = null;
	    try {
			content=getContentFromUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(content);
		JSONObject jsonTracks=json.getJSONObject("topfans");
		JSONArray jsonItems=jsonTracks.getJSONArray("user");
		for (int i = 0; i < jsonItems.length(); i++) {
			JSONObject jObj = (JSONObject) jsonItems.get(i);
			String username=jObj.getString("name");
			topFansNames.add(username);
			
		}
		return topFansNames;
	}
	
}
