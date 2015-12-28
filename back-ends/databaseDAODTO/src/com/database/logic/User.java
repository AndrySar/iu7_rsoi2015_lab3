package com.database.logic;

/**
 * Created by user on 27.11.15.
 *
 * */

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="user_id")
    private Integer user_id;

    @Column(name="login", length = 64, nullable = false, unique = true)
    private String login;

    @Column(name="passwd", length = 64, nullable = false)
    private String passwd;

    @Column(name="email")
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

    public int getId(){ return this.user_id;}

    public String getLogin() { return this.login; }

    public String getPasswd() { return this.passwd;}

    public String getEmail() { return this.email;}


}
