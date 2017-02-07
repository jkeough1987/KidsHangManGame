package com.theironyard;

import javax.persistence.*;

/**
 * Created by joshuakeough on 10/7/16.
 */
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer win;

    @Column(nullable = false)
    private Integer loss;

    @Column(nullable = false)
    private String name;

    public Player() {
    }

    public Player(String firstName, String lastName, String password, Integer win, Integer loss, String name) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.win = win;
        this.loss = loss;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLoss() {
        return loss;
    }

    public void setLoss(Integer loss) {
        this.loss = loss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
