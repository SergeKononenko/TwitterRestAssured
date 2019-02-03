package com.serge.tests;

import java.util.Date;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.serge.Tweet;
import com.serge.Utils;

public class TweeterRESTApiTest extends Utils {

	public static Logger log = Logger.getLogger("devpinoyLogger");
	String expected = "Test tweet from REST Assured API: "
			+ new Date();
	Tweet t = new Tweet(expected);

	@Test(priority = 1)
	public void createTweetTest() {
            
		String actual = createTweet(t);
		Assert.assertTrue(actual.contains(expected));
	}

	@Test(dependsOnMethods = "createTweetTest")
	public void getLatestTweetTest() throws ParseException {

		String actual = getLatestTweet();
		Assert.assertTrue(actual.contains(expected));

	}

	@Test(dependsOnMethods = "getLatestTweetTest")
	public void deleteTweetTest() {

		String actual = deleteTweet(t);
		Assert.assertTrue(actual.contains(expected));
	}

}
