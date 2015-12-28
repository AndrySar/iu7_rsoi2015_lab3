package com.database.logic;

/**
 * Created by user on 28.11.15.
 */

import java.util.Date;


public class Session {

    private Integer session_id;

    private String accessToken;

    private Date dateCreate;

    private Date dateSpoiled;

    private int user_id;

    public Session(){}

    public void setId(int Id) {
        this.session_id = Id;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }

    public void setDateSpoiled(Date date)
    {
        this.dateSpoiled = date;
    }

    public void setUser(int id)
    {
        this.user_id = id;
    }

    public int getId()
    {
        return this.session_id;
    }

    public String getAccessToken()
    {
        return this.accessToken;
    }

    public Date getDateCreate()
    {
        return this.dateCreate;
    }

    public Date getDateSpoiled()
    {
        return this.dateSpoiled;
    }

    public int getUser()
    {
        return this.user_id;
    }

}
