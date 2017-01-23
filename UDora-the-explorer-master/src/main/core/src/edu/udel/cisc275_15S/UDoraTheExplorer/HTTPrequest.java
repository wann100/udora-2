package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.Net.HttpResponseListener;
/**
 * CLass that connects to database
 * @author mamadou
 * cisc275udora.appspot.comcisc275udora.appspot.com
 *
 */

public class HTTPrequest {
	public Boolean logedin = false;
	public HttpRequest request = new HttpRequest(HttpMethods.GET);
	public HttpRequest    post  = new HttpRequest(HttpMethods.POST);
	public void get(String URL){
		request.setUrl(URL);
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
		@Override
		public void handleHttpResponse (HttpResponse httpResponse) {
			Gdx.app.log("HttpRequestExample", "response: " + httpResponse.getResultAsString());
		}

		@Override
		public void failed (Throwable t) {
			Gdx.app.error("HttpRequestExample", "something went wrong", t);
		}

		@Override
		public void cancelled () {
			Gdx.app.log("HttpRequestExample", "cancelled");
		}
	});
		
	}
	/**
	 * This function adds data to users datable
	 * @param URL
	 * @param username
	 * @param password
	 * @param content
	 */
	public void post (String URL,String username,String content) {
		//this is the content im putting in my database
		// In my python i also secure the password
		content+="&username="+username;
		
		post.setUrl(URL);
		post.setContent(content);
		
		
			Gdx.net.sendHttpRequest(post, new HttpResponseListener() {

				@Override
				public void handleHttpResponse (HttpResponse httpResponse) {
					Gdx.app.log("HttpRequestExample", "response: " + httpResponse.getResultAsString());
				}

				@Override
				public void failed (Throwable t) {
					Gdx.app.error("HttpRequestExample", "something went wrong", t);
				}

				@Override
				public void cancelled () {
					Gdx.app.log("HttpRequestExample", "cancelled");
				}
		});
	}
	/**
	 * checks if username and password in database are the same
	 * and sets boolean logedin to true
	 * @param URL url where server takes input 
	 * @param content format of string(username=email@email.com&password=password);
	 */

	public void login (String URL,String content) {
		//boolean String mamadou;
		//this is the content im putting in my database
		// In my python i also secure the password
		post.setUrl(URL);
		post.setContent(content);
		
			 Gdx.net.sendHttpRequest (post, new HttpResponseListener() {
				public String logerin;
				@Override
				public void handleHttpResponse (final HttpResponse httpResponse) {
							if(httpResponse.getResultAsString().equals("True")){
								Gdx.app.postRunnable(new Runnable() {
									@Override
									public void run () {
										//System.out.println(testing);
										logedin(true);
									}});
								
								}	
					}
				
				
				@Override
				public void failed (Throwable t) {
					Gdx.app.error("HttpRequestExample", "something went wrong", t);
				}

				@Override
				public void cancelled () {
					Gdx.app.log("HttpRequestExample", "cancelled");
				}
				});
			 
	}

/**
 * sets boolean loged in to true
 * @param word
 */
public void logedin(boolean word){
	if(word== true){
	logedin = true;
	}
	//System.out.println(logedin);
}

}
