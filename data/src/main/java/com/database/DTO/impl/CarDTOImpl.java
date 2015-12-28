package com.database.DTO.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.database.container.CarContainer;
import com.database.container.ModelContainer;
import com.database.logic.Car;
import com.database.logic.Mark;

/**
 * Created by user on 26.12.15.
 */
public class CarDTOImpl implements com.database.DTO.interf.CarDTO {

    public String convertToJsonstring(Car car)
    {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.toJson(car);
        }catch (Exception ex)
        {
            return null;
        }
    }

    public String convertToJsonstring(CarContainer model)
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

    public Car convertToObject(String json)
    {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.fromJson(json, Car.class);

        }catch (Exception ex)
        {
            return null;
        }
    }

    public CarContainer convertToContainer(String json)
    {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            return gson.fromJson(json, CarContainer.class);

        }catch (Exception ex)
        {
            return null;
        }
    }

}
