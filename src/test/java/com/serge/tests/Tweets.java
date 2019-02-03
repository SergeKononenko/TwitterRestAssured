package com.serge.tests;

import org.testng.annotations.Test;

import com.serge.SensetiveData;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Date;

public class Tweets {

	@Test(priority = 2)
	public void getLatestTweet() {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth()
				.oauth(SensetiveData.apiKey, SensetiveData.apiSecret,
						SensetiveData.accessToken,
						SensetiveData.accessSecret)
				.queryParam("count", "1").when()
				.get("/home_timeline.json").then().extract()
				.response();

		String response = res.asString();
		// System.out.println(response);
		JsonPath json = new JsonPath(response);
		System.out.println(
				"Reading the latest tweet text: " + json.get("text"));

	}

	@Test(priority = 1)
	public void createTweet() {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth()
				.oauth(SensetiveData.apiKey, SensetiveData.apiSecret,
						SensetiveData.accessToken,
						SensetiveData.accessSecret)
				.queryParam("status",
						"Test post from RestAssured API123" + new Date().getTime())
				.when().post("/update.json").then().extract()
				.response();

		String response = res.asString();
		System.out.println(response);
		JsonPath json = new JsonPath(response);
		tweetId = json.get("id").toString();
		System.out
				.println("Just created a tweet with id - " + tweetId);

	}

	@Test(priority = 3)
	public void deleteTweet() throws InterruptedException {
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth()
				.oauth(SensetiveData.apiKey, SensetiveData.apiSecret,
						SensetiveData.accessToken,
						SensetiveData.accessSecret)
				.queryParam("status",
						"Test post from RestAssured API")
				.when().post("/destroy/" + tweetId + ".json").then()
				.extract().response();

		String response = res.asString();
		// System.out.println(response);
		JsonPath json = new JsonPath(response);
		System.out.println("Just removed tweet " + json.get("id_str"));

	}

	private String tweetId;

}
