package com.database.DTO.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.database.container.ModelContainer;
import com.database.logic.Mark;

/**
 * Created by user on 25.12.15.
 */
public class ModelDTOImpl implements com.database.DTO.interf.ModelDTO {

    public String convertToJsonstring(Mark mark)
    {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
             return gson.toJson(mark);
        }catch (Exception ex)
        {
            return null;
        }
    }

    public String convertToJsonstring(ModelContainer model)
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

    public Mark convertToObject(String json)
    {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.fromJson(json, Mark.class);

        }catch (Exception ex)
        {
            return null;
        }
    }

    public ModelContainer convertToContainer(String json)
    {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.fromJson(json, ModelContainer.class);

        }catch (Exception ex)
        {
            return null;
        }
    }


}
