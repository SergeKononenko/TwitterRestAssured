package com.serge;

import static io.restassured.RestAssured.given;

import java.util.Date;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utils {

	public static String createTweet(Tweet t) {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth()
				.oauth(SensetiveData.apiKey, SensetiveData.apiSecret,
						SensetiveData.accessToken,
						SensetiveData.accessSecret)
				.queryParam("status",
						t.getText() + " : " + new Date())
				.when().post("/update.json").then().statusCode(200)
				.extract().response();

		String response = res.asString();
		//System.out.println(response);
		JsonPath json = new JsonPath(response);
		t.setTweetId(json.get("id").toString());
		System.out.println(
				"Just created a tweet with id - " + t.getTweetId());

		return json.get("text");
	}

	public String getLatestTweet() {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth()
				.oauth(SensetiveData.apiKey, SensetiveData.apiSecret,
						SensetiveData.accessToken,
						SensetiveData.accessSecret)
				.queryParam("count", "1").when()
				.get("/home_timeline.json").then().statusCode(200).extract()
				.response();

		String response = res.asString();
		//System.out.println(response);
		JsonPath json = new JsonPath(response);
		System.out.println(
				"Reading the latest tweet text: " + json.get("text"));

		return response;
	}

	public String deleteTweet(Tweet t) {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth()
				.oauth(SensetiveData.apiKey, SensetiveData.apiSecret,
						SensetiveData.accessToken,
						SensetiveData.accessSecret)
				.queryParam("status",
						"Test post from RestAssured API")
				.when().post("/destroy/" + t.getTweetId() + ".json")
				.then().statusCode(200).extract().response();

		String response = res.asString();
		//System.out.println(response);
		JsonPath json = new JsonPath(response);
		System.out
				.println("Just removed tweet " + json.get("id_str"));

		return json.get("text");
	}

}
