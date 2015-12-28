package com.database.logic;

/**
 * Created by user on 27.11.15.
 *
 * */

import java.util.Set;


public class User {

    private Integer user_id;

    private String login;

    private String passwd;

    private String email;


    public User(){}

    public User(String _login,
                String _passwd,
                String _email)
    {
        login = _login;
        passwd = _passwd;
        email = _email;
    }


    public void setId(int Id) {
        this.user_id = Id;
    }

    public void setLogin(String _login)
    {
        this.login = _login;
    }

    public void setPasswd(String _passwd)
    {
        this.passwd = _passwd;
    }

    public void setAddress(String _email) { this.email = _email;}

    public int getId(){ return  this.user_id; }

    public String getLogin() { return this.login; }

    public String getPasswd()
    {
        return this.passwd;
    }

    public String getEmail() { return this.email;}



}
