package com.serge.tests;

import java.util.Date;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.serge.Tweet;
import com.serge.Utils;

public class TweeterRESTApiTest extends Utils {
	
	Tweet t = new Tweet("Test tweet from REST Assured API: "  + new Date()); 
	
	@Test(priority = 1)
	public void createTweetTest() {
		
		String actual = createTweet(t);
		String expected = t.getText();
		Assert.assertTrue(actual.contains(expected));
	}
	
	@Test(priority = 2)
	public void getLatestTweetTest() throws ParseException {
		
		String actual = getLatestTweet();
		String expected = t.getText();
		Assert.assertTrue(actual.contains(expected));
		
	}
	
	@Test(priority = 3)
	public void deleteTweetTest() {
		
		String actual = deleteTweet(t);
		String expected = t.getText();
		Assert.assertTrue(actual.contains(expected));
	}
	
	
}
