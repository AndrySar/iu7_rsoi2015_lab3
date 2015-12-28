package com.database.DTO.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.database.container.CarContainer;
import com.database.container.UserContainer;
import com.database.logic.Car;
import com.database.logic.User;

/**
 * Created by user on 26.12.15.
 */
public class UserDTOImpl  implements com.database.DTO.interf.UserDTO{

    public String convertToJsonstring(User user)
    {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.toJson(user);
        }catch (Exception ex)
        {
            return null;
        }
    }

    public String convertToJsonstring(UserContainer model)
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

    public User convertToObject(String json)
    {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.fromJson(json, User.class);

        }catch (Exception ex)
        {
            return null;
        }
    }

    public UserContainer convertToContainer(String json)
    {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.fromJson(json, UserContainer.class);

        }catch (Exception ex)
        {
            return null;
        }
    }
}
