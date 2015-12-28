package com.dependencies;

import org.json.JSONObject;


/**
 * Created by user on 19.12.15.
 */
public class SolveSession {

    public static String checkToken(String authorHeader)
    {
        Object login = null;
        try {
            if (authorHeader != null) {

                String responceString = StreamRequest.sendGet("http://localhost:5000/api/sessions/session/" + authorHeader);
                login = new JSONObject(responceString).get("login");

            } else {

            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        if(login != null)
            return login.toString();
        else
            return null;
    }

    public static String cheakUser(String login, String passwd)
    {
        Object curr_login = null;
        try {
            String responceString = StreamRequest.sendGet("http://localhost:5000/api/sessions/autho?login="
                                                        + login + "&passwd=" + passwd);
            curr_login = new JSONObject(responceString).get("login");

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        if(curr_login != null)
            return curr_login.toString();
        else
            return null;
    }


    public static String createSession(String login)
    {
        Object token = null;
        try {
            String responceString = StreamRequest.sendGet("http://localhost:5000/api/sessions/session?login="
                    + login);

            token = new JSONObject(responceString).get("token");

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        if(token != null)
            return token.toString();
        else
            return null;
    }

    public static int createUser(String data)
    {
        try{
                int code = StreamRequest.sendPost("http://localhost:5000/api/sessions/user", data);

          //  if(code != null)
                return code;

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return 502;
    }



}
