package testTwitter;

import client.TwitterAPIClient;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import load.LoadBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tweet.tweetClient;


import java.util.ArrayList;
import java.util.Iterator;

import static tweet.tweetClient.*;

public class TestTwitterAPI  {
    private tweetClient client;

    @BeforeClass
    public void setupTweetClient() {
        this.client = new tweetClient();
    }

    @Test
//    @Severity(Severity.Blocker)
//    @Description()
    public void testTweetSuccesful() {
        //dont need to generate random
        String tweet = "hi everyone hope this message will find you well";

        ValidatableResponse response = this.client.createNewTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);

    }

    @Test(enabled = true)
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // 1. user send a tweet
        // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet = "HEllo THere";
        ValidatableResponse response = this.client.createNewTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet = response.extract().body().path("text");
        org.junit.Assert.assertEquals(tweet, actualTweet);
        // User send the same tweet again
        response = this.client.createNewTweet(tweet);
        // Verify that the tweet was unsuccessful
        response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        org.junit.Assert.assertEquals(actualMessage, expectedMessage);
        org.junit.Assert.assertNotSame("200", 403);
    }

    @Test(enabled = true)
    public void testTweetDeleted() {
        String tweet = "Hello there ";
        ValidatableResponse response = this.client.deleteTweet(1309883952501579778l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        org.junit.Assert.assertEquals(tweet, actualTweet);
    }

    @Test(enabled = true)

    public void testInfoTweet() {
        String tweet = "LIve and Die on this Day ";
        ValidatableResponse response = this.client.getTweetInfo(1387170021072379909l);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
        System.out.println(actualTweet);

    }

    @Test(enabled = true)

    public void testgetTweet() {
        ValidatableResponse response = this.client.getTweet(1386900355225690112l);
    }

    @Test(enabled = true)
    public void testRetweet() {
        ValidatableResponse response = this.client.postRetweet(1386900355225690112l);
    }

    @Test(enabled = true)
    public void testgetfollowersId() {
        ValidatableResponse response = this.client.getfollowerIds(34654595);
        ResponseBodyExtractionOptions body=response.extract().body().path("text");
        String actualTweet=response.extract().body().path("text");
        System.out.println(body);
    }
    @Test(enabled = true)
    public void testGetFREINDS(){
        ValidatableResponse response = this.client.getFreinds(2806295158L);
        ResponseBodyExtractionOptions body=response.extract().body();
        String actualTweet=response.extract().body().path("text");
        System.out.println(actualTweet);
        //can't figure it out how to get list of followers
    }


    @Test(enabled = false)
    public void setMutipletweetsTweets() {
        ArrayList<String> tweets= new ArrayList<String>();
        tweets.add("HELLO EVERYONE");
        tweets.add("SECOND TWEET");
        tweets.add("THIRD TWEET");
        for (Iterator j = tweets.iterator(); j.hasNext();){
            System.out.println(j.next());
            String tweet= j.toString();
            ValidatableResponse response = this.client.createNewTweet(tweet); }

    }
    @Test(enabled = true)
    public void testTweet2Succesful() {
        //dont need to generate random
        String tweet = "saha ftorkom";

        ValidatableResponse response = this.client.createNewTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);

    }
    @Test(enabled = true)
    public void testTweet3Succesful() {
        //dont need to generate random
        String tweet = "04/27/2021";

        ValidatableResponse response = this.client.createNewTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);

    }


}
