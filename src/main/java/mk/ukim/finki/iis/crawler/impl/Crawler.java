package mk.ukim.finki.iis.crawler.impl;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import mk.ukim.finki.iis.lastfmAccess.LastFmAccess;
import mk.ukim.finki.iis.crawler.CrawlerInterface;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

public class Crawler implements CrawlerInterface{

	public List<Track> getTopTracksForUser(User user) {
		String query=null;
		try {
			query=LastFmAccess.getQueryStringForUser("user.gettoptracks", user.getName());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Track> tracks=LastFmAccess.parseTracks("toptracks",query);
		
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

	public List<User> getUserFriends(User user) {
		String query=null;
		try {
			query=LastFmAccess.getQueryStringForUser("user.getfriends", user.getName());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LastFmAccess.parseUserFriends(query);
	}

	public void crawlUsers(User startUser) {
		Queue<User> usersQueue=new LinkedList<User>();
		usersQueue.add(startUser);
		//bfs
		while((!usersQueue.isEmpty())){
			User user=usersQueue.poll();
			List<User> friends=getUserFriends(user);
			if(friends==null)continue;
			for (User friend : friends) {
				// TODO: if user not in database - add to db
				System.out.println(friend);
				usersQueue.add(friend);				
  		   }			
		}		
	}

	public List<Track> getLovedTracksForUser(User user) {
		String query=null;
		try {
			query=LastFmAccess.getQueryStringForUser("user.getlovedtracks", user.getName());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Track> tracks=LastFmAccess.parseTracks("lovedtracks",query);
		
		return tracks;
	}

	public List<Track> getSimilarTracks(Track track) {
		String query=null;
		try {
			query=LastFmAccess.getQueryStringForTrack("track.getsimilar",track.getArtist(),track.getName());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Track> tracks=LastFmAccess.parseTracks("similartracks",query);
		
		return tracks;
	}
}
