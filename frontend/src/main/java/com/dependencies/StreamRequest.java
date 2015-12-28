package com.dependencies;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 26.12.15.
 */
public class StreamRequest {

    public static String sendGet(String url)throws IOException {

        URL httpURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        InputStream ins = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader br = new BufferedReader(isr);

//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (conn.getInputStream())));

        String outputLine = null;
        String str = "";

        while ((outputLine = br.readLine()) != null) {
            str += outputLine;
        }

        conn.disconnect();

        return str;
    }

    public static int sendPost(String url, String data)throws IOException {

        URL httpURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send post request
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        InputStream ins = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader br = new BufferedReader(isr);

//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (conn.getInputStream())));

        String outputLine = null;
        String str = null;

        while ((outputLine = br.readLine()) != null) {
            str += outputLine;
        }

        int code = conn.getResponseCode();

        br.close();
        conn.disconnect();

        return code;
    }

    public static int sendDelete(String url)throws IOException {

        URL httpURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        InputStream ins = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader br = new BufferedReader(isr);

//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (conn.getInputStream())));

        String outputLine = null;
        String str = "";

        while ((outputLine = br.readLine()) != null) {
            str += outputLine;
        }

        int code = conn.getResponseCode();
        br.close();
        conn.disconnect();

        return code;
    }

    public static int sendPut(String url, String data)throws IOException {

        URL httpURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send post request
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(data);
        wr.flush();
        wr.close();

        InputStream ins = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader br = new BufferedReader(isr);

//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (conn.getInputStream())));

        String outputLine = null;
        String str = null;

        while ((outputLine = br.readLine()) != null) {
            str += outputLine;
        }

        int code = conn.getResponseCode();

        br.close();
        conn.disconnect();

        return code;
    }
}
