package com.serge;

public class Tweet {

	private String tweetId;
	private String text;
	
	
	public Tweet(String text) {
		super();
		this.text = text;
	}
		
	public String getTweetId() {
		return tweetId;
	}
	
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	
	public String getText() {
		return text;
	}
		
}
