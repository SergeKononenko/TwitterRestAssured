package com.serge.tests;

import org.testng.annotations.Test;

import com.serge.Tweet;
import com.serge.Utils;

public class TweeterRESTApiTest extends Utils {
	
	Tweet t = new Tweet("Test tweet from REST Assured API"); 
	
	@Test
	public void createTweetTest() {
		
		createTweet(t);
		
	}
	
	@Test
	public void getLatestTweetTest() {
		
		getLatestTweet();
		
	}
	
	@Test
	public void deleteTweetTest() {
		
		deleteTweet(t);
		
	}
	
	
}
