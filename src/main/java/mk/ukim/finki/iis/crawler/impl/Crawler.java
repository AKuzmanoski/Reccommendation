package mk.ukim.finki.iis.crawler.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import mk.finki.ukim.finki.iis.lastfmAccess.LastFmAccess;
import mk.ukim.finki.iis.crawler.CrawlerInterface;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

public class Crawler implements CrawlerInterface{
	

	
	public static void main(String[] args){
		
	}

	public List<Track> getStarterTracks(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getUsersForTrack(Track track) {
		String query=null;
		try {
			query=LastFmAccess.getQueryStringForTrack("track.gettopfans",track.getArtist(),track.getName());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<User> users=new ArrayList<User>();
		List<String> names=LastFmAccess.parseTopfansNames(query);
		query=null;
		for (String name : names) {	
			User user=getUserInfo(name);
			users.add(user);
		}
		return users;
		
	}

	public List<Track> getTrackForUser(User user) {
		String query=null;
		try {
			query=LastFmAccess.getQueryStringForUser("user.gettoptracks", user.getName());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Track> tracks=LastFmAccess.parseTopTracks(query);
		
		return tracks;
	}

	public User getUserInfo(String username) {
		// TODO Auto-generated method stub
		String query=null;
		try {
			query=LastFmAccess.getQueryStringForUser("user.getinfo", username);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LastFmAccess.parseUserInfo(query);
	}

}
