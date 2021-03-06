package com.serge;

import static io.restassured.RestAssured.given;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utils {

	public static Logger log = Logger.getLogger(Utils.class);
	
	
	public static String createTweet(Tweet t) {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth()
				.oauth(SensetiveData.apiKey, SensetiveData.apiSecret,
						SensetiveData.accessToken,
						SensetiveData.accessSecret)
				.queryParam("status", t.getText()).when()
				.post("/update.json").then().statusCode(200).extract()
				.response();

		String response = res.asString();
		// System.out.println(response);
		JsonPath json = new JsonPath(response);
		t.setTweetId(json.get("id").toString());
		log.info("Just created a tweet with id - " + t.getTweetId());

		return json.get("text");
	}

	public String getLatestTweet() throws ParseException {

		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth()
				.oauth(SensetiveData.apiKey, SensetiveData.apiSecret,
						SensetiveData.accessToken,
						SensetiveData.accessSecret)
				.queryParam("count", "1").when()
				.get("/home_timeline.json").then().statusCode(200)
				.extract().response();

		String response = res.asString();
		JSONParser parser = new JSONParser();
		JSONArray json = (JSONArray) parser.parse(response);
		JSONObject jo = (JSONObject) json.get(0);
		log.info("Reading the latest tweet text: "
				+ (String) jo.get("text"));
		return (String) jo.get("text");
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
		// System.out.println(response);
		JsonPath json = new JsonPath(response);
		log.info("Just removed tweet " + json.get("id_str"));

		return json.get("text");
	}

}
