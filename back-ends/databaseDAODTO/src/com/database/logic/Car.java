package com.database.logic;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Created by user on 29.11.15.
 */
@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="car_id")
    private Integer car_id;

    @Column(name="model", length = 64, nullable = false)
    private String model;

    @Column(name="power", nullable = false)
    private Integer power;

    @Column(name="year")
    private String year;

    @Column(name="color")
    private String color;

    @Column(name="cost")
    private Integer cost;

    @Column(name="mark_id")
    private Integer mark_id;

    public Car(){}

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
