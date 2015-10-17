package com.nunovieira;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class HTTPRequest {
	
	
	public static String sendRequest(String url, Map<String, String> textArgs, Map<String, String> fileArgs, String httpMethod){
		try{
			
			if(textArgs == null) textArgs = new HashMap<String, String>();
			if(fileArgs == null) fileArgs = new HashMap<String, String>();
			
			if(httpMethod.equals("GET") || httpMethod.equals("DELETE")){
				HttpClient client = new DefaultHttpClient();
				
				url += "?";
				for(String name: textArgs.keySet()){
					url += name + "=" + textArgs.get(name) + "&";
				}
				
				HttpResponse response = null;
				
				if(httpMethod.equals("GET")){
					HttpGet request = new HttpGet(url);
					response = client.execute(request);
				} else {
					HttpDelete request = new HttpDelete(url);
					response = client.execute(request);
				}
				
				HttpEntity resEntity = response.getEntity();
		        
				return EntityUtils.toString(resEntity);
			}
			
			if(httpMethod.equals("POST") || httpMethod.equals("PUT")){
				MultipartEntity mpEntity = new MultipartEntity();
				
				for(String name: textArgs.keySet()){
					ContentBody arg = new StringBody(textArgs.get(name));
					mpEntity.addPart(name, arg);
				}
				
				for(String name: fileArgs.keySet()){
					File f = new File(fileArgs.get(name));
					ContentBody arg = new FileBody(f);
					mpEntity.addPart(name, arg);
				}
				
				HttpClient client = new DefaultHttpClient();
				HttpResponse response = null;
				
				if(httpMethod.equals("POST")){
					HttpPost request = new HttpPost(url);
					request.setEntity(mpEntity);
					response = client.execute(request);
				} else {
					HttpPut request = new HttpPut(url);
					request.setEntity(mpEntity);
					response = client.execute(request);
				}
				
		        HttpEntity resEntity = response.getEntity();
		        
		        return EntityUtils.toString(resEntity);
			}
			
		}
		catch(Exception e){ return null; }
		
		return "";
	}
    
	
	

	
    
    public static String getContents(String url) {
        URL u;
        StringBuilder builder = new StringBuilder();
        try {
            u = new URL(url);
            try {
                BufferedReader theHTML = new BufferedReader(new InputStreamReader(u.openStream()));
                String thisLine;
                while ((thisLine = theHTML.readLine()) != null) {
                    builder.append(thisLine).append("\n");
                } 
            } 
            catch (Exception e) {
                System.err.println(e);
            }
        } catch (MalformedURLException e) {
            System.err.println(url + " is not a parseable URL");
            System.err.println(e);
        }
        return builder.toString();
    }
	
}
