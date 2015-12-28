package com.database.logic;


/**
 * Created by user on 29.11.15.
 */

public class Car {

    private Integer car_id;

    private String model;

    private Integer power;

    private String year;

    private String color;

    private Integer cost;

    private Integer mark_id;

    public Car(){}

    public Car(String _model,
               int _power,
               String _year,
               String _color,
               int _cost,
               int _mark_id)
    {
        this.model = _model;
        this.power = _power;
        this.year = _year;
        this.color = _color;
        this.cost = _cost;
        this.mark_id = _mark_id;
    }

    public void setId(int Id) {
        this.car_id = Id;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public void setMark(int id)
    {
        this.mark_id = id;
    }

    public int getId()
    {
        return this.car_id;
    }

    public String getModel()
    {
        return this.model;
    }

    public int getPower()
    {
        return this.power;
    }

    public String getYear()
    {
        return this.year;
    }

    public String getColor()
    {
        return this.color;
    }

    public int getCost()
    {
        return this.cost;
    }

    public int getMark()
    {
        return this.mark_id;
    }

    @Override
    public String toString()
    {
        return "car [mode=" + model + ", power=" + power + ", cost=" + cost + "]";
    }



}
