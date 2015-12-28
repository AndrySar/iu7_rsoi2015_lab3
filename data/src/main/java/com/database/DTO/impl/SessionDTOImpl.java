package com.database.DTO.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.database.DTO.interf.SessionDTO;
import com.database.container.SessionContainer;
import com.database.logic.Session;

/**
 * Created by user on 26.12.15.
 */
public class SessionDTOImpl implements SessionDTO {

    public String convertToJsonstring(Session session)
    {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.toJson(session);
        }catch (Exception ex)
        {
            return null;
        }
    }

    public String convertToJsonstring(SessionContainer model)
    {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.toJson(model);
        }catch (Exception ex)
        {
            return null;
        }
    }

    public Session convertToObject(String json)
    {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.fromJson(json, Session.class);

        }catch (Exception ex)
        {
            return null;
        }
    }

    public SessionContainer convertToContainer(String json)
    {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.fromJson(json, SessionContainer.class);

        }catch (Exception ex)
        {
            return null;
        }
    }

}
