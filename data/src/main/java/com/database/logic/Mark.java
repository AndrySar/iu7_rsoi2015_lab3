package com.database.logic;

import java.util.Set;

/**
 * Created by user on 29.11.15.
 */


public class Mark {

    private Integer mark_id;

    private String name;

    private String contry;


    public Mark(){}

    public Mark(String _name, String _contry)
    {
        this.name = _name;
        this.contry = _contry;
    }

    public void setId(int Id) {
        this.mark_id = Id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setContry(String contry)
    {
        this.contry = contry;
    }


    public int getId()
    {
        return this.mark_id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getContry() { return  this.contry;}



    @Override
    public String toString()
    {
        return "mark [name=" + name + ", contry=" + contry + "]";
    }

}
