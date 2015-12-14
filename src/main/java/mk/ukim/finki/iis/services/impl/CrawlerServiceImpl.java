package mk.ukim.finki.iis.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.ukim.finki.iis.crawler.CrawlerInterface;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.persistance.UserRepository;
import mk.ukim.finki.iis.services.CrawlerService;
import mk.ukim.finki.iis.services.UserService;

@Service
public class CrawlerServiceImpl implements CrawlerService{
	
	@Autowired
	private CrawlerInterface crawler;
	
	@Autowired
	private UserService userService;

	public void crawlLastFm(int numberOfSongs, int numberOfUsers) {
		
	}

	public void crawlUsers(int numberOfUsers) {
		User startUser=new User();
		startUser.setName("RJ");
		crawlUsers(startUser);

	}

	public void crawlTracks(List<User> users, int numberOfSongs) {
		
	}
	
	private void crawlUsers(User startUser){
		Queue<User> usersQueue=new LinkedList<User>();
		usersQueue.add(crawler.getUserInfo(startUser.getName()));
		
		while((!usersQueue.isEmpty())){
			User user=usersQueue.poll();
			List<User> friends=crawler.getUserFriends(user);
			if(friends==null)continue;
			for (User friend : friends) {
				// TODO: if user not in database - add to db
				userService.insertUser(crawler.getUserInfo(friend.getName()));
				usersQueue.add(friend);				
  		   }			
		}
	}

}
