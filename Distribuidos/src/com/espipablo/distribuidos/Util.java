package com.espipablo.distribuidos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Util {
	public static String request(String urlS) {
        URL url = null;
        try {
            url = new URL(urlS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        try {
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String output;
        String result = "";
        try {
            while ((output = br.readLine()) != null) {
                result += output;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        return result;

    }
}