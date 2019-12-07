package com.h4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLFetcher {
	public static String get(String url){
		StringBuilder sb = new StringBuilder();
		try {
			URL graphURL = new URL(url);
			HttpURLConnection conn = 
					(HttpURLConnection)graphURL.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line = in.readLine();
			while(line !=null){
				sb.append(line);
				line = in.readLine();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
