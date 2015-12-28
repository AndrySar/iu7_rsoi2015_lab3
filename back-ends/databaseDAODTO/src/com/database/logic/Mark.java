package com.database.logic;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by user on 29.11.15.
 */

@Entity
@Table(name="mark")
public class Mark {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="mark_id")
    private Integer mark_id;

    @Column(name="name", length = 128, nullable = false)
    private String name;

    @Column(name="contry", length = 128, nullable = false)
    private String contry;


    public Mark(){}

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
