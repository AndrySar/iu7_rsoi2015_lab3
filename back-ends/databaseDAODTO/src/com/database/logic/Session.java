package com.database.logic;

/**
 * Created by user on 28.11.15.
 */

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="session")
public class Session {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="session_id")
    private Integer session_id;

    @Column(name="access_token", length = 64)
    private String accessToken;

    @Column(name="date_create")
    @Temporal(value = TemporalType.DATE)
    private Date dateCreate;

    @Column(name="date_spoiled")
    @Temporal(value = TemporalType.DATE)
    private Date dateSpoiled;

    @Column(name="user_id")
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

    public void setUser(int id) { this.user_id = id; }

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

    public int getUser() { return this.user_id; }

}
